package org.tuberlin.de.deployment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tuberlin.de.deployment.util.ExecuteShell;
import org.tuberlin.de.server.controller.BaseController;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Fabian on 13.01.2016.
 */
public class DeploymentImplementation implements DeploymentInterface {

    public static String PROPERTIES_FILE_NAME = "deploy.properties";
    private static final Logger LOG = LoggerFactory.getLogger(DeploymentImplementation.class);

    private Properties myProperties;

    public DeploymentImplementation() {
        myProperties = new Properties();
        try  {
            myProperties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            LOG.error("Could not read properties file for " + PROPERTIES_FILE_NAME);
            e.printStackTrace();
        }
    }

    //TODO: get this right
    private String getFlinkCommand() {
        String classToJar = new Scanner(
                getClass().getClassLoader().getResourceAsStream("FlinkSkeleton/src/main/java/org/test/de/WordCount.java"),
                "utf-8").useDelimiter("\\Z").next();

        LOG.debug("Showing class content:\n" + classToJar);

        String flinkPath = myProperties.getProperty("flink.path");
        String flinkAddress = myProperties.getProperty("flink.jobmanager.address");
        String flinkPort = myProperties.getProperty("flink.jobmanager.port");
        String flinkCommand = flinkPath + " " + " -m " + flinkAddress + ":" + flinkPort;

        LOG.debug("Flink command: " + flinkCommand);

        LOG.debug("Current directory: " + ExecuteShell.executeCommand("pwd"));
        LOG.debug("Maven Invocation " + ExecuteShell.executeCommand("mvn package", new File("src/main/resources/FlinkSkeleton")));

        return flinkAddress + " " + flinkPort;
    }

    @Override
    public void generateProjectJAR(String entryClass, List<String> clazzes, boolean deploy) {

    }

    @Override
    public OutputStream getJarStream() {
        return null;
    }

    @Override
    public OutputStream getZipSource(String entryClass, List<String> clazzes) {
        return null;
    }
}
