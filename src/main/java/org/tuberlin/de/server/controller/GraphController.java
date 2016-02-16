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
 * <p/>
 * The information which button was pressed is stored into the POST parameter "action".
 * In future this Controller should kick-off the following tasks:
 * <p/>
 * 1. Code generation from the graph
 * 2. Insert code into Maven project
 * 3. Compile Maven project into Jar
 * 4. Deploy Jar to remote Flink maschine
 * <p/>
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

        LOG.debug("DoPut called");

        String body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        JSONObject bodyObject;

        try {
            bodyObject = new JSONObject(body);
        } catch (JSONException e) {
            LOG.debug("Request did not contain data");
            return;
        }

        if (!bodyObject.has("graph")) {
            LOG.debug("Request did not contain data for parameter graph");
            return;
        } else {
            LOG.debug("Graph: " + bodyObject.getJSONObject("graph").toString());
        }

        JobGraph jobGraph;
        try {
            jobGraph = backendController.getJobGraph(bodyObject.getJSONObject("graph"));
        } catch (Exception e) {
            LOG.error("Error in construction jobGraph.", e);
            return;
        }

        String mainClass = CodeGenerator.generateCode(jobGraph);
        Map<String, String> clazzes = CodeGenerator.getComponentSources(jobGraph);

        Session clientSession = JettyWebSocket.getSession(req.getRemoteAddr());

        if (clientSession == null) {
            LOG.debug("Could not find Websocket for current client");
        }

        String uuid = UUID.randomUUID().toString();

        JSONObject response = new JSONObject();
        response.put("uuid", uuid);
        response.put("status", "submitted");
        response.put("log", "");

        new Thread(() -> {
            deploymentInterface.generateProjectDirectory(clientSession, uuid, mainClass, clazzes);
        }).start();

        startDownload(resp, response);
    }

    /**
     * Starts the json download for a given JSON object
     * @param resp The response object
     * @param jsonObject The JSON object to be send
     */
    private void startDownload(HttpServletResponse resp, JSONObject jsonObject) {
        try {
            resp.getOutputStream().write(jsonObject.toString().getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Failed to send JSON object: " + jsonObject);
        }
    }
}