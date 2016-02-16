package org.tuberlin.de.server.controller;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/ControllerWebSocket")
public class SubmitControllerServlet extends WebSocketServlet{

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.register(FlinkWebSocket.class);
    }

}
