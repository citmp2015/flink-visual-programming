package org.tuberlin.de.server.controller;

import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tuberlin.de.deployment.DeploymentImplementation;
import org.tuberlin.de.deployment.DeploymentInterface;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/graph/jar/*"})
public class GraphJarController extends HttpServlet {

    private static final long serialVersionUID = 23523652345L;
    private static final Logger LOG = LoggerFactory.getLogger(GraphZipController.class);

    private DeploymentInterface deploymentInterface;

    public GraphJarController() {
        LOG.debug("Init " + GraphController.class.getSimpleName());
        deploymentInterface = DeploymentImplementation.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Request: " + req.getPathInfo());
    }

    /**
     * Starts the ZIP download
     *
     * @param clientSession The Websocket connection
     * @param resp       The response object
     * @param entryClass The entry class
     * @param classez    The classes
     */
    private void startZipDownload(Session clientSession, HttpServletResponse resp, String entryClass, Map<String, String> classez) {
        InputStream inputStream = deploymentInterface.getZipSource(clientSession, "Bla");

        resp.setContentType("application/zip");
        resp.setHeader("Content-Disposition", "attachment; filename=\"FlinkJobArchive.zip\"");

        startDownload(resp, inputStream);
    }

    /**
     * Starts the Jar download
     *
     * @param clientSession The Websocket connection
     * @param resp The response object
     */
    private void startJarDownload(Session clientSession, HttpServletResponse resp, String uuid) {
//        deploymentInterface.generateProjectDirectory(clientSession, uuid, entryClass, clazzes);

        InputStream inputStream = deploymentInterface.getJarStream(clientSession, "bla");

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