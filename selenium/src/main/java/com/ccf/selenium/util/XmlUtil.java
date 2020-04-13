package com.ccf.selenium.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * xml工具类.
 */
public class XmlUtil {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(XmlUtil.class);

    /**
     * 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象.
     * @param xmlPath
     * @return
     */
    public static Object getBean(String xmlPath){
        log.info("XmlUtil----getBean---------begin!"+xmlPath);
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File(xmlPath+"/"+"config.xml"));

            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode=nl.item(0).getFirstChild();
            String cName=classNode.getNodeValue();

            //通过类名生成实例对象并将其返回
            Class c=Class.forName(cName);
            Object obj=c.newInstance();
            log.info("XmlUtil----getBean----------end!"+xmlPath);
            return obj;
        } catch(Exception e) {
            log.error("XmlUtil----getBean!"+xmlPath,e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 取xml文件整体信息.
     * @param xmlFile
     * @return
     */
    public static Document getXmlDoc(String xmlFile){
        log.info("XmlUtil----getXmlDoc---------begin!"+xmlFile);
        Document doc = null;

        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            doc = builder.parse(new File(xmlFile));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            log.error("解析文件"+(xmlFile)+"失败! ParserConfigurationException ",e);
        } catch (SAXException e) {
            e.printStackTrace();
            log.error("解析文件"+(xmlFile)+"失败! SAXException ",e);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("解析文件"+(xmlFile)+"失败! IOException ",e);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("解析文件"+(xmlFile)+"失败! Exception ",e);
        }

        log.info("XmlUtil----getXmlDoc---------end!");
        return doc;
    }
}
