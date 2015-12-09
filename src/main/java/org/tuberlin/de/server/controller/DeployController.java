package org.tuberlin.de.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller that is invoked when clicking on "Deploy and Run" Button.
 * This Controller is currently only a Dummy.
 * In future this Controller should kick-off the following tasks:
 * 	1. Code generation from the graph
 * 	2. Insert code into Maven project
 * 	3. Compile Maven project into Jar
 * 	4. Deploy Jar to remote Flink maschine
 *
 * This Controller handles a POST event and awaits a JSON content that describes
 * the graph.
 */
//@WebServlet(urlPatterns ={ "/deploy_and_run" })
public class DeployController extends HttpServlet {

  private static final long serialVersionUID = 23523652345L;
  private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
		  ServletException, IOException {
    //TODO: talk to the Front-End group about the parameter name
    String json = req.getParameter("graph");

    LOG.info("Post received with the following JSON" + json);

    //TODO: kickoff code generation, compilation and deployment
  }
}
