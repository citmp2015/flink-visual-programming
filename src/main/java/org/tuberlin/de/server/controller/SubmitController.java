package org.tuberlin.de.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tuberlin.de.common.codegenerator.CodeGenerator;
import org.tuberlin.de.common.model.BackendControllerImpl;
import org.tuberlin.de.common.model.JSONParser;
import org.tuberlin.de.common.model.interfaces.BackendController;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.deployment.DeploymentImplementation;
import org.tuberlin.de.deployment.DeploymentInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller that is invoked when clicking on any of the following buttons:
 * <p>
 * 1.  "Deploy and Run"
 * 2.  "Download Java Project"
 * 3.  "Download JAR File"
 * </p>
 * <p/>
 * The information which button was pressed is stored into the POST parameter "action".
 * This Controller is currently only a Dummy.
 * In future this Controller should kick-off the following tasks:
 * <p/>
 * 1. Code generation from the graph
 * 2. Insert code into Maven project
 * 3. Compile Maven project into Jar
 * 4. Deploy Jar to remote Flink maschine
 * <p/>
 * This Controller handles a POST event and awaits a JSON content that describes
 * the graph.
 */
@WebServlet(urlPatterns = {"/submit_jobgraph"})
public class SubmitController extends HttpServlet {

    private static final long serialVersionUID = 23523652345L;
    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    private DeploymentInterface deploymentInterface;

    public SubmitController() {
        LOG.debug("Init " + SubmitController.class.getSimpleName());
        deploymentInterface = new DeploymentImplementation();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        BackendController backendController = new BackendControllerImpl();
        String json = req.getParameter("graph");


        JobGraph jobGraph;
        try {
            jobGraph = backendController.getJobGraph(json);
        } catch (Exception e) {
            LOG.error("Error in construction jobGraph.", e);
            return;
        }

        String action = req.getParameter("action");
        if (action == null) {
            LOG.error("No action specified in the 'action' parameter");
            return;
        }

        String mainClass = CodeGenerator.generateCode(jobGraph);
        Map<String, String> clazzes = CodeGenerator.getComponentSources(jobGraph);

        switch (action) {
            case "deploy":
                LOG.debug("Starting deployment");
                deploymentInterface.generateProjectJAR(mainClass, clazzes, false);
                break;
            case "download_sources":
                LOG.debug("Starting download source");
                startZipDownload(resp, mainClass, clazzes);
                break;
            case "download_jar":
                LOG.debug("Starting download jar");
                startJarDownload(resp, mainClass, clazzes);
                break;
            default:
                LOG.debug("No action specified");
        }
    }

    /**
     * Starts the ZIP download
     *
     * @param resp       The response object
     * @param entryClass The entry class
     * @param classez    The classes
     */
    private void startZipDownload(HttpServletResponse resp, String entryClass, Map<String, String> classez) {
        InputStream inputStream = deploymentInterface.getZipSource(entryClass, classez);

        resp.setContentType("application/zip, application/octet-stream");
        resp.setHeader("Content-disposition", "attachment; filename=FlinkJobArchive.zip");

        startDownload(resp, inputStream);
    }

    /**
     * Starts the Jar download
     *
     * @param resp The response object
     */
    private void startJarDownload(HttpServletResponse resp, String entryClass, Map<String, String> clazzes) {
        deploymentInterface.generateProjectJAR(entryClass, clazzes, false);

        InputStream inputStream = deploymentInterface.getJarStream(entryClass, clazzes);

        resp.setContentType("application/java-archive");
        resp.setHeader("Content-disposition", "attachment; filename=FlinkJob.jar");

        startDownload(resp, inputStream);
    }

    /**
     * Starts the file download for the given inputStream
     *
     * @param resp        The response object
     * @param inputStream The input stream to send to the client
     */
    private void startDownload(HttpServletResponse resp, InputStream inputStream) {

        if (inputStream == null) {
            LOG.error("InputStream is null - exiting execution");
            return;
        }

        try {
            OutputStream out = resp.getOutputStream();
            byte[] buffer = new byte[4096];
            while ((inputStream.read(buffer)) > 0) {
                out.write(buffer);
                buffer = new byte[4096];
            }
            inputStream.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}