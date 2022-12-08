package com.macrochina.net.service;

import bcsis.HdrAndData;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import iso.head_001_001.ClearingSystemMemberIdentification2;
import iso.head_001_001.*;
import iso.pacs_002_001.*;
import iso.pacs_008_001.CreditTransferTransactionInformation11;
import iso.pacs_008_001.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Slf4j
@Service
public class MsgAckPacs002Service {

    private final MessagePacsService messagePacsService;
    private final FpsParamService fpsParamService;

    MsgAckPacs002Service(MessagePacsService messagePacsService, FpsParamService fpsParamService) {
        this.messagePacsService = messagePacsService;
        this.fpsParamService = fpsParamService;
    }

    /**
     * 回复pacs002
     */
    @Async("asyncTaskExecutor")
    public void ack(HdrAndData fpsMsg, String url, BizRuleSet bizRuleSet) {

        //保存messagePacs
        messagePacsService.saveMessagePacs008(fpsMsg, SysParamsContst.INWARD);
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 oriHead = fpsMsg.getAppHdr();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        FpsParam param = fpsParamService.findById();
        String mgId = MessageUtils.getCTxnId(param.getSendBank());
        fillHeader(oriHead, retHead, param, mgId);
        hdrAndData.setAppHdr(retHead);
        Document origDoc = fpsMsg.getCreditTransfer();
        createDocument(origDoc, oriHead, bizRuleSet, mgId, hdrAndData);
    }

    /**
     * 组 pacs002 Document
     */
    private void createDocument(Document origDoc, BusinessApplicationHeaderV01 origHead, BizRuleSet bizAnsweSet, String mgId, HdrAndData hdrAndData) {
        log.debug("pacs002 found orignal entity to update status ");
        List<CreditTransferTransactionInformation11> cdtTrfTxInf = origDoc.getFIToFICstmrCdtTrf().getCdtTrfTxInf();
        if (!CollectionUtils.isEmpty(cdtTrfTxInf)) {
            for (CreditTransferTransactionInformation11 info11 : cdtTrfTxInf) {
                try {
                    iso.pacs_002_001.Document document = new iso.pacs_002_001.Document();
                    FIToFIPaymentStatusReportV03 fiToFIPmtStsRpt = new FIToFIPaymentStatusReportV03();//(一)

                    GroupHeader37 grpHdr = new GroupHeader37();//1
                    grpHdr.setMsgId(mgId);
                    grpHdr.setCreDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
                    fiToFIPmtStsRpt.setGrpHdr(grpHdr);

                    OriginalGroupInformation20 orgnlGrpInfAndSts = new OriginalGroupInformation20();//2
                    orgnlGrpInfAndSts.setOrgnlMsgId(origDoc.getFIToFICstmrCdtTrf().getGrpHdr().getMsgId());
                    orgnlGrpInfAndSts.setOrgnlMsgNmId(origHead.getBizMsgIdr());
                    orgnlGrpInfAndSts.setOrgnlCreDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
                    orgnlGrpInfAndSts.setOrgnlNbOfTxs("1");
                    fiToFIPmtStsRpt.setOrgnlGrpInfAndSts(orgnlGrpInfAndSts);

                    List<PaymentTransactionInformation26> txInfAndSts = new ArrayList<>(); //3
                    PaymentTransactionInformation26 information26 = new PaymentTransactionInformation26();
                    information26.setOrgnlInstrId(info11.getPmtId().getInstrId());
                    information26.setTxSts(TransactionIndividualStatus3Code.ACTC);
                    if (bizAnsweSet != null) {
                        String RespStatus = bizAnsweSet.getResp_status();
                        if (RespStatus.equals("Reject")) {
                            information26.setTxSts(TransactionIndividualStatus3Code.RJCT);
                            StatusReasonInformation8 info8 = new StatusReasonInformation8();
                            StatusReason6Choice choice = new StatusReason6Choice();
                            choice.setPrtry(bizAnsweSet.getRsnCode());
                            info8.setRsn(choice);
                            information26.getStsRsnInf().add(info8);//拒绝原因
                        }
                    }
                    information26.setAccptncDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
                    information26.setClrSysRef("001");//清算系统参考号
                    BranchAndFinancialInstitutionIdentification4 instgAgt = new BranchAndFinancialInstitutionIdentification4();
                    FinancialInstitutionIdentification7 finInstnId = new FinancialInstitutionIdentification7();
                    iso.pacs_002_001.ClearingSystemMemberIdentification2 clrSysMmbId = new iso.pacs_002_001.ClearingSystemMemberIdentification2();
                    clrSysMmbId.setMmbId(info11.getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId());
                    finInstnId.setClrSysMmbId(clrSysMmbId);
                    instgAgt.setFinInstnId(finInstnId);
                    information26.setInstgAgt(instgAgt);
                    BranchAndFinancialInstitutionIdentification4 instdAgt = new BranchAndFinancialInstitutionIdentification4();
                    FinancialInstitutionIdentification7 finInstnId2 = new FinancialInstitutionIdentification7();
                    iso.pacs_002_001.ClearingSystemMemberIdentification2 clrSysMmbId2 = new iso.pacs_002_001.ClearingSystemMemberIdentification2();
                    clrSysMmbId2.setMmbId(info11.getInstdAgt().getFinInstnId().getClrSysMmbId().getMmbId());
                    finInstnId2.setClrSysMmbId(clrSysMmbId2);
                    instdAgt.setFinInstnId(finInstnId2);
                    information26.setInstdAgt(instdAgt);
                    OriginalTransactionReference13 orgnlTxRef = new OriginalTransactionReference13();
                    orgnlTxRef.setIntrBkSttlmDt(XmlIsoDateUtil.convertToXMLGregorianCalendarDate(Calendar.getInstance()));
                    information26.setOrgnlTxRef(orgnlTxRef);
                    txInfAndSts.add(information26);
                    fiToFIPmtStsRpt.setTxInfAndSts(txInfAndSts);
                    document.setFIToFIPmtStsRpt(fiToFIPmtStsRpt);
                    hdrAndData.setPaymentStatus(document);
                    // 异常处理
                    String xml = ConvertUtils.marshal(hdrAndData, true);
                    log.info("outgoing pacs002 xml ={}", xml);
                    // 加签验证
                    String signXml = fpsParamService.xmlStringSign(xml);
                    // 写入本地文件
                    String url = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
                    FileUtils.write(signXml, url);
                    messagePacsService.saveMessage(hdrAndData, url, SysParamsContst.OUTWARD);
//                    Boolean bool = schemaService.validateXmlByXsd2(signXml, SysParamsContst.SCHEMA_PACS_002,FileUtils.FPS_SCHEMA);
//                    if(bool){
                    sendTask(signXml, bizAnsweSet);
//                    }
                } catch (Exception e) {
                    log.error("HttpClient send error!", e);
                }
            }
        }
    }

