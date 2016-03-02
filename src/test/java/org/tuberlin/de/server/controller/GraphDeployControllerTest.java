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

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import static org.junit.Assert.*;
/**
 * Created by Fabian on 23.02.2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({JettyWebSocket.class, DeploymentImplementation.class})
public class GraphDeployControllerTest {

    @Test
    public void testDoGet() throws Exception {
        String uuid = "1234";

        DeploymentImplementation deployment = EasyMock.createMock(DeploymentImplementation.class);
        PowerMock.mockStatic(DeploymentImplementation.class);
        EasyMock.expect(DeploymentImplementation.getInstance()).andReturn(deployment);

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        EasyMock.expect(request.getPathInfo()).andReturn(uuid).times(2);
        EasyMock.expect(request.getRemoteAddr()).andReturn("localhost").times(2);

        Session session = EasyMock.createMock(Session.class);
        PowerMock.mockStatic(JettyWebSocket.class);
        EasyMock.expect(JettyWebSocket.getSession("localhost")).andReturn(session);

        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        ServletOutputStream outputStream = new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
            }

            @Override
            public void write(int b) throws IOException {
            }

            @Override
            public void write(byte[] b) throws IOException{
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("uuid", uuid.substring(1));
                jsonResponse.put("status", "builded");
                jsonResponse.put("log", "");
                String expectedResponse = jsonResponse.toString();
                String result = new String(b);
                assertEquals(result, expectedResponse);
            }
        };
        EasyMock.expect(response.getOutputStream()).andReturn(outputStream);

        PowerMock.replay(request, session, response, JettyWebSocket.class, DeploymentImplementation.class);
        GraphDeployController controller = new GraphDeployController();
        controller.doGet(request, response);
        PowerMock.verify(request, session, response, JettyWebSocket.class, DeploymentImplementation.class);

    }
}