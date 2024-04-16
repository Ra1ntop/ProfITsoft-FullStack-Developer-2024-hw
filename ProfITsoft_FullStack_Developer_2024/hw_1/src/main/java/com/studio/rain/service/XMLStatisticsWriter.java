package com.studio.rain.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Map;


/**
    fawdawd
 dawdwad
 **/
public class XMLStatisticsWriter {
    public void writeStatisticsToXML(Map<String, Integer> statistics, String outputPath, String atribut) throws Exception {

        outputPath=outputPath+"statistics_by_"+atribut+".xml";
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("statistics");
        doc.appendChild(rootElement);

        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            String value = entry.getKey();
            int count = entry.getValue();

            Element itemElement = doc.createElement("item");
            rootElement.appendChild(itemElement);

            Element valueElement = doc.createElement("value");
            valueElement.appendChild(doc.createTextNode(value));
            itemElement.appendChild(valueElement);

            Element countElement = doc.createElement("count");
            countElement.appendChild(doc.createTextNode(String.valueOf(count)));
            itemElement.appendChild(countElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(outputPath));
        transformer.transform(source, result);

        System.out.println("Statistic was saved in: " + outputPath);
    }
}