    public void sendTask(String xml, BizRuleSet bizRuleSet) {
        String host = fpsParamService.findById().getHostAdr();
        String url = host + "/fast/v1/payment/credit-transfer-status/receive";
//        String url="http://localhost:8080/fast/v1/payment/credit-transfer-status/receive";
        log.info(host);
        HttpClientUtils.sendCallback(url, xml,bizRuleSet);
    }

    private void fillHeader(BusinessApplicationHeaderV01 origHead, BusinessApplicationHeaderV01 retHead, FpsParam param, String mgId) {
        retHead.setBizMsgIdr(mgId);
        retHead.setMsgDefIdr(G3MessageTypes.PACS_002_001_03);
        retHead.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        retHead.setBizSvc(origHead.getBizSvc());
        Party9Choice fr = new Party9Choice();
        fillHead(fr, origHead.getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
        retHead.setFr(fr);
        Party9Choice to = new Party9Choice();
        fillHead(to, origHead.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
        retHead.setTo(to);
    }

    private void fillHead(Party9Choice from, String mid) {
        BranchAndFinancialInstitutionIdentification5 fiid = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 finsId = new FinancialInstitutionIdentification8();
        ClearingSystemMemberIdentification2 mbid = new ClearingSystemMemberIdentification2();
        mbid.setMmbId(mid);
        finsId.setClrSysMmbId(mbid);
        fiid.setFinInstnId(finsId);
        from.setFIId(fiid);
    }
}
