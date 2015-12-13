package org.tuberlin.de.server.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.anyObject;
import static org.powermock.api.easymock.PowerMock.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Unit test for the {@link DownloadJavaController} class that controls that a file
 * is written into the response
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ HttpServletRequest.class, HttpServletResponse.class, DownloadJavaController.class})
public class DownloadJavaControllerTest {

  @Test
  public void testDownloadContent() throws Exception {
    HttpServletRequest request = createMock(HttpServletRequest.class);
    HttpServletResponse response = createMock(HttpServletResponse.class);
    ServletOutputStream stream = createMock(ServletOutputStream.class);

    expect(request.getParameter("json")).andReturn("mock json");

    //prepare response
    response.setContentType("text/x-java-source,java");
    expectLastCall();
    response.setHeader("Content-disposition","attachment; filename=Wordcount.java");
    expectLastCall();
    expect(response.getOutputStream()).andReturn(stream);

    //write content (bytearray)
    stream.write((byte[]) anyObject());
    expectLastCall().anyTimes();
    stream.flush();
    expectLastCall();

    replayAll();

    DownloadJavaController controller = new DownloadJavaController();
    controller.doPost(request, response);

    verifyAll();

  }
}
