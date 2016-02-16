package org.tuberlin.de.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
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

    @OnOpen
    public void onConnect(Session session) throws IOException {
        LOG.debug("New client: " + session.getId() + " connected!");
    }

    @OnMessage
    public void onText(String message, Session session) throws IOException {
        LOG.debug("Message received:" + message);
        if (session.isOpen()) {
            String response = message.toUpperCase();
            session.getBasicRemote().sendText(response);
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        LOG.debug(session.getId() + " closed!" + reason.getReasonPhrase());
    }
}
