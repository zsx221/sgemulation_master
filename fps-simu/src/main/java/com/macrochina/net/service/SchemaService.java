package com.macrochina.net.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;

@Slf4j
@Service
public class SchemaService {

    public Boolean validateXmlByXsd2(String xml, String xsdName,String url) {
        boolean enabled = false;
        String xsdFileName = url + xsdName;
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            //xml
            InputSource inputSource = new InputSource(new ByteArrayInputStream(xml.getBytes()));//A single input source for an XML entity
            Source source = new SAXSource(inputSource);
            //xsd
            Schema schema = factory.newSchema(SchemaService.class.getResource(xsdFileName));
            Validator validator = schema.newValidator();
            validator.validate(source);
            enabled = true;
            log.info("==================xml verify success!!!===============");
        } catch (Exception ex) {
            log.info("==================xml verify fail!!!===============");
            log.info("cause: " + ex.getMessage());
            ex.printStackTrace();
        }
        return enabled;
    }
}
