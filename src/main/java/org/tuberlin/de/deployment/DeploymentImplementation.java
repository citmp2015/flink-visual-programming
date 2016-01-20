package org.tuberlin.de.deployment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tuberlin.de.deployment.util.ExecuteShell;
import org.tuberlin.de.deployment.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Implements the DeploymentInterface and is responsible for building the project, executing it on a cluster and return
 * output files to the user. It creates a temporary folder under /tmp for each task (execution, jar, zip).
 * It copies the FlinkSkeleton into the folder. All further operations are done only in the tmp folder,
 * not the Skeleton.
 */
public class DeploymentImplementation implements DeploymentInterface {

    public static String PROPERTIES_FILE_NAME = "deploy.properties";
    private static final Logger LOG = LoggerFactory.getLogger(DeploymentImplementation.class);

    private String mavenPath;
    private String flinkPath;
    private String flinkClusterAddress;
    private String flinkPort;

    public DeploymentImplementation() {
        Properties myProperties = new Properties();
        try {
            myProperties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            LOG.error("Could not read properties file for " + PROPERTIES_FILE_NAME);
            e.printStackTrace();
        }

        mavenPath = myProperties.getProperty("maven.path");
        flinkPath = myProperties.getProperty("flink.path");
        flinkClusterAddress = myProperties.getProperty("flink.jobmanager.address");
        flinkPort = myProperties.getProperty("flink.jobmanager.port");
    }

    @Override
    public void generateProjectJAR(String entryClass, List<String> clazzes, boolean deploy) {

        File temporaryFolder = null;
        try {
             temporaryFolder = createTemporaryProjectFolder();

            createClasses(temporaryFolder, entryClass, clazzes);

            LOG.debug("Maven Invocation " + ExecuteShell.executeCommand(mavenPath + " package", temporaryFolder));

            if (deploy) {

                String outputJar = temporaryFolder.toString() + "/target/";
                String flinkClusterExecutionCommand = flinkPath + " run -m " + flinkClusterAddress + ":" + flinkPort + " " + outputJar;

                LOG.debug("Flink cluster execution command: " + flinkClusterExecutionCommand);
                LOG.debug("Maven Invocation " + ExecuteShell.executeCommand(flinkClusterExecutionCommand, temporaryFolder));
            }

        } catch (IOException e) {
            LOG.error("Failed to create tmp directory");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            LOG.error("Error", e);
            e.printStackTrace();
        } finally {
            cleanUp(temporaryFolder);
        }
    }

    @Override
    public InputStream getJarStream() {

        File temporaryProjectFolder = null;
        try {
             temporaryProjectFolder = createTemporaryProjectFolder();

            // TODO something missing. Creation of jar with empty project?

            // Trigger Maven Build
            LOG.debug("Maven Invocation " + ExecuteShell.executeCommand(mavenPath + " package", temporaryProjectFolder));

            // Get jar name
            String outputJarName = temporaryProjectFolder.toString() + "/target/" + "original-flink-job-1.0.jar";
            File outputJar = new File(outputJarName);

            // Return created jar as stream
            return new FileInputStream(outputJar);

        } catch (URISyntaxException | IOException e) {
            LOG.error("Failed to getJarStream", e);
            e.printStackTrace();
        } finally {
            cleanUp(temporaryProjectFolder);
        }
        return null;
    }

    @Override
    public InputStream getZipSource(String entryClass, List<String> clazzes) {

        File temporaryProjectFolder = null;
        try {
            temporaryProjectFolder = createTemporaryProjectFolder();

            createClasses(temporaryProjectFolder, entryClass, clazzes);

            String zipFilePath = temporaryProjectFolder.toString() + "/FlinkProject.zip";
            File zipFile = new File(zipFilePath);

            // Zip src folder and not tmp root, because output zip also is created there and this might lead to an error
            String srcFolderPath = temporaryProjectFolder.toString() + "/src/";
            File srcFolderToZip = new File(srcFolderPath);
            FileUtils.zipFolder(srcFolderToZip, zipFile);

            // Return created zip as stream
            return new FileInputStream(zipFile);

        } catch (URISyntaxException | IOException e) {
            LOG.error("Failed to getJarStream", e);
            e.printStackTrace();
        } finally {
            cleanUp(temporaryProjectFolder);
        }
        return null;
    }

    /**
     * Creates a temporary project folder, that is only used once.
     * It copies all files from the FlinkSkeleton to /tmp/{SomeRandomUUID}.
     * All further modifications (addition of files/classes) should be done to this temporary project.
     *
     * @return A file object that represents the temporary project directory
     */
    private File createTemporaryProjectFolder() throws IOException, URISyntaxException {

        // Used to create tmp folder for this specific project
        String randomUUID = UUID.randomUUID().toString();

        LOG.debug("Current directory: " + ExecuteShell.executeCommand("pwd"));

        // Creating tmp directory
        Path tmpDirectory = Files.createTempDirectory(randomUUID);
        File tmpProjectFolder = tmpDirectory.toFile();

        File skeletonFolder = new File(getClass().getClassLoader().getResource("FlinkSkeleton/").toURI());

        // Copying skeleton to tmp directory
        FileUtils.copyFolder(skeletonFolder, tmpProjectFolder);

        return tmpProjectFolder;
    }

    /**
     * Adds the classes from the source code generator to the temporary working directory
     *
     * @param temporaryFolder The base folder of the temporary directory
     * @param entryClass      The entry class from the source code generator
     * @param clazzes         The classes from the source code generator
     */
    private void createClasses(File temporaryFolder, String entryClass, List<String> clazzes) {


        // TODO Copy java class files to tmp directory
//            String classToJar = new Scanner(
//                    getClass().getClassLoader().getResourceAsStream("FlinkSkeleton/src/main/java/org/test/de/WordCount.java"),
//                    "utf-8").useDelimiter("\\Z").next();
//
//            LOG.debug("Showing class content:\n" + classToJar);
    }

    /**
     * Deletes the tmp Project folder after the task is done.
     *
     * @param tmpProjectFolder The temporary project folder
     */
    private void cleanUp(File tmpProjectFolder) {

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
