package com.macrochina.net.util;

import com.macrochina.net.utils.SignTools;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.security.PublicKey;
import java.util.Base64;

public class SignUtils{

    /**
     * Method used to get the XML document by parsing
     *
     * @param xmlFilePath , file path of the XML document
     * @return Document
     */
    public static Document getXmlDocument(String xmlFilePath) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //if doesn't setNamespaceAware(true);
        //getElementsByTagNameNS(XMLSignature.XMLNS, "Signature") may be unavailable
        dbf.setNamespaceAware(true);
        try {
            doc = dbf.newDocumentBuilder().parse(new FileInputStream(xmlFilePath));
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return doc;
    }

    /**
     * convert string to Element
     *
     * @param xmlStr
     * @return Element
     */
    public static Element convertStringToElement(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //if doesn't setNamespaceAware(true);
        //getElementsByTagNameNS(XMLSignature.XMLNS, "Signature") may be unavailable
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) );
            return doc.getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     * 公钥验签
     * @param xml
     * @param publicKey
     * @param isBase64
     * @return boolean
     * @throws Exception
     */
    public static boolean isXmlDigitalSignatureValid(String xml,
                                                     String publicKey, boolean isBase64) throws Exception {
        //Document doc = getXmlDocument(signedXmlFilePath);
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        docBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        docBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        docBuilderFactory.setNamespaceAware(true);
        Document doc = docBuilderFactory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        if (isBase64) {
            //encode the signature element before store it
            try {
                Element root = doc.getDocumentElement();
                NodeList signatures = root.getElementsByTagName("Signature");
                if (signatures.getLength() != 1) {
                    throw new Exception("Signature error");
                }else {
                    Node signature = signatures.item(0);
                    System.out.println(signature.getTextContent());
                    String signatureContent = new String(Base64.getDecoder().decode(signature.getTextContent()));
                    Node newChild = doc.importNode(convertStringToElement(signatureContent), true);
                    root.replaceChild(newChild, signature);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("No XML Digital Signature Found, document is discarded");
        }
        publicKey=publicKey.replaceAll("-----BEGIN CERTIFICATE-----","").replaceAll("-----END CERTIFICATE-----","");
        publicKey=publicKey.replaceAll("-----BEGIN PUBLIC KEY-----","").replaceAll("-----END PUBLIC KEY-----","");
        System.out.println("puiblicKey:"+publicKey);
        PublicKey pubKey=SignTools.getPublicKeyByStr(publicKey);
        DOMValidateContext valContext = new DOMValidateContext(pubKey, nl.item(0));
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);
        return signature.validate(valContext);
    }
}