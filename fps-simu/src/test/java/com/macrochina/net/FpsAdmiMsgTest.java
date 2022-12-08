package com.macrochina.net;


import adrs.HdrAndData;
import com.macrochina.net.util.ConvertAdrsUtils;
import com.macrochina.net.util.ConvertUtils;
import com.macrochina.net.utils.SignTools;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.util.XMLErrorHandler;
import org.junit.Test;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

public class FpsAdmiMsgTest {

	@Test
	public void test008() throws JAXBException {
		String path="C:\\home\\pacs.002.xml";
		String xml = SignTools.readKeyFromFile(path) ;
		bcsis.HdrAndData fpsMsg = ConvertUtils.unmarshal(xml);

		System.out.println(">>>>>>>>>>>>>>>组包>>>>>>>>>>>>>>>>>");
		String xls = ConvertUtils.marshal(fpsMsg, true);
		System.out.println(xls);

		System.out.println("------------------解包-----------------------");
		fpsMsg = ConvertUtils.unmarshal(xml);
		System.out.println(fpsMsg);

	}

	@Test
	public void pxry001() throws JAXBException {
		String path="C:\\home\\prxy.001.001.01.xml";
		String xml = SignTools.readKeyFromFile(path) ;
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:bcsis\" xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"xsi:schemaLocation=\"urn:bcsis ..messages.xsd\"><AppHdr><head:Fr><head:FIId><head:FinInstnId><head:BICFI>BANKSGSGXXX</head:BICFI></head:FinInstnId></head:FIId></head:Fr><head:To><head:FIId><head:FinInstnId><head:BICFI>SACHSGS1XXX</head:BICFI></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr>B20161011BANKSGSGB0001</head:BizMsgIdr><head:MsgDefIdr>prxy.001.001.01</head:MsgDefIdr><head:CreDt>2016-10-11T07:21:00Z</head:CreDt><head:Sgntr><n1:auto-generated_for_wildcard xmlns:n1=\"http://www.w3.org/2000/09/xmldsig#\"></n1:auto-generated_for_wildcard></head:Sgntr></AppHdr><ProxyRegistration><p1:PrxyRegn xmlns:p1=\" urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01\"><p1:GrpHdr><p1:MsgId>M20160614BANKSGSGB1234567890</p1:MsgId><p1:CreDtTm>2016-10-11T15:21:00.000</p1:CreDtTm><p1:MsgSndr><p1:Agt><p1:FinInstnId><p1:BICFI>BANKSGSGXXX</p1:BICFI></p1:FinInstnId></p1:Agt></p1:MsgSndr></p1:GrpHdr><p1:Regn><p1:RegnTp>NEWR</p1:RegnTp><p1:Prxy><p1:Tp>MSISDN</p1:Tp><p1:Val>+65087808652001001</p1:Val></p1:Prxy><p1:PrxyRegn><p1:DsplNm>Andy Smith</p1:DsplNm><p1:Agt><p1:FinInstnId><p1:BICFI>BANKSGSGXXX</p1:BICFI></p1:FinInstnId></p1:Agt><p1:Acct><p1:Id><p1:Othr><p1:Id>1100017951219900</p1:Id></p1:Othr></p1:Id><p1:Tp><p1:Prtry>BANK</p1:Prtry></p1:Tp><p1:Nm>Mr A N Smith</p1:Nm></p1:Acct><p1:AcctHldr><p1:IndvPrsn><p1:GvnNm>Andrew</p1:GvnNm><p1:MddlNm>Nigel</p1:MddlNm><p1:Nm>Smith</p1:Nm></p1:IndvPrsn></p1:AcctHldr><p1:PreAuthrsd>true</p1:PreAuthrsd></p1:PrxyRegn></p1:Regn></p1:PrxyRegn></ProxyRegistration></Message>";
		HdrAndData fpsMsg = ConvertAdrsUtils.unmarshal(xml);

		System.out.println(">>>>>>>>>>>>>>>组包>>>>>>>>>>>>>>>>>");
		String xls = ConvertAdrsUtils.marshal(fpsMsg, true);
		System.out.println(xls);

		System.out.println("------------------解包-----------------------");
		fpsMsg = ConvertAdrsUtils.unmarshal(xml);
		System.out.println(fpsMsg);

	}

	@Test
	public void prxy() {
		validateXmlByXsd("prxy.001.001.01","prxy.001.001.01");
	}

	public Boolean validateXmlByXsd(String xmlName, String xsdName) {
		boolean enabled = false;
		String head = "/home/schema1/";
		String xmlFileName = head + xmlName + ".xml";
		String xsdFileName = head + xsdName + ".xsd";
		try {
			String path = "C:\\home\\prxy.001.001.01.xml";
			String xsdText = SignTools.readKeyFromFile(path) ;
			InputStream is = new ByteArrayInputStream(xsdText.getBytes());
			XMLErrorHandler errorHandler = new XMLErrorHandler();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
			factory.setValidating(true);
			factory.setNamespaceAware(true);
			SAXParser parser = factory.newSAXParser();
			SAXReader xmlReader = new SAXReader();
			File file = Paths.get(xmlFileName).toFile();
			Document xmlDocument = xmlReader.read(file);
			parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", XMLConstants.W3C_XML_SCHEMA_NS_URI);
			parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", is);
			SAXValidator validator = new SAXValidator(parser.getXMLReader());
			validator.setErrorHandler(errorHandler);
			validator.validate(xmlDocument);
			if (errorHandler.getErrors().hasContent()) {
				System.out.println();
				List<Element> elements = errorHandler.getErrors().elements();
				for (Element element : elements) {
					List<Attribute> attributes = element.attributes();
					System.out.println("第"+attributes.get(0).getValue()+"栏,"+"第"+attributes.get(0).getValue()+"行");
					System.out.println("错误内容："+element.getStringValue());
					System.out.println();
				}
			} else {
				enabled = true;
				System.out.println("==========================校验成功！==========================");
			}
		} catch (Exception ex) {
			System.out.println("========xml文件: " + "xmlFileName" + " 通过xsd文件:" + xsdFileName + "检验失败。\n原因： " + ex.getMessage());
			ex.printStackTrace();
		}
		return enabled;
	}
}
