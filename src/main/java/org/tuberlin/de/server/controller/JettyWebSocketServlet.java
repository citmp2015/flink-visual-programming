package org.tuberlin.de.server.controller;

import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/ControllerWebSocket")
public class JettyWebSocketServlet extends org.eclipse.jetty.websocket.servlet.WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.register(JettyWebSocket.class);
    }
}
