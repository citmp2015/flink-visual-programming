package org.tuberlin.de.deployment;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tuberlin.de.common.model.Constants;
import org.tuberlin.de.deployment.util.DOMParser;
import org.tuberlin.de.deployment.util.ExecuteShell;
import org.tuberlin.de.deployment.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Implements the DeploymentInterface and is responsible for building the project, executing it on a cluster and return
 * output files to the user. It creates a temporary folder under /tmp for each task (execution, jar, zip).
 * It copies the FlinkSkeleton into the folder. All further operations are done only in the tmp folder,
 * not the Skeleton.
 */
public class DeploymentImplementation implements DeploymentInterface {

    private static final Logger LOG = LoggerFactory.getLogger(DeploymentImplementation.class);

    private static DeploymentInterface instance;

    public static String PROPERTIES_FILE_NAME = "deploy.properties";

    private String flinkPath;
    private String flinkClusterAddress;
    private String flinkPort;

    public static DeploymentInterface getInstance() {
        if (instance == null) {
            instance = new DeploymentImplementation();
        }
        return instance;
    }

    private DeploymentImplementation() {
        Properties myProperties = new Properties();
        try {
            myProperties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            LOG.error("Could not read properties file for " + PROPERTIES_FILE_NAME);
            e.printStackTrace();
        }

        flinkPath = myProperties.getProperty("flink.path");
        flinkClusterAddress = myProperties.getProperty("flink.jobmanager.address");
        flinkPort = myProperties.getProperty("flink.jobmanager.port");
    }

