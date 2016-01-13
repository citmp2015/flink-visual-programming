package org.tuberlin.de.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tuberlin.de.common.model.BackendControllerImpl;
import org.tuberlin.de.common.model.interfaces.BackendController;
import org.tuberlin.de.common.model.interfaces.JobGraph;
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
 * Controller that is invoked when clicking on any of the following buttons:
 * <p>
 * 1.  "Deploy and Run"
 * 2.  "Download Java Project"
 * 3.  "Download JAR File"
 * </p>
 *
 * The information which button was pressed is stored into the POST parameter "action".
 * This Controller is currently only a Dummy.
 * In future this Controller should kick-off the following tasks:
 * <p>
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        BackendController backendController = new BackendControllerImpl();
        String json = req.getParameter("graph");
        JobGraph jobGraph;

        try {
            jobGraph = backendController.getJobGraph(json);
        } catch (Exception e){
            LOG.error("Error in construction jobGraph.", e);
            return;
        }


        String action = req.getParameter("action");

        if(action.equals("deploy")){
            LOG.debug("Starting deployment");

        } else if (action.equals("download_source")){
            LOG.debug("Starting download source");

        } else if (action.equals("download_jar")){
            LOG.debug("Starting download jar");
        }




    }

}