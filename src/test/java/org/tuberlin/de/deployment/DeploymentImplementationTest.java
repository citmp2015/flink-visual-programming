package org.tuberlin.de.deployment;

import org.easymock.EasyMock;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.*;

/**
 * Created by Fabian on 28.02.2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DeploymentImplementation.class, FileSystems.class})
public class DeploymentImplementationTest {

    String uuid = UUID.randomUUID().toString();

    @Test
    public void testCreateTemporaryProjectFolder() throws Exception {
        DeploymentImplementation deployment = (DeploymentImplementation) DeploymentImplementation.getInstance();

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
        DeploymentImplementation deployment = (DeploymentImplementation) DeploymentImplementation.getInstance();
        //DeploymentImplementation deploymentSpy = PowerMockito.spy(deployment);

        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.isOpen()).andReturn(true).anyTimes();
        RemoteEndpoint remoteEndpoint = EasyMock.createMock(RemoteEndpoint.class);
        EasyMock.expect(session.getRemote()).andReturn(remoteEndpoint).anyTimes();
        remoteEndpoint.sendString(EasyMock.anyString());
        EasyMock.expectLastCall().anyTimes();

        Path path = FileSystems
                .getDefault().getPath(
                        new File(getFileFromResource("test/FlinkProject/Wordcount.java"))
                        .getParentFile().getParentFile()
                        .getAbsolutePath());

        FileSystem fileSystems = EasyMock.createMock(FileSystem.class);
        PowerMock.mockStatic(FileSystems.class);
        EasyMock.expect(FileSystems.getDefault()).andReturn(fileSystems);
        EasyMock.expect(fileSystems.getPath(EasyMock.anyString())).andReturn(path);

        PowerMock.replay(session, remoteEndpoint, FileSystems.class, fileSystems);

        FileInputStream inputStream = (FileInputStream) deployment.getZipSource(session, uuid);

        File testFile = new File(getFileFromResource("test/compareZip/FlinkProject.zip"));

        ZipInputStream zinTestFile = new ZipInputStream(new FileInputStream(testFile));
        ZipEntry zipEntryTestFile = zinTestFile.getNextEntry();

        ZipInputStream zinResult = new ZipInputStream(inputStream);
        ZipEntry zipEntryResult = zinResult.getNextEntry();
        assertEquals(zipEntryResult.getName(), zipEntryTestFile.getName());
        assertEquals(zipEntryResult.getSize(), zipEntryTestFile.getSize());

        PowerMock.verify(session, remoteEndpoint, FileSystems.class, fileSystems);
    }

    private String getFileFromResource(String resourceNamePath) {
        return getClass()
                .getClassLoader()
                .getResource(resourceNamePath)
                .getFile();
    }

    private boolean contains(File[] files, String fileName){
        for(File file: files){
            if(file.getName().equals(fileName))
                return true;
        }
        return false;
    }

}