    /**
     * Logs the message to the local LOG and to the Websocket simultaneously
     *
     * @param clientSession The Websocket session
     * @param message       The message to be logged
     */
    private void logEvent(Session clientSession, String message) {
        LOG.debug(message);
        if (clientSession != null && clientSession.isOpen()) {
            try {
                clientSession.getRemote().sendString(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void generateProjectDirectory(Session clientSession, String uuid, String entryClass, Map<String, String> clazzes) {

        File temporaryFolder;

        try {

            temporaryFolder = createTemporaryProjectFolder(clientSession, uuid);

            createClasses(temporaryFolder, entryClass, clazzes);

            logEvent(clientSession, "graph:" + uuid + ":mvnBuildStarted");

            InvocationRequest request = new DefaultInvocationRequest();
            StringBuilder mvnOutput = new StringBuilder();
            request.setOutputHandler(s -> {
                mvnOutput.append(s);
                mvnOutput.append("\n");
            });
            request.setPomFile(new File(temporaryFolder.getAbsolutePath() + "/pom.xml"));
            request.setGoals(Arrays.asList("clean", "package"));
            request.setProfiles(Collections.singletonList("build-jar"));
            Invoker invoker = new DefaultInvoker();
            InvocationResult invocationResult = invoker.execute(request);

            logEvent(clientSession, "graph:" + uuid + ":mvnBuildOutput " + mvnOutput);

            if (invocationResult.getExitCode() == 0) {
                logEvent(clientSession, "graph:" + uuid + ":mvnBuildSucceeded");
            } else {
                logEvent(clientSession, "graph:" + uuid + ":mvnBuildError");
            }

            return;

        } catch (IOException e) {
            LOG.error("Failed to create tmp directory");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            LOG.error("Error", e);
            e.printStackTrace();
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }

        logEvent(clientSession, "graph:" + uuid + ":mvnBuildError");
    }

    public void deploy(Session clientSession, String uuid) {

        try {

            File temporaryFolder = loadTemporaryProjectFolder(clientSession, uuid);

            logEvent(clientSession, "graph:" + uuid + ":deployStarted");

            String outputJar = temporaryFolder.getAbsolutePath() + "/target/" + Constants.FLINK_JOB_NAME + "-1.0.jar";
            String flinkClusterExecutionCommand = flinkPath + " run -m " + flinkClusterAddress + ":" + flinkPort + " " + outputJar;

            LOG.debug("Flink cluster execution command: " + flinkClusterExecutionCommand);
            logEvent(clientSession, "graph:" + uuid + ":deployOutput " +
                    ExecuteShell.executeCommand(flinkClusterExecutionCommand, temporaryFolder));

            logEvent(clientSession, "graph:" + uuid + ":deploySucceeded");

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            logEvent(clientSession, "graph:" + uuid + ":deployError");
        }
    }

    @Override
    public InputStream getJarStream(Session clientSession, String uuid) {

        try {

            File temporaryProjectFolder = loadTemporaryProjectFolder(clientSession, uuid);

            if (!temporaryProjectFolder.exists()) {
                logEvent(clientSession, "Failed to load project folder");
                return null;
            }

            logEvent(clientSession, "Loaded temporary project folder: " + temporaryProjectFolder.getAbsolutePath());

            // Get ouput jar name
            String outputJarName = temporaryProjectFolder.toString() + "/target/" + Constants.FLINK_JOB_NAME + "-1.0.jar";
            File outputJar = new File(outputJarName);

            // Return created jar as stream
            return new FileInputStream(outputJar);

        } catch (URISyntaxException | IOException e) {
            LOG.error("Failed to getJarStream", e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public InputStream getZipSource(Session clientSession, String uuid) {

        try {

            File temporaryProjectFolder = loadTemporaryProjectFolder(clientSession, uuid);

            if (!temporaryProjectFolder.exists()) {
                logEvent(clientSession, "Failed to load project folder");
                return null;
            }

            logEvent(clientSession, "Loaded temporary project folder: " + temporaryProjectFolder.getAbsolutePath());

            String zipFilePath = temporaryProjectFolder.getParentFile().toString() + "/FlinkProject.zip";
            File zipFile = new File(zipFilePath);

            // Zip src folder and not tmp root, because output zip also is created there and this might lead to an error
            String srcFolderPath = temporaryProjectFolder.toString();
            File srcFolderToZip = new File(srcFolderPath);
            FileUtils.zipFolder(srcFolderToZip, zipFile);

            // Return created zip as stream
            return new FileInputStream(zipFile);

        } catch (URISyntaxException | IOException e) {
            LOG.error("Failed to getZipStream", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a temporary project folder, that is only used once.
     * It copies all files from the FlinkSkeleton to /tmp/{SomeRandomUUID}.
     * All further modifications (addition of files/classes) should be done to this temporary project.
     *
     * @param clientSession The websocket session
     * @return A file object that represents the temporary project directory
     */
    private File createTemporaryProjectFolder(Session clientSession, String uuid) throws IOException, URISyntaxException {

        logEvent(clientSession, "Creating temporary folders: " + uuid);

        // Creating tmp directory
        String tempDirPath = System.getProperty("java.io.tmpdir");
        Path tempDir = FileSystems.getDefault().getPath(tempDirPath + "/" + uuid);
        Path tmpDirectory = Files.createDirectory(tempDir);

        File tmpProjectFolderParent = tmpDirectory.toFile();
        File tmpProjectFolder = new File(tmpProjectFolderParent.getAbsolutePath() + "/FlinkProject");

        File skeletonFolder = new File(getClass().getClassLoader().getResource("FlinkSkeleton/").toURI());

        // Copying skeleton to tmp directory
        LOG.debug("Copy directory from " + skeletonFolder.toString() + "  to " + tmpDirectory.toString());
        FileUtils.copyFolder(skeletonFolder, tmpProjectFolder);

        // Map containing the values to be replaced in the pom.xml
        HashMap<String, String> map = new HashMap<>();
        map.put(Constants.ARTIFACT_ID_KEY, Constants.FLINK_JOB_NAME);
        map.put(Constants.ENTRY_CLASS_KEY, Constants.ENTRY_CLASS_NAME_WITH_PACKAGE);
        map.put(Constants.MANIFEST_ENTRY_MAIN_CLASS, Constants.ENTRY_CLASS_NAME_WITH_PACKAGE);

        // Get jar name
        String pomXMLPath = tmpProjectFolder.toString() + "/pom.xml";
        File pomFile = new File(pomXMLPath);

        // Replaces values in pom.xml corresponding to the map above
        DOMParser.replaceXMLValues(pomFile, map);

        return tmpProjectFolder;
    }

    private File loadTemporaryProjectFolder(Session clientSession, String uuid) throws IOException, URISyntaxException {

        String tempDirPath = System.getProperty("java.io.tmpdir");
        Path tempDir = FileSystems.getDefault().getPath(tempDirPath + "/" + uuid);

        logEvent(clientSession, "Trying to load project folder from " + tempDir.toString() + "/FlinkProject");

        return new File(tempDir + "/FlinkProject");
    }

    /**
     * Adds the classes from the source code generator to the temporary working directory
     *
     * @param temporaryFolder The base folder of the temporary directory
     * @param entryClass      The entry class from the source code generator
     * @param clazzes         The classes from the source code generator
     */
    private void createClasses(File temporaryFolder, String entryClass, Map<String, String> clazzes) {
        saveClass(temporaryFolder, Constants.ENTRY_CLASS_NAME, entryClass);
        clazzes.forEach((className, clazz) -> saveClass(temporaryFolder, className, clazz));
    }

    /**
     * This methods saves a class as a child of the temporary folder
     *
     * @param temporarayFolder the parent folder
     * @param clazzName        the className of the class
     * @param clazz            the content of the class (source code)
     */
    private void saveClass(File temporarayFolder, String clazzName, String clazz) {
        try {
            File outputFile = new File(temporarayFolder.getPath() + "/src/main/java/testpackage/" + clazzName + ".java");
            FileOutputStream stream = new FileOutputStream(outputFile);
            stream.write(clazz.getBytes());
            stream.flush();
            stream.close();
        } catch (IOException e) {
            LOG.error("could not write class ", e);
        }
    }

    /**
     * This method parses the classname of a file
     *
     * @param clazz the entire class
     */
    private String getClassName(String clazz) {
        Pattern pattern = Pattern.compile("class (\\w+)\\s*[i{]");
        Matcher matcher = pattern.matcher(clazz);
        LOG.debug("Found class name - " + matcher.group(0));
        return matcher.group(0);
    }

    /**
     * Deletes the tmp Project folder after the task is done.
     *
     * @param tmpProjectFolder The temporary project folder
     */
    public void cleanUp(File tmpProjectFolder) {

        if (tmpProjectFolder == null) {
            LOG.debug("Nothing to clean up as tmpProjectFolder is null");
            return;
        }

        LOG.debug("Deleting temporary project folder for " + tmpProjectFolder.toString());

        try {
            FileUtils.removeRecursive(tmpProjectFolder.toPath());
        } catch (IOException e) {
            LOG.error("Could not remove temporary folder " + tmpProjectFolder.toString(), e);
            e.printStackTrace();
        }
    }
}