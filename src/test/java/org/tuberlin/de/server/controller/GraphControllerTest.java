package org.tuberlin.de.server.controller;

import javassist.compiler.CodeGen;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.tuberlin.de.common.codegenerator.CodeGenerator;
import org.tuberlin.de.common.model.BackendControllerImpl;
import org.tuberlin.de.common.model.interfaces.BackendController;
import org.tuberlin.de.common.model.interfaces.JobGraph;
import org.tuberlin.de.deployment.DeploymentImplementation;
import org.tuberlin.de.deployment.DeploymentInterface;
import org.tuberlin.de.deployment.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by Fabian on 24.02.2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({JettyWebSocket.class, DeploymentImplementation.class, GraphController.class, JSONObject.class, BackendControllerImpl.class, UUID.class, JettyWebSocket.class, CodeGenerator.class, ServletUtil.class})
public class GraphControllerTest {

    @Test
    public void testDoPut() throws Exception {
        String exampleJsonGraph = "example Json Graph";
        UUID fullUUID = UUID.randomUUID();
        String uuid = fullUUID.toString();
        String mainClass = "mainClass";
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("uuid", uuid);
        jsonResponse.put("status", "submitted");
        jsonResponse.put("log", "");
        JSONObject internalJSONObject = new JSONObject();

        DeploymentImplementation deployment = EasyMock.createMock(DeploymentImplementation.class);
        PowerMock.mockStatic(DeploymentImplementation.class);
        EasyMock.expect(DeploymentImplementation.getInstance()).andReturn(deployment);

        BackendController backendController = EasyMock.createMock(BackendControllerImpl.class);
        PowerMock.mockStatic(BackendControllerImpl.class);
        EasyMock.expect(BackendControllerImpl.getInstance()).andReturn(backendController);

        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        BufferedReader bufferedReader = EasyMock.createMock(BufferedReader.class);
        EasyMock.expect(request.getReader()).andReturn(bufferedReader);
        Stream<String> lines = EasyMock.createMock(Stream.class);
        EasyMock.expect(bufferedReader.lines()).andReturn(lines);
        EasyMock.expect(lines.reduce(EasyMock.eq(""), EasyMock.anyObject(BinaryOperator.class))).andReturn(exampleJsonGraph);
        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);

        JSONObject jsonObject = EasyMock.createMock(JSONObject.class);
        PowerMock.expectNew(JSONObject.class,  new Class<?>[] { String.class }, exampleJsonGraph).andReturn(jsonObject);
        PowerMock.mockStatic(UUID.class);
        EasyMock.expect(UUID.randomUUID()).andReturn(fullUUID);

        PowerMock.mockStatic(JettyWebSocket.class);
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(request.getRemoteAddr()).andReturn("localhost");
        EasyMock.expect(JettyWebSocket.getSession("localhost")).andReturn(session);

        RemoteEndpoint remoteEndpoint = EasyMock.createMock(RemoteEndpoint.class);
        EasyMock.expect(session.getRemote()).andReturn(remoteEndpoint);
        remoteEndpoint.sendString("graph:" + uuid + ":generationStarted");
        EasyMock.expectLastCall();

        JobGraph jobGraph = EasyMock.createMock(JobGraph.class);
        EasyMock.expect(backendController.getJobGraph(jsonObject)).andReturn(jobGraph);

        EasyMock.expect(session.getRemote()).andReturn(remoteEndpoint);
        remoteEndpoint.sendString("graph:" + uuid + ":generationSucceeded");
        EasyMock.expectLastCall();

        PowerMock.mockStatic(CodeGenerator.class);
        EasyMock.expect(CodeGenerator.generateCode(jobGraph)).andReturn(mainClass);
        Map<String, String> clazzes = new HashMap<>();
        EasyMock.expect(CodeGenerator.getComponentSources(jobGraph)).andReturn(clazzes);

        PowerMock.expectNew(JSONObject.class).andReturn(internalJSONObject);

        deployment.generateProjectDirectory(session, uuid, mainClass, clazzes);
        EasyMock.expectLastCall();


        PowerMock.mockStatic(ServletUtil.class);
        ServletUtil.sendJson(EasyMock.anyObject(HttpServletResponse.class), EasyMock.anyObject(JSONObject.class));
        EasyMock.expectLastCall();

        PowerMock.replay(request, backendController, deployment, lines, bufferedReader, jsonObject, session, response, JSONObject.class, JettyWebSocket.class, DeploymentImplementation.class, BackendControllerImpl.class, UUID.class, JettyWebSocket.class, CodeGenerator.class, ServletUtil.class);
        GraphController graphController = new GraphController();
        graphController.doPut(request, response);
        assertEquals(jsonResponse.toString(), internalJSONObject.toString());
        PowerMock.verify(request, backendController, deployment, lines, bufferedReader, jsonObject, session, response, JSONObject.class, JettyWebSocket.class, DeploymentImplementation.class, BackendControllerImpl.class, UUID.class, JettyWebSocket.class, CodeGenerator.class, ServletUtil.class);

    }

}