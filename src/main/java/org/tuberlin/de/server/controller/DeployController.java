package org.tuberlin.de.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tuberlin.de.deployment.DeploymentInterface;
import org.tuberlin.de.deployment.util.ExecuteShell;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Controller that is invoked when clicking on "Deploy and Run" Button.
 * This Controller is currently only a Dummy.
 * In future this Controller should kick-off the following tasks:
 * 1. Code generation from the graph
 * 2. Insert code into Maven project
 * 3. Compile Maven project into Jar
 * 4. Deploy Jar to remote Flink maschine
 * <p/>
 * This Controller handles a POST event and awaits a JSON content that describes
 * the graph.
 */
@WebServlet(urlPatterns = {"/deploy"})
public class DeployController extends HttpServlet implements DeploymentInterface {

    private static final long serialVersionUID = 23523652345L;
    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    public static String PROPERTIES_FILE_NAME = "deploy.properties";

    private Properties myProperties;

    public DeployController() {
        myProperties = new Properties();
        try  {
            myProperties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            LOG.error("Could not read properties file for " + PROPERTIES_FILE_NAME);
            e.printStackTrace();
        }
    }

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
//        String json = req.getParameter("graph");

        LOG.debug("Starting deployment");

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
    }

    @Override
    public void generateProjectJAR(String entryClass, List<String> clazzes, boolean deploy) {

    }

    @Override
    public OutputStream getJarStream() {
        return null;
    }
}