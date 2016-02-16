package org.tuberlin.de.server.controller;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tuberlin.de.deployment.DeploymentImplementation;
import org.tuberlin.de.deployment.DeploymentInterface;
import org.tuberlin.de.deployment.util.ServletUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/graph/deploy/*"})
public class GraphDeployController extends HttpServlet {

    private static final long serialVersionUID = 23523652345L;
    private static final Logger LOG = LoggerFactory.getLogger(GraphDeployController.class);

    private DeploymentInterface deploymentInterface;

    public GraphDeployController() {
        LOG.debug("Init");
        deploymentInterface = DeploymentImplementation.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Request: " + req.getPathInfo().substring(1) + " from " + req.getRemoteAddr());
        String uuid = req.getPathInfo().substring(1);

        Session clientSession = JettyWebSocket.getSession(req.getRemoteAddr());

        JSONObject response = new JSONObject();
        response.put("uuid", uuid);
        response.put("status", "builded");
        response.put("log", "");

        new Thread(() -> {
            deploymentInterface.deploy(clientSession, uuid);
        }).start();

        ServletUtil.sendJson(resp, response);
    }
}