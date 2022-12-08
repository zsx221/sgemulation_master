package com.macrochina.net.util;

import bcsis.HdrAndData;
import com.macrochina.net.jaxb.XmlNsprefixFilter;
import iso.head_001_001.G3MessageTypes;
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


public class ConvertUtils {

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
        G3MessageTypes msgDefIdr = root.getAppHdr().getMsgDefIdr();
        JAXBContext jaxbCtx = InitJaxCtx.instance().jaxbContext();
        Marshaller marshaller = jaxbCtx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
        extracted(msgDefIdr, marshaller);
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
        switch(msgDefIdr.value()){
            case "pacs.008.001.02" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Credit.Transfer.01.xsd\"");
                list.add(" xmlns:ct=\"urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02\"");
                break;
            case "pacs.002.001.03" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Payment.Status.01.xsd\"");
                list.add(" xmlns:ps=\"urn:iso:std:iso:20022:tech:xsd:pacs.002.001.03\"");
                break;
            case "admi.002.001.01" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Admin.Message.Reject.01.xsd\"");
                list.add(" xmlns:mr=\"urn:iso:std:iso:20022:tech:xsd:admi.002.001.01\"");
                break;
            case "admi.004.001.01" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.System.Event.Notification.01.xsd\"");
                list.add(" xmlns:ne=\"urn:iso:std:iso:20022:tech:xsd:admi.004.001.01\"");
                break;
            case "admn.002.001.01" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Sign.On.Response.01.xsd\"");
                list.add(" xmlns:rs=\"urn:iso:std:iso:20022:tech:xsd:admn.002.001.01\"");
                break;
            case "admn.004.001.01" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Sign.Off.Response.01.xsd\"");
                list.add(" xmlns:rf=\"urn:iso:std:iso:20022:tech:xsd:admn.004.001.01\"");
                break;
            case "admn.005.001.01" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Echo.Request.01.xsd\"");
                list.add(" xmlns:er=\"urn:iso:std:iso:20022:tech:xsd:admn.005.001.01\"");
                break;
            case "admn.006.001.01" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Echo.Response.01.xsd\"");
                list.add(" xmlns:re=\"urn:iso:std:iso:20022:tech:xsd:admn.006.001.01\"");
                break;
            case "camt.053.001.02" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Report.Bank.Statement.01.xsd\"");
                list.add(" xmlns:bs=\"urn:iso:std:iso:20022:tech:xsd:camt.053.001.02\"");
                break;
            case "camt.056.001.01" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Payment.Cancellation.01.xsd\"");
                list.add(" xmlns:pc=\"urn:iso:std:iso:20022:tech:xsd:camt.056.001.01\"");
                break;
            case "stmt.002.001.01" :
                list.add(" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Bank.Statement.Response.01.xsd\"");
                list.add(" xmlns:bp=\"urn:iso:std:iso:20022:tech:xsd:stmt.002.001.01\"");
                break;
        }
        for (String xl : list) {
                xml = xml.replaceFirst(xl, "");
        }
        if(msgDefIdr.value().equals("stmt.002.001.01")){
            return xml.replaceFirst("<bp:BankStmtResp","<bp:BankStmtResp xmlns:bp=\"urn:iso:std:iso:20022:tech:xsd:stmt.002.001.01\"");
        }else if(msgDefIdr.value().equals("admi.004.001.01")){
            return xml.replaceFirst("<ne:admi.004.001.01","<ne:admi.004.001.01 xmlns:ne=\"urn:iso:std:iso:20022:tech:xsd:admi.004.001.01\"");
        }else {
            return xml;
        }
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

    private static void extracted(G3MessageTypes msgDefIdr, Marshaller marshaller) throws PropertyException {
        if(msgDefIdr.equals(G3MessageTypes.PACS_002_001_03)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Payment.Status.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.PACS_003_001_02)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Direct.Debit.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.PACS_007_001_02)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Payment.Reversal.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.PACS_008_001_02)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Credit.Transfer.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.CAMT_029_001_03)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Resolution.Of.Investigation.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.CAMT_053_001_02)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Report.Bank.Statement.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.CAMT_056_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Payment.Cancellation.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_001_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Sign.On.Request.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_002_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Sign.On.Response.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_003_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Sign.Off.Request.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_004_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Sign.Off.Response.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_005_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Echo.Request.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_006_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Echo.Response.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMI_002_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Admin.Message.Reject.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMI_004_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.System.Event.Notification.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.CTRL_001_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Admin.ControlFile.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.STMT_001_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Bank.Statement.Request.01.xsd");
        }
        if(msgDefIdr.equals(G3MessageTypes.STMT_002_001_01)){
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:bcsis G3.Real-Time.Bank.Statement.Response.01.xsd");
        }
    }

    private static void prefix(G3MessageTypes msgDefIdr,XmlNsprefixFilter nsfFilter) {
        if(msgDefIdr.equals(G3MessageTypes.PACS_002_001_03)){
            nsfFilter.startPrefixMappingExtend("ps");
        }
        if(msgDefIdr.equals(G3MessageTypes.PACS_003_001_02) || (msgDefIdr.equals(G3MessageTypes.PACS_007_001_02))){
            nsfFilter.startPrefixMappingExtend("xs");
        }
        if(msgDefIdr.equals(G3MessageTypes.PACS_008_001_02)){
            nsfFilter.startPrefixMappingExtend("ct");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMI_002_001_01)){
            nsfFilter.startPrefixMappingExtend("mr");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMI_004_001_01)){
            nsfFilter.startPrefixMappingExtend("ne");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_001_001_01)){
            nsfFilter.startPrefixMappingExtend("sr");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_002_001_01)){
            nsfFilter.startPrefixMappingExtend("rs");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_003_001_01)){
            nsfFilter.startPrefixMappingExtend("fr");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_004_001_01)){
            nsfFilter.startPrefixMappingExtend("rf");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_005_001_01)){
            nsfFilter.startPrefixMappingExtend("er");
        }
        if(msgDefIdr.equals(G3MessageTypes.ADMN_006_001_01)){
            nsfFilter.startPrefixMappingExtend("re");
        }
        if(msgDefIdr.equals(G3MessageTypes.CAMT_029_001_03)){
            nsfFilter.startPrefixMappingExtend("ri");
        }
        if(msgDefIdr.equals(G3MessageTypes.CAMT_053_001_02)){
            nsfFilter.startPrefixMappingExtend("bs");
        }
        if(msgDefIdr.equals(G3MessageTypes.CAMT_056_001_01)){
            nsfFilter.startPrefixMappingExtend("pc");
        }
        if(msgDefIdr.equals(G3MessageTypes.STMT_002_001_01)){
            nsfFilter.startPrefixMappingExtend("bp");
        }
    }
}
