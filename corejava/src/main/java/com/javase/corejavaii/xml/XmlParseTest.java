package com.javase.corejavaii.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by root on 12/26/16.
 */
public class XmlParseTest {
    public static void main(String[] args) {
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            File f = new File("src/main/java/logback.xml");
            Document document = builder.parse(f);
            Element element = document.getDocumentElement();
            traversal(element);
            String tag = element.getTagName();
            System.out.println("tag : " + tag);
            // traversalOne(element);
            // System.out.println(element.getAttribute("root"));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void traversalOne(Element root) {
        NodeList children = root.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            Node child = children.item(i);
            if(child instanceof Element) {
                Element ele = (Element)child;
                System.out.println("tag " + ele.getTagName());
            }

        }
    }

    public static void traversal(Element root) {
        for(Node childNode = root.getFirstChild();
                childNode != null;
                childNode = childNode.getNextSibling()) {
            if(childNode instanceof Element) {
                System.out.println(childNode.getNodeName());
                getAttribute((Element)childNode);
            } else {
                // System.out.println(childNode.getNodeName());
            }

        }
    }

    public static void getAttribute(Element element) {
        NamedNodeMap attributes = element.getAttributes();
        for(int i=0; i<attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            String name = attribute.getNodeName();
            String value = attribute.getNodeValue();
            System.out.println("name -> value " + name + " -> " + value);
        }
    }
}

