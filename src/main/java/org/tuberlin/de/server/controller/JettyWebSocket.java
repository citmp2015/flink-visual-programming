package org.tuberlin.de.server.controller;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebSocket
public class JettyWebSocket {

    private static final Logger LOG = LoggerFactory.getLogger(JettyWebSocket.class);

    private static Map<String, Session> hostSessionMapping;

    public JettyWebSocket() {
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
        hostSessionMapping.put(session.getRemoteAddress().getHostString(), session);
    }

    @OnWebSocketMessage
    public void onText(Session session, String message) throws IOException {
        LOG.debug("Message received: " + message);
        if (session.isOpen()) {
            String response = message.toUpperCase();
            session.getRemote().sendString(response);
        }
    }

    @OnWebSocketClose
    public void onClose(Session session, int status, String reason) {
        LOG.debug(session.getRemoteAddress().getHostString() + " closed! " + reason);
        hostSessionMapping.remove(session.getRemoteAddress().getHostString());
    }
}