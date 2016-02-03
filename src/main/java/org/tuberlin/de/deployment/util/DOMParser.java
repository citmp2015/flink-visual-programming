package org.tuberlin.de.deployment.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Used to replaces text contents in tags in an xml file.
 * Specifically in this project, it replaces the artifactID and the mainClass in the flink-skeleton pom.xml.
 */
public class DOMParser {

    /**
     * Replaces every node value in the XML file specified by file with the mapping from NameValuePairs
     *
     * @param file           The XML file
     * @param nameValuePairs The mapping from node name to new value
     */
    public static void replaceXMLValues(File file, Map<String, String> nameValuePairs) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            for (Map.Entry<String, String> entry : nameValuePairs.entrySet()) {
                changeXMLNodeContent(doc, entry.getKey(), entry.getValue());
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException | IOException | SAXException pce) {
            pce.printStackTrace();
        }
    }

    private static void changeXMLNodeContent(Document doc, String nodeName, String value) {

        // Get the staff element by tag nodeName directly
        Node node = doc.getElementsByTagName(nodeName).item(0);
        node.setTextContent(value);
    }
}
