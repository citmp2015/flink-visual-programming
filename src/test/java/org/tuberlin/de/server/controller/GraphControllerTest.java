package org.tuberlin.de.server.controller;

import org.easymock.EasyMock;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.tuberlin.de.deployment.DeploymentImplementation;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by Fabian on 24.02.2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({JettyWebSocket.class, DeploymentImplementation.class, UUID.class, JettyWebSocket.class})
public class GraphControllerTest {

    @Test
    public void testDoPut() throws Exception {
        String exampleJsonGraph = "example Json Graph";
        String uuid = "12345";
        String mainClass = "mainClass";

        DeploymentImplementation deployment = EasyMock.createMock(DeploymentImplementation.class);
        PowerMock.mockStatic(DeploymentImplementation.class);
        EasyMock.expect(DeploymentImplementation.getInstance()).andReturn(deployment);

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        EasyMock.expect(request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual))
                .andReturn(exampleJsonGraph);

        JSONObject jsonObject = EasyMock.createMock(JSONObject.class);
        PowerMock.expectNew(JSONObject.class, exampleJsonGraph).andReturn(jsonObject);
        PowerMock.mockStatic(UUID.class);
        EasyMock.expect(UUID.randomUUID().toString()).andReturn(uuid);

        PowerMock.mockStatic(JettyWebSocket.class);
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(JettyWebSocket.getSession(request.getRemoteHost())).andReturn(session);
        session.getRemote().sendString("graph:" + uuid + ":generationStarted");
        EasyMock.expectLastCall();

    }

}