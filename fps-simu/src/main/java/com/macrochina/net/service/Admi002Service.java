package com.macrochina.net.service;

import bcsis.HdrAndData;
import com.macrochina.net.dao.MessageRepository;
import com.macrochina.net.dto.Admi002Dto;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.entity.Message;
import com.macrochina.net.util.*;
import iso.admi_002_001.Admi00200101;
import iso.admi_002_001.Document;
import iso.admi_002_001.MessageReference;
import iso.admi_002_001.RejectionReason2;
import iso.head_001_001.BusinessApplicationHeaderV01;
import iso.head_001_001.G3MessageTypes;
import iso.head_001_001.Party9Choice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.xml.bind.JAXBException;
import java.util.Calendar;


@Slf4j
@Service
public class Admi002Service {

    private Log logger = LogFactory.getLog(Admi002Service.class);

    @Autowired
    FpsParamService fpsParamService;

    @Autowired
    SchemaService  schemaService;

    @Autowired
    MessageRepository messageRepository;


    @Async("asyncTaskExecutor")
    public void receive(Admi002Dto dto,String tag,String to ,String from){
        HdrAndData hdrAndData = new HdrAndData();
        Document document = new Document();

        //head
        BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();
        String msgId = MessageUtils.genMsgId();
        appHdr.setBizMsgIdr(msgId);
        appHdr.setMsgDefIdr(G3MessageTypes.ADMI_002_001_01);
        appHdr.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));

        FpsParam param = fpsParamService.findById();
        Party9Choice fr = new Party9Choice();
        MessageHeadUtils.fillHeadFrom(fr, to);
        appHdr.setFr(fr);
        Party9Choice party9Choiceto = new Party9Choice();
        MessageHeadUtils.fillHeadTo(party9Choiceto,from);
        appHdr.setTo(party9Choiceto);
        hdrAndData.setAppHdr(appHdr);

        //document
        Admi00200101 admi00200101 = new Admi00200101();
        MessageReference rltdRef = new MessageReference();
        rltdRef.setRef(dto.getRef());
        admi00200101.setRltdRef(rltdRef);
        RejectionReason2 rsn = new RejectionReason2();
        rsn.setAddtlData(FileUtils.addCdata(dto.getAddtlData()));
        rsn.setRjctgPtyRsn(dto.getRjctgPtyRsn());
        String ref = dto.getRjctgPtyRsn();
        if(ref.equals("650")){
            rsn.setRsnDesc("cannot parse the message");
        }else {
            rsn.setRsnDesc("signature mismatch or signature verification error.");
        }

        rsn.setRjctnDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        admi00200101.setRsn(rsn);
        document.setAdmi00200101(admi00200101);

        hdrAndData.setMessageReject(document);
        String xml = null;
        try {
            xml = ConvertUtils.marshal(hdrAndData, true);
        } catch (JAXBException e) {
            logger.error("error " + e);
        }
        xml = HtmlUtils.htmlUnescape(xml);
        xml = xml.replaceFirst("<mr:admi.002.001.01","<mr:admi.002.001.01 xmlns:mr=\"urn:iso:std:iso:20022:tech:xsd:admi.002.001.01\"");
        if("1".equals(fpsParamService.findById().getIsSign())){
            xml = xml.replaceFirst("</head:CreDt>","</head:CreDt><head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01 \"/>");
        }
        String xmlSign =  fpsParamService.xmlStringSign(xml) ;
        logger.info("outgoing admi002 xml ="+ xmlSign);
        String urls = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
        FileUtils.write(xmlSign,urls);
        this.saveMessagePacs002(hdrAndData,tag);
        String url = "";
        if(tag.equals("Paynow")){
            url = param.getPaynowHost() + "/paynow/v1/admin/advice/reject";
        }else {
            url = param.getHostAdr() + "/fast/v1/admin/advice/reject";
        }
        logger.info(url);
        HttpClientUtils.doPost(url, xmlSign);
    }

    @Async("asyncTaskExecutor")
    public void saveMessagePacs002(HdrAndData fpsMsgEnv,String tag){
        Message fpsMsg = new Message();
        BusinessApplicationHeaderV01 appHdr = fpsMsgEnv.getAppHdr();
        if(appHdr != null){
            String fr = appHdr.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId();
            String to = appHdr.getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId();
            fpsMsg.setToInstn(to);
            fpsMsg.setFromInstn(fr);
            fpsMsg.setMsgId(appHdr.getBizMsgIdr());
        }
        fpsMsg.setDirct(0);
        fpsMsg.setState(1);
        fpsMsg.setMsgDef(appHdr.getMsgDefIdr().value());
        fpsMsg.setMsgFile(FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName());
        fpsMsg.setTag(tag);
        messageRepository.save(fpsMsg);
    }
}
