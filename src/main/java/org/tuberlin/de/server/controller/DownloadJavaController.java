package org.tuberlin.de.server.controller;

import javassist.bytecode.ByteArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * This is a dummy class that delivers a example Wordcount file when
 * clicked on the button "Download Java Project". This controller expects
 * a parameter "json" that contains the json describing the graph.
 */
//@WebServlet(urlPatterns ={ "/download_java" })
public class DownloadJavaController extends HttpServlet {

  private static final long serialVersionUID = 23522353465L;
  private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
		  ServletException, IOException {
    String json = req.getParameter("json");
    //TODO: run code generation

    //currently a static mock. It will allways deliver the example file
    resp.setContentType("text/x-java-source,java");
    resp.setHeader("Content-disposition","attachment; filename=Wordcount.java");


    OutputStream out = resp.getOutputStream();
    InputStream in = getClass().getResourceAsStream("/Wordcount.java");
    byte[] buffer = new byte[4096];
    int length;
    while ((length = in.read(buffer)) > 0){
      out.write(buffer);
    }
    in.close();
    out.flush();
  }
}
