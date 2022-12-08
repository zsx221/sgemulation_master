package com.macrochina.net.service;

import bcsis.HdrAndData;
import com.macrochina.net.dao.FpsParamRepository;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.entity.Message;
import com.macrochina.net.entity.MessagePacs;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import iso.head_001_001.BusinessApplicationHeaderV01;
import iso.head_001_001.G3MessageTypes;
import iso.head_001_001.Party9Choice;
import iso.pacs_008_001.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class Camt056Service {
    @Autowired
    DbToXmlService dbToXmlService;

    @Autowired
    FpsParamService fpsParamService;

    @Autowired
    MessagePacsService messagePacsService;

    @Async("asyncTaskExecutor")
    public void sendCamt056(Document document ,String dbtrcMmid , String cdtrcMmid){
        iso.camt_056_001.Document document056 = new iso.camt_056_001.Document();

        Map<String,Object> map = new HashMap<>();
        FpsParam param = fpsParamService.findById();
        String msgId = MessageUtils.genMsgId();
        map.put("assgnmtId", MessageUtils.genMsgId());
        map.put("assgnrMmid",param.getAcceptBank());
        map.put("assgneMmid",param.getSendBank());
        map.put("creDtTm", XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        map.put("caseId",MessageUtils.genMsgId());
        map.put("cretrMmid",param.getAcceptBank());
        map.put("orgnlMsgId",document.getFIToFICstmrCdtTrf().getGrpHdr().getMsgId());
        map.put("orgnlMsgNmId","pacs.008.001.02");
        map.put("orgnlInstrId",document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getPmtId().getInstrId());
        map.put("orgnlEndToEndId",document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getPmtId().getEndToEndId());
        map.put("orgnlTxId",document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getPmtId().getTxId());
        //map.put("orgnlClrSysRef", document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).get);
        map.put("orgnlIntrBkSttlmAmt",document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getIntrBkSttlmAmt().getValue());
        map.put("ccy",document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getIntrBkSttlmAmt().getCcy());
        map.put("orgnlIntrBkSttlmDt",document.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getIntrBkSttlmDt());
        map.put("prtry","4021"); //超时代码

        document056 = (iso.camt_056_001.Document) dbToXmlService.DbToXml(map, document056);
        HdrAndData fpsevnp = new HdrAndData();
        Party9Choice fr = new Party9Choice();
        MessageHeadUtils.fillHeadFrom(fr, dbtrcMmid);
        BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();

        appHdr.setBizMsgIdr(msgId);
        appHdr.setMsgDefIdr(G3MessageTypes.CAMT_056_001_01);
        appHdr.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        appHdr.setFr(fr);
        Party9Choice to = new Party9Choice();
        MessageHeadUtils.fillHeadTo(to, cdtrcMmid);
        appHdr.setTo(to);
        fpsevnp.setAppHdr(appHdr);
        fpsevnp.setPaymentCancellation(document056);
        try {
            String xml = ConvertUtils.marshal(fpsevnp, true);
            String username = "";
            String password = "";
            String partition = "", method = "", alias = "";
            String xmlSign = fpsParamService.xmlStringSign(xml);
            //xmlSignService.xmlStringSign(xml,username,password,partition,method,alias);
            String fileUrl = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName();
            FileUtils.write(xmlSign, fileUrl);

            String ip = param.getHostAdr();
            String url = ip + "/fast/v1/payment/credit-cancellation/receive";
            log.info("outgoing camt056 xml :" + xmlSign );
            HttpClientUtils.doPost(url, xmlSign);

            messagePacsService.saveMessage(fpsevnp, fileUrl, SysParamsContst.OUTWARD);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Async("asyncTaskExecutor")
    public void sendCamt056(HdrAndData hdrAndData){
        Map<String,Object> map = new HashMap<>();
        FpsParam param = fpsParamService.findById();
        String msgId = MessageUtils.genMsgId();
        map.put("assgnmtId", MessageUtils.genMsgId());
        map.put("assgnrMmid",hdrAndData.getAppHdr().getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());

        map.put("assgneMmid",hdrAndData.getAppHdr().getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
        map.put("creDtTm", XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        map.put("caseId",MessageUtils.genMsgId());
        map.put("cretrMmid",hdrAndData.getAppHdr().getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
        map.put("orgnlMsgId",hdrAndData.getPaymentStatus().getFIToFIPmtStsRpt().getOrgnlGrpInfAndSts().getOrgnlMsgId());
        map.put("orgnlMsgNmId",hdrAndData.getPaymentStatus().getFIToFIPmtStsRpt().getOrgnlGrpInfAndSts().getOrgnlMsgNmId());
        map.put("orgnlInstrId",hdrAndData.getPaymentStatus().getFIToFIPmtStsRpt().getTxInfAndSts().get(0).getOrgnlInstrId());
        map.put("orgnlEndToEndId",MessageUtils.getEndToEndId());
        map.put("orgnlTxId",MessageUtils.getTxId());
        map.put("orgnlClrSysRef", "001");
        map.put("orgnlIntrBkSttlmAmt","20");
        map.put("ccy","USD");
        map.put("orgnlIntrBkSttlmDt",hdrAndData.getPaymentStatus().getFIToFIPmtStsRpt().getOrgnlGrpInfAndSts().getOrgnlCreDtTm());
        map.put("prtry","4021"); //超时代码
        iso.camt_056_001.Document document056 = new iso.camt_056_001.Document();
        document056 = (iso.camt_056_001.Document) dbToXmlService.DbToXml(map, document056);
        HdrAndData fpsevnp = new HdrAndData();
        Party9Choice fr = new Party9Choice();
        MessageHeadUtils.fillHeadFrom(fr, hdrAndData.getAppHdr().getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
        BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();

        appHdr.setBizMsgIdr(msgId);
        appHdr.setMsgDefIdr(G3MessageTypes.CAMT_056_001_01);
        appHdr.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        appHdr.setFr(fr);
        Party9Choice to = new Party9Choice();
        MessageHeadUtils.fillHeadTo(to, hdrAndData.getAppHdr().getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
        appHdr.setTo(to);
        fpsevnp.setAppHdr(appHdr);
        fpsevnp.setPaymentCancellation(document056);
        try {
            String xml = ConvertUtils.marshal(fpsevnp, true);
            String username = "";
            String password = "";
            String partition = "", method = "", alias = "";
            String xmlSign = fpsParamService.xmlStringSign(xml);
            //xmlSignService.xmlStringSign(xml,username,password,partition,method,alias);
            String fileUrl = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName();
            FileUtils.write(xmlSign, fileUrl);

            String ip = param.getHostAdr();
            String url = ip + "/fast/v1/payment/credit-cancellation/receive";
            log.info("outgoing camt056 xml :" + xmlSign );
            HttpClientUtils.doPost(url, xmlSign);

            messagePacsService.saveMessage(fpsevnp, fileUrl, SysParamsContst.OUTWARD);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
