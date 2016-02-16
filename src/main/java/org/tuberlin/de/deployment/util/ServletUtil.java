package org.tuberlin.de.deployment.util;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

public class ServletUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ServletUtil.class);

    /**
     * Starts the file download for the given inputStream
     *
     * @param resp        The response object
     * @param inputStream The input stream to send to the client
     */
    public static void startDownload(HttpServletResponse resp, InputStream inputStream) {

        if (inputStream == null) {
            LOG.error("InputStream is null - exiting execution");
            return;
        }

        try {
            OutputStream out = resp.getOutputStream();
            byte[] buffer = new byte[4096];
            while ((inputStream.read(buffer)) > 0) {
                out.write(buffer);
                buffer = new byte[4096];
            }
            inputStream.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the json download for a given JSON object
     *
     * @param resp       The response object
     * @param jsonObject The JSON object to be send
     */
    public static void sendJson(HttpServletResponse resp, JSONObject jsonObject) {
        try {
            resp.getOutputStream().write(jsonObject.toString().getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Failed to send JSON object: " + jsonObject);
        }
    }
}
