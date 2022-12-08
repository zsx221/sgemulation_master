package com.macrochina.net.service;

import bcsis.HdrAndData;
import com.macrochina.net.dto.Admi004Dto;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import iso.admi_004_001.Admi00400101;
import iso.admi_004_001.Document;
import iso.admi_004_001.Event1;
import iso.head_001_001.BusinessApplicationHeaderV01;
import iso.head_001_001.G3MessageTypes;
import iso.head_001_001.Party9Choice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.Calendar;


@Slf4j
@Service
public class Admi004Service {
    @Autowired
    FpsParamService fpsParamService;


    @Autowired
    SchemaService  schemaService;

    @Autowired
    MessagePacsService messagePacsService;

    public void receiveNotification(Admi004Dto dto){
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();
        String msgId = MessageUtils.genMsgId();
        iso.admi_004_001.Document document = new Document();
        appHdr.setBizMsgIdr(msgId);

        dto.getEvtParam().add(0,msgId);
        appHdr.setMsgDefIdr(G3MessageTypes.ADMI_004_001_01);
        appHdr.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));

        FpsParam fpsParam = fpsParamService.findById();
        String sendBank = fpsParam.getSendBank();
        String acceptBank = fpsParam.getAcceptBank();
        Party9Choice fr = new Party9Choice();
        MessageHeadUtils.fillHeadFrom(fr, appHdr.getTo().getFIId().getFinInstnId().getBICFI());
        appHdr.setFr(fr);
        Party9Choice to = new Party9Choice();
        MessageHeadUtils.fillHeadTo(to,appHdr.getFr().getFIId().getFinInstnId().getBICFI());
        appHdr.setTo(to);
        hdrAndData.setAppHdr(appHdr);
        document = format(dto);
        hdrAndData.setSystemNotificationEvent(document);
        String xml = null;
        try {
            xml = ConvertUtils.marshal(hdrAndData, true);
        } catch (JAXBException e) {

        }
        String xmlSign =   fpsParamService.xmlStringSign(xml) ;
        String fileUrl = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName();
        FileUtils.write(xmlSign,fileUrl);
        log.info("outgoing admi004 :" + xmlSign);
//        String fileName = String.valueOf(System.currentTimeMillis());
//        FileUtils.strToXml(xmlSign, fileName);
//        Boolean bool = schemaService.validateXmlByXsd(fileName, SysParamsContst.PACS_002,FileUtils.FPS_SCHEMA);
//        if(bool) {
            // 保存报文以及接收到错误报文处理
//        String url = "http://localhost:8080/sgfps/fast/v1/payment/credit-transfer/send";
            String ip = fpsParam.getHostAdr();
            String url = ip + "/fast/v1/admin/snm/receive";

        messagePacsService.saveMessage(hdrAndData,fileUrl, SysParamsContst.OUTWARD);
            HttpClientUtils.doPost(url, xmlSign);
//        }

    }
    public iso.admi_004_001.Document format(Admi004Dto admi){
        iso.admi_004_001.Document doc = new iso.admi_004_001.Document();
        Admi00400101 admi00400101 = new Admi00400101();
        Event1 event1 = new Event1();
        event1.setEvtCd(admi.getEvtCd());
        event1.setEvtTm(XmlIsoDateUtil.convertToXMLGregorianCalendarMillsec(Calendar.getInstance()));
        if(!StringUtils.isEmpty(admi.getEvtDesc())){
            event1.setEvtDesc(admi.getEvtDesc());
        }
        if(admi.getEvtParam()!=null && admi.getEvtParam().size()>0){
            for(String str:admi.getEvtParam()){
                event1.getEvtParam().add(str);
            }
        }
        admi00400101.setEvtInf(event1);
        doc.setAdmi00400101(admi00400101);
        return doc;
    }
}
