package com.issoft.training.stores.pars;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class DocPars {
    private Document doc;
    public final static String FILEPATH = "config.xml";

    public void parseFile() {
        File file = new File(FILEPATH);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            this.doc = dbf.newDocumentBuilder().parse(file);
        } catch (SAXException e) {
            throw new RuntimeException("SAX error or error warning");
        } catch (IOException e) {
            throw new RuntimeException("These are input or output exceptions ");
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Serious configuration error. ");
        }
    }

    public Map<String, String> getDateSort() {
        Map<String, String> tagsNameValue = new TreeMap<>();
        Node modelNote = doc.getFirstChild();
        NodeList modelChilds = modelNote.getChildNodes();

        for (int i = 0; i < modelChilds.getLength(); i++) {
            if (modelChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            tagsNameValue.put(modelChilds.item(i).getNodeName(), modelChilds.item(i).getTextContent());
        }
        return tagsNameValue;
    }

    public Map<String, String> getPriceSort() {
        Map<String, String> tagsNameValue = new TreeMap<>();
        Node modelNote = doc.getFirstChild();
        NodeList modelChilds = modelNote.getChildNodes();

        for (int i = 0; i < modelChilds.getLength(); i++) {
            if (modelChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (modelChilds.item(i).getNodeName().equalsIgnoreCase("price")) {
                tagsNameValue.put(modelChilds.item(i).getNodeName(), modelChilds.item(i).getTextContent());
            }
        }
        return tagsNameValue;
    }
}
