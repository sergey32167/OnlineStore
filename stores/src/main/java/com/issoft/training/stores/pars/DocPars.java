package com.issoft.training.stores.pars;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocPars {

    private Document doc;

    public void docParse(String filepath) {
        File file = new File(filepath);
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

    public List<String> getTagsValue() {
        List<String> tagsValue = new ArrayList<>();
        Node modelNote = doc.getFirstChild();
        NodeList modelChilds = modelNote.getChildNodes();

        for (int i = 0; i < modelChilds.getLength(); i++) {
            if (modelChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            tagsValue.add(modelChilds.item(i).getTextContent());
        }
        return tagsValue;
    }

    public List<String> getTagValue(String tagName) {
        List<String> tagValue = new ArrayList<>();
        Node modelNote = doc.getFirstChild();
        NodeList modelChilds = modelNote.getChildNodes();

        for (int i = 0; i < modelChilds.getLength(); i++) {
            if (modelChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (tagName.equals(modelChilds.item(i).getNodeName()))
                tagValue.add(modelChilds.item(i).getTextContent());
        }
        return tagValue;
    }

    public List<String> getTagsName() {
        List<String> tagsName = new ArrayList<>();
        Node modelNote = doc.getFirstChild();
        NodeList modelChilds = modelNote.getChildNodes();

        for (int i = 0; i < modelChilds.getLength(); i++) {
            if (modelChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            tagsName.add(modelChilds.item(i).getNodeName());
        }
        return tagsName;
    }

    public List<String> getTagName(String tagName) {
        List<String> oneTagName = new ArrayList<>();
        Node modelNote = doc.getFirstChild();
        NodeList modelChilds = modelNote.getChildNodes();

        for (int i = 0; i < modelChilds.getLength(); i++) {
            if (modelChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (tagName.equals(modelChilds.item(i).getNodeName()))
                oneTagName.add(modelChilds.item(i).getNodeName());
        }
        return oneTagName;
    }
}
