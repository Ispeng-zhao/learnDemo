package com.myself.utils.linux.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 小赵
 * @Date: 2021/03/29/9:31
 * @Description:
 */
public class XmlUtils {
    public static Boolean macConfig(String mac) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        String decodeXmlPath =  "snoop.xml";
        /*
        创建解析器，解析XML文档
         */
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            /*
            使用dom解析xml文件
             */
            Document doc = db.parse(new File("").getAbsolutePath()+"/common/src/main/resource/xml/"+decodeXmlPath);

            NodeList endpoints = doc.getElementsByTagName("endpoint");
            for (int i = 0; i < endpoints.getLength(); i++) {
                Element endpoint = (Element) endpoints.item(i);
                String nodeValue = endpoint.getFirstChild().getNodeValue().substring(0, 6) + mac;
                endpoint.getFirstChild().setNodeValue(nodeValue);
            }

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(doc), new StreamResult(new File(decodeXmlPath)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }




}
