package org.tuberlin.de.server.controller;

import org.easymock.EasyMock;
import org.eclipse.jetty.websocket.api.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.tuberlin.de.deployment.DeploymentImplementation;
import org.tuberlin.de.deployment.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by Fabian on 28.02.2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DeploymentImplementation.class, JettyWebSocket.class, ServletUtil.class})
public class GraphJarControllerTest {

    @Test
    public void testDoGet() throws Exception {
        String uuid = "12345";
        DeploymentImplementation deployment = EasyMock.createMock(DeploymentImplementation.class);
        PowerMock.mockStatic(DeploymentImplementation.class);
        EasyMock.expect(DeploymentImplementation.getInstance()).andReturn(deployment);

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        EasyMock.expect(request.getPathInfo()).andReturn(uuid).times(2);
        EasyMock.expect(request.getRemoteAddr()).andReturn("localhost").times(2);

        Session session = EasyMock.createMock(Session.class);
        PowerMock.mockStatic(JettyWebSocket.class);
        EasyMock.expect(JettyWebSocket.getSession("localhost")).andReturn(session);

        InputStream jarStream = EasyMock.createMock(InputStream.class);
        EasyMock.expect(deployment.getJarStream(session, uuid)).andReturn(jarStream);

        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        response.setContentType("application/java-archive");
        EasyMock.expectLastCall();
        response.setHeader("Content-Disposition", "attachment; filename=\"FlinkJob.jar\"");
        EasyMock.expectLastCall();

        PowerMock.mockStatic(ServletUtil.class);
        ServletUtil.startDownload(response, jarStream);
        EasyMock.expectLastCall();
    }
}