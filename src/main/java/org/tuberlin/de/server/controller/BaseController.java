package org.tuberlin.de.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base controller for testing purposes. Demonstrates basic functionality and logging.
 */
@WebServlet(urlPatterns ={ "/BaseController" })
public class BaseController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @Override
    public void init() throws ServletException {
        LOG.debug("Init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        LOG.debug("DoGet");

        resp.sendRedirect("/index.html");
    }

    @Override
    public void destroy() {
        LOG.debug("Destroy");
    }
}