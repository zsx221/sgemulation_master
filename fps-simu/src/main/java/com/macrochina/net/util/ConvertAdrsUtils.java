package com.macrochina.net.util;

import address.head_001_001.MessageTypesCAS;
import adrs.HdrAndData;
import com.macrochina.net.jaxb.XmlNsprefixFilter;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.xml.bind.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class ConvertAdrsUtils {

    private static class InitJaxCtx {
        private static InitJaxCtx initDelay = new InitJaxCtx();
        private final JAXBContext jaxbCtx;

        private InitJaxCtx() {
            try {
                this.jaxbCtx = JAXBContext.newInstance(HdrAndData.class);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }

        private static InitJaxCtx instance() {
            return initDelay;
        }

        private JAXBContext jaxbContext() {
            return jaxbCtx;
        }
    }

    public static String marshal(HdrAndData root, boolean isFormat) throws JAXBException {
        MessageTypesCAS msgDefIdr = root.getAppHdr().getMsgDefIdr();
        JAXBContext jaxbCtx = InitJaxCtx.instance().jaxbContext();
        Marshaller marshaller = jaxbCtx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis messages.xsd");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        StringWriter out = new StringWriter();
        OutputFormat format = new OutputFormat();
        format.setIndent(isFormat);
        format.setNewlines(isFormat);
        format.setNewLineAfterDeclaration(false);
        XMLWriter writer = new XMLWriter(out, format);

        XmlNsprefixFilter nsfFilter = new XmlNsprefixFilter();
        prefix(msgDefIdr,nsfFilter);
        nsfFilter.setContentHandler(writer);
        marshaller.marshal(root, nsfFilter);
        String xml = out.toString();
        List<String> list = new ArrayList<>();
        list.add(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        list.add(" xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"");
        list.add(" xsi:schemaLocation=\"urn:bcsis messages.xsd\"");
        switch(msgDefIdr.value()){
            case "prxy.002.001.01" :
                list.add(" xmlns:p2=\"urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01\"");
                break;
            case "prxy.004.001.01" :
                list.add(" xmlns:p4=\"urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01\"");
                break;
            case "prxy.006.001.01" :
                list.add(" xmlns:p6=\"urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01\"");
                break;
            case "prxy.008.001.01" :
                list.add(" xmlns:p8=\"urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01\"");
                break;
            case "admn.006.001.01" :
                list.add(" xmlns:re=\"urn:iso:std:iso:20022:tech:xsd:admn.006.001.01\"");
                break;
        }
        for (String xls : list) {
            xml = xml.replaceFirst(xls, "");
        }
//        if(msgDefIdr.value().equals("admn.006.001.01")){
//            return xml;
//        }else {
//            String xl1 = xml.replaceFirst("<head:Fr", "<head:Fr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"");
//            String xl2 = xl1.replaceFirst("<head:To", "<head:To xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"");
//            String xl3 = xl2.replaceFirst("<head:BizMsgIdr", "<head:BizMsgIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"");
//            String xl4 = xl3.replaceFirst("<head:MsgDefIdr", "<head:MsgDefIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"");
//            String xl5 = xl4.replaceFirst("<head:CreDt", "<head:CreDt xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"");
//            String xl6 = xl5.replaceFirst("<head:Sgntr", "<head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"");
//            String xl7 = null;
//            switch(msgDefIdr.value()){
//                case "prxy.002.001.01" :
//                    xl7 = xl6.replaceFirst("<p2:PrxyRegnRspn", "<p2:PrxyRegnRspn xmlns:p2=\"urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01\"");
//                    break;
//                case "prxy.004.001.01" :
//                    xl7 = xl6.replaceFirst("<p4:PrxyLookUpRspn", "<p4:PrxyLookUpRspn xmlns:p4=\"urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01\"");
//                    break;
//                case "prxy.006.001.01" :
//                    xl7 = xl6.replaceFirst("<p6:PrxyNqryRspn", "<p6:PrxyNqryRspn xmlns:p6=\"urn:iso:std:iso:20022:tech:xsd:prxy.006.001.01\"");
//                    break;
//                case "prxy.008.001.01" :
//                    xl7 = xl6.replaceFirst("<p8:PtcptRptRspn", "<p8:PtcptRptRspn xmlns:p8=\"urn:iso:std:iso:20022:tech:xsd:prxy.008.001.01\"");
//                    break;
//                case "admi.002.001.01" :
//                    xl7 = xl6.replaceFirst("<mr:admi.002.001.01", "<mr:admi.002.001.01 xmlns:mr=\"urn:iso:std:iso:20022:tech:xsd:admi.002.001.01\"");
//                    break;
//            }
            return xml;
//        }
    }

    public static HdrAndData unmarshal(String xml) {
        JAXBContext jaxbCtx = InitJaxCtx.instance().jaxbContext();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Unmarshaller unmarshaler = jaxbCtx.createUnmarshaller();
            HdrAndData result = (HdrAndData) unmarshaler.unmarshal(bais);

            bais.close();
            return result;
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    public static HdrAndData unmarshal(Path xmlFile) throws JAXBException, IOException {
        JAXBContext jaxbCtx = InitJaxCtx.instance().jaxbContext();
        Unmarshaller unmarshaler = jaxbCtx.createUnmarshaller();
        HdrAndData result;
        try (BufferedReader br = Files.newBufferedReader(xmlFile)) {
            result = (HdrAndData) unmarshaler.unmarshal(br);
            br.close();
        }
        return result;
    }

    public static HdrAndData unmarshal(URL url) throws JAXBException {
        JAXBContext jaxbCtx = InitJaxCtx.instance().jaxbContext();
        Unmarshaller unmarshaler = jaxbCtx.createUnmarshaller();
        HdrAndData result = (HdrAndData) unmarshaler.unmarshal(url);
        return result;
    }

    private static void extracted(MessageTypesCAS msgDefIdr, Marshaller marshaller) throws PropertyException {
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis messages.xsd");
    }

    private static void prefix(MessageTypesCAS msgDefIdr,XmlNsprefixFilter nsfFilter) {
        if(msgDefIdr.equals(MessageTypesCAS.PRXY_001_001_01)){
            nsfFilter.startPrefixMappingExtend("p1");
        }
        if(msgDefIdr.equals(MessageTypesCAS.PRXY_002_001_01)){
            nsfFilter.startPrefixMappingExtend("p2");
        }
        if(msgDefIdr.equals(MessageTypesCAS.PRXY_003_001_01)){
            nsfFilter.startPrefixMappingExtend("p3");
        }
        if(msgDefIdr.equals(MessageTypesCAS.PRXY_004_001_01)){
            nsfFilter.startPrefixMappingExtend("p4");
        }
        if(msgDefIdr.equals(MessageTypesCAS.PRXY_005_001_01)){
            nsfFilter.startPrefixMappingExtend("p5");
        }
        if(msgDefIdr.equals(MessageTypesCAS.PRXY_006_001_01)){
            nsfFilter.startPrefixMappingExtend("p6");
        }
        if(msgDefIdr.equals(MessageTypesCAS.PRXY_007_001_01)){
            nsfFilter.startPrefixMappingExtend("p7");
        }
        if(msgDefIdr.equals(MessageTypesCAS.PRXY_008_001_01)){
            nsfFilter.startPrefixMappingExtend("p8");
        }
        if(msgDefIdr.equals(MessageTypesCAS.ADMN_006_001_01)){
            nsfFilter.startPrefixMappingExtend("re");
        }
    }
}