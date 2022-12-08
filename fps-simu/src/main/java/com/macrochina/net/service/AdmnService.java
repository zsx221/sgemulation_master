package com.macrochina.net.service;

import bcsis.HdrAndData;
import com.macrochina.net.dao.FpsParamRepository;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.util.*;
import iso.head_001_001.*;
import iso.pacs_002_001.TransactionIndividualStatus3Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AdmnService {
    @Autowired
    MessageAdmnService messageAdmnService;
    @Autowired
    DbToXmlService dbToXmlService;
    @Autowired
    SchemaService schemaService;
    @Autowired
    FpsParamRepository fpsParamRepository;
    @Autowired
    private FpsParamService fpsParamService;

    public HdrAndData receive(String xml, String url) throws JAXBException {
        // System.out.println(xml);
        HdrAndData hdrAndData = ConvertUtils.unmarshal(xml);
        // messageAdmnService.saveMessage(hdrAndData,url,SysParamsContst.INWARD);
        return hdrAndData;
        //   sendAdmn002(hdrAndData);
    }

    @Async("asyncTaskExecutor")
    public void sendAdmn002(HdrAndData fpsMsg, BizRuleSet bizRuleSet) throws JAXBException {
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 oriHead = fpsMsg.getAppHdr();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        fillHeader(oriHead, retHead, G3MessageTypes.ADMN_002_001_01);
        hdrAndData.setAppHdr(retHead);// 组装 head
        iso.admn_002_001.Document document = new iso.admn_002_001.Document();
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", MessageUtils.genMsgId());
        map.put("creDtTm", XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        map.put("orgnlInstrId", fpsMsg.getSignOnRequest().getAdmnSignOnReq().getSignOnReq().getInstrId());
        map.put("instgAgtMmbId", fpsMsg.getSignOnRequest().getAdmnSignOnReq().getSignOnReq().getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId());
        map.put("instdAgtMmbId", fpsMsg.getSignOnRequest().getAdmnSignOnReq().getSignOnReq().getInstdAgt().getFinInstnId().getClrSysMmbId().getMmbId());
        map.put("sts", "ACTC");
        if(bizRuleSet!=null && "Reject".equals(bizRuleSet.getResp_status())){
            map.put("sts", "RJCT");
        }
        iso.admn_002_001.Document pacs002 = (iso.admn_002_001.Document) dbToXmlService.DbToXml(map, new iso.admn_002_001.Document());
        hdrAndData.setSignOnResponse(pacs002);
        String xml = ConvertUtils.marshal(hdrAndData, true);
        String signXml = fpsParamService.xmlStringSign(xml);
        //xmlSignService.xmlStringSign(xml, "admin", "123456", "", "", "");
        // 写入本地文件
        String url = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
        //FileUtils.write(signXml,url);
        // messageAdmnService.saveMessage(hdrAndData,url,SysParamsContst.OUTWARD);
/*        String fileName = String.valueOf(System.currentTimeMillis());
        FileUtils.strToXml(signXml, fileName);*/
        /*Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_ADMN_002,FileUtils.FPS_SCHEMA);
       if(bool){*/
//        log.info("outgoing admn 002 String xml =  "+signXml);
        String ip = fpsParamRepository.findById(1).get().getHostAdr();
        sendTask(signXml, ip + "/fast/v1/admin/sign-on/response",bizRuleSet);

        // }
    }

    @Async("asyncTaskExecutor")
    public void sendAdmn004(HdrAndData fpsMsg, BizRuleSet bizRuleSet) throws JAXBException {
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 oriHead = fpsMsg.getAppHdr();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        fillHeader(oriHead, retHead, G3MessageTypes.ADMN_004_001_01);
        hdrAndData.setAppHdr(retHead);// 组装 head
        iso.admn_004_001.Document document = new iso.admn_004_001.Document();
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", MessageUtils.genMsgId());
        map.put("creDtTm", XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        map.put("orgnlInstrId", fpsMsg.getSignOffRequest().getAdmnSignOffReq().getSignOffReq().getInstrId());
        map.put("instgAgtMmbId", fpsMsg.getSignOffRequest().getAdmnSignOffReq().getSignOffReq().getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId());
        map.put("instdAgtMmbId", fpsMsg.getSignOffRequest().getAdmnSignOffReq().getSignOffReq().getInstdAgt().getFinInstnId().getClrSysMmbId().getMmbId());
        map.put("sts", TransactionIndividualStatus3Code.fromValue("ACTC"));
        if(bizRuleSet!=null && "Reject".equals(bizRuleSet.getResp_status())){
            map.put("sts", "RJCT");
        }

        iso.admn_004_001.Document admn004 = (iso.admn_004_001.Document) dbToXmlService.DbToXml(map, new iso.admn_004_001.Document());
        hdrAndData.setSignOffResponse(admn004);
        String xml = ConvertUtils.marshal(hdrAndData, true);
        String signXml = fpsParamService.xmlStringSign(xml);
        // String signXml = NewXmlSignUtil.xmlStringSign(xml, Constants.OFFORON,"admin", "123456", "", "", "");
        //xmlSignService.xmlStringSign(xml, "admin", "123456", "", "", "");
        // 写入本地文件
        String url = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
        //FileUtils.write(signXml,url);
        // messageAdmnService.saveMessage(hdrAndData,url,SysParamsContst.OUTWARD);
/*        String fileName = String.valueOf(System.currentTimeMillis());
        FileUtils.strToXml(signXml, fileName);*/
       /* Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_ADMN_004,FileUtils.FPS_SCHEMA);
        if(bool){*/
        /*  log.info("outgoing admn 004 String xml =  "+signXml);*/
        String ip = fpsParamRepository.findById(1).get().getHostAdr();
        sendTask(signXml, ip + "/fast/v1/admin/sign-off/response",bizRuleSet);
        // }
    }

    @Async("asyncTaskExecutor")
    public void sendAdmn006(HdrAndData fpsMsg, BizRuleSet bizRuleSet) throws JAXBException {
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 oriHead = fpsMsg.getAppHdr();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        fillHeader(oriHead, retHead, G3MessageTypes.ADMN_006_001_01);
        hdrAndData.setAppHdr(retHead);// 组装 head
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", MessageUtils.genMsgId());
        map.put("creDtTm", XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        map.put("orgnlInstrId", fpsMsg.getEchoRequest().getAdmnEchoReq().getEchoTxInf().getInstrId());
        map.put("instgAgtMmbId", fpsMsg.getEchoRequest().getAdmnEchoReq().getEchoTxInf().getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId());
        map.put("instdAgtMmbId", fpsMsg.getEchoRequest().getAdmnEchoReq().getEchoTxInf().getInstdAgt().getFinInstnId().getClrSysMmbId().getMmbId());
        map.put("fnctnCd", fpsMsg.getEchoRequest().getAdmnEchoReq().getEchoTxInf().getFnctnCd());
        map.put("txSts", TransactionIndividualStatus3Code.fromValue("ACTC"));
        if(bizRuleSet!=null && "Reject".equals(bizRuleSet.getResp_status())){
            map.put("txSts", TransactionIndividualStatus3Code.fromValue("RJCT"));
        }
        iso.admn_006_001.Document admn006 = (iso.admn_006_001.Document) dbToXmlService.DbToXml(map, new iso.admn_006_001.Document());
        hdrAndData.setEchoResponse(admn006);
        String xml = ConvertUtils.marshal(hdrAndData, true);
        xml = xml.replace("re:re:EchoResponse", "re:EchoResponse");
        String signXml = fpsParamService.xmlStringSign(xml);
        // String signXml = NewXmlSignUtil.xmlStringSign(xml, Constants.OFFORON,"admin", "123456", "", "", "");
        //xmlSignService.xmlStringSign(xml, "admin", "123456", "", "", "");
        // 写入本地文件
        //String url = FileUtils.OUTWARD_RT_REQ+ MessageUtils.getXmlName();
        //FileUtils.write(signXml,url);
        //messageAdmnService.saveMessage(hdrAndData,url,SysParamsContst.OUTWARD);
    /*    String fileName = String.valueOf(System.currentTimeMillis());
        FileUtils.strToXml(signXml, fileName);*/
      /*  Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_ADMN_006,FileUtils.FPS_SCHEMA);
        if(bool){*/
//        log.info("outgoing admn 006 String xml =  "+signXml);
        String ip = fpsParamRepository.findById(1).get().getHostAdr();
        sendTask(signXml, ip + "/fast/v1/admin/echo/response",bizRuleSet);
        //  }
    }

    public void sendTask(String xml, String url,BizRuleSet bizRuleSet) {
//        String url = "http://localhost:8080/sgfps/fast/v1/payment/credit-transfer-status/send";
        //String url = "https://opf-service.default.svc.cluster.local:9443/fast/v1/payment/credit-transfer-status/send";
        HttpClientUtils.sendCallback(url, xml,bizRuleSet);
    }

    public void fillHeader(BusinessApplicationHeaderV01 origHead, BusinessApplicationHeaderV01 retHead, G3MessageTypes messageType) {
        retHead.setBizMsgIdr(MessageUtils.genMsgId());
        retHead.setMsgDefIdr(messageType);
        retHead.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        retHead.setBizSvc(origHead.getBizSvc());
        Party9Choice fr = new Party9Choice();
        FpsParam param = fpsParamService.findById();
        fillHead(fr, origHead.getTo().getFIId().getFinInstnId().getBICFI());
        retHead.setFr(fr);
        Party9Choice to = new Party9Choice();
        fillHead(to, origHead.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
        retHead.setTo(to);
    }

    public void fillHead(Party9Choice from, String mid) {
        BranchAndFinancialInstitutionIdentification5 fiid = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 finsId = new FinancialInstitutionIdentification8();
        ClearingSystemMemberIdentification2 mbid = new ClearingSystemMemberIdentification2();
        mbid.setMmbId(mid);
        finsId.setClrSysMmbId(mbid);
        fiid.setFinInstnId(finsId);
        from.setFIId(fiid);
    }
}
