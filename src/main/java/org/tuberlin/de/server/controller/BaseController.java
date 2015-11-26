package org.tuberlin.de.server.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Fabian on 25.11.2015.
 */
public class BaseController extends HttpServlet {

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
		  ServletException, IOException {
    resp.sendRedirect("/pages/index.html");
  }
}
