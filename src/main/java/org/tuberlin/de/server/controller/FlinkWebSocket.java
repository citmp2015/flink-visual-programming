package org.tuberlin.de.server.controller;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ControllerWebSocket")
public class FlinkWebSocket {

    private static final Logger LOG = LoggerFactory.getLogger(FlinkWebSocket.class);

    private static Map<String, Session> hostSessionMapping;

    public FlinkWebSocket() {
        hostSessionMapping = new HashMap<>();
    }

    public static Session getSession(String host) {
        if (hostSessionMapping.containsKey(host)) {
            return hostSessionMapping.get(host);
        } else {
            return null;
        }
    }

    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        LOG.debug(session.getRemoteAddress().getHostString() + " connected!");
        LOG.debug(session.getRemoteAddress().getAddress() + " connected!");
        LOG.debug(session.getRemoteAddress().getHostName() + " connected!");
        hostSessionMapping.put(session.getRemoteAddress().getHostString(), session);
    }

    @OnWebSocketMessage
    public void onText(Session session, String message) throws IOException {
        LOG.debug("Message received:" + message);
        if (session.isOpen()) {
            switch (message) {
                case "Start":
                    LOG.debug("Received Start");
                    break;
                default:
                    LOG.debug("Unrecognized command");
            }

            String response = message.toUpperCase();
            session.getRemote().sendString(response);
        }
    }

    @OnWebSocketClose
    public void onClose(Session session, int status, String reason) {
        LOG.debug(session.getRemoteAddress().getHostString() + " closed!");
    }
}
