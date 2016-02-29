package org.tuberlin.de.server.controller;

import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tuberlin.de.deployment.DeploymentImplementation;
import org.tuberlin.de.deployment.DeploymentInterface;
import org.tuberlin.de.deployment.util.ServletUtil;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class compiles the project and downloads the generated JAR. Before this URL is called the /graph URL must be
 * called that will create a project folder and returns a UUID that identifies that particular project folder.
 * When the GET method of this class is called, one need the UUID from the project folder to be included in the request
 * body. Then this servlet compiles the application and starts the download of the JAR file.
 */

@WebServlet(urlPatterns = {"/graph/jar/*"})
public class GraphJarController extends HttpServlet {

    private static final long serialVersionUID = 23523652345L;
    private static final Logger LOG = LoggerFactory.getLogger(GraphJarController.class);

    private DeploymentInterface deploymentInterface;

    public GraphJarController() {
        LOG.debug("Init");
        deploymentInterface = DeploymentImplementation.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Request: " + req.getPathInfo().substring(1) + " + Addr: " + req.getRemoteAddr());
        String uuid = req.getPathInfo().substring(1);

        Session clientSession = JettyWebSocket.getSession(req.getRemoteAddr());

        InputStream inputStream = deploymentInterface.getJarStream(clientSession, uuid);

        if (inputStream == null) {
            return;
        }

        resp.setContentType("application/java-archive");
        resp.setHeader("Content-Disposition", "attachment; filename=\"FlinkJob.jar\"");

        ServletUtil.startDownload(resp, inputStream);
    }
}
