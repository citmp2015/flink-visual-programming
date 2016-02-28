package org.tuberlin.de.deployment;

import org.easymock.EasyMock;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.junit.Test;
import org.tuberlin.de.common.model.Constants;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Fabian on 28.02.2016.
 */
public class DeploymentImplementationTest {

    DeploymentImplementation deployment = (DeploymentImplementation) DeploymentImplementation.getInstance();
    String uuid = UUID.randomUUID().toString();

    @Test
    public void testCreateTemporaryProjectFolder() throws Exception {
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.isOpen()).andReturn(true);
        RemoteEndpoint remoteEndpoint = EasyMock.createMock(RemoteEndpoint.class);
        EasyMock.expect(session.getRemote()).andReturn(remoteEndpoint);
        remoteEndpoint.sendString(EasyMock.anyString());
        EasyMock.expectLastCall();

        File projectFolder = deployment.createTemporaryProjectFolder(session, uuid);
        assertEquals(projectFolder.getName(), "FlinkProject");
        assertEquals(projectFolder.getParentFile().getName(), uuid);
        assertTrue(contains(projectFolder.listFiles(), "src"));
        assertTrue(contains(projectFolder.listFiles(), "pom.xml"));

        deployment.cleanUp(projectFolder);

    }

    @Test
    public void testZipSource() throws Exception {
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.isOpen()).andReturn(true);
        RemoteEndpoint remoteEndpoint = EasyMock.createMock(RemoteEndpoint.class);
        EasyMock.expect(session.getRemote()).andReturn(remoteEndpoint);
        remoteEndpoint.sendString(EasyMock.anyString());
        EasyMock.expectLastCall().anyTimes();


    }

    private boolean contains(File[] files, String fileName){
        for(File file: files){
            if(file.getName().equals(fileName))
                return true;
        }
        return false;
    }

}