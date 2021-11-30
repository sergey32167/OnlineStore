package com.issoft.training.stores.pars;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DocPars {

    private Document doc;

    public void DocParse(String filepath) {
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

    public String getOrderingValueByTag(String tagName) {
        Node modelNote = doc.getFirstChild();
//        System.out.println(modelNote);
        NodeList modelChilds = modelNote.getChildNodes();

        for (int i = 0; i < modelChilds.getLength(); i++) {

            if (modelChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
//            System.out.println(modelChilds.item(i).getNodeName());
            if (tagName.equals(modelChilds.item(i).getNodeName())) {
                return modelChilds.item(i).getTextContent();
            }
        }
        return null;
    }
}
