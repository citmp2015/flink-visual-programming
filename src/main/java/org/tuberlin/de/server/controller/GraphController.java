package org.tuberlin.de.server.controller;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tuberlin.de.common.codegenerator.CodeGenerator;
import org.tuberlin.de.common.model.BackendControllerImpl;
import org.tuberlin.de.common.model.interfaces.BackendController;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.deployment.DeploymentImplementation;
import org.tuberlin.de.deployment.DeploymentInterface;
import org.tuberlin.de.deployment.util.ServletUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller that is invoked when clicking on any of the following buttons:
 * <p>
 * 1.  "Deploy and Run"
 * 2.  "Download Java Project"
 * 3.  "Download JAR File"
 * </p>
 * The controller does not handle the deployment nor the downloads. It is only the first step in a series of calls.
 * This controller only creates the project folder by parsing the JSON content and creates a UUID that identifies this
 * project folder. Using this UUID the client can execute in a next step the desired action (deployment or download..).
 * This Controller handles a POST event and awaits a JSON content that describes the graph.
 */
@WebServlet(urlPatterns = {"/graph"})
public class GraphController extends HttpServlet {

    private static final long serialVersionUID = 23523652345L;
    private static final Logger LOG = LoggerFactory.getLogger(GraphController.class);

    private DeploymentInterface deploymentInterface;
    private BackendController backendController;

    public GraphController() {
        LOG.debug("Init");
        deploymentInterface = DeploymentImplementation.getInstance();
        backendController = BackendControllerImpl.getInstance();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        JSONObject jobGraphJSON;

        try {
            jobGraphJSON = new JSONObject(body);
        } catch (JSONException e) {
            LOG.debug("Request did not contain data");
            return;
        }

        String uuid = UUID.randomUUID().toString();

        Session clientSession = JettyWebSocket.getSession(req.getRemoteAddr());

        if (clientSession == null) {
            LOG.debug("Could not find Websocket for current client");
        }

        sendToWebsocket(clientSession, "graph:" + uuid + ":generationStarted");

        JobGraph jobGraph;
        try {
            jobGraph = backendController.getJobGraph(jobGraphJSON);
        } catch (Exception e) {
            sendToWebsocket(clientSession, "graph:" + uuid + ":generationError");
            return;
        }

        sendToWebsocket(clientSession, "graph:" + uuid + ":generationSucceeded");

        String mainClass = CodeGenerator.generateCode(jobGraph);
        Map<String, String> clazzes = CodeGenerator.getComponentSources(jobGraph);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("uuid", uuid);
        jsonResponse.put("status", "submitted");
        jsonResponse.put("log", "");

        new Thread(() -> {
            deploymentInterface.generateProjectDirectory(clientSession, uuid, mainClass, clazzes);
        }).start();

        ServletUtil.sendJson(resp, jsonResponse);
    }

    private void sendToWebsocket(Session clientSession, String message) {
        if (clientSession != null) {
            try {
                clientSession.getRemote().sendString(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOG.debug(message);
    }
}