package com.macrochina.net.service;

import address.head_001_001.BranchAndFinancialInstitutionIdentification5;
import address.head_001_001.FinancialInstitutionIdentification8;
import address.head_001_001.*;
import address.prxy_007_001.Document;
import address.prxy_008_001.*;
import adrs.HdrAndData;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;


@Slf4j
@Service
public class Prxy008Service {

    private final MessagePacsService messagePacsService;
    private final FpsParamService fpsParamService;

    @Autowired
    private SchemaService schemaService;

    Prxy008Service(MessagePacsService messagePacsService, FpsParamService fpsParamService) {
        this.messagePacsService = messagePacsService;
        this.fpsParamService = fpsParamService;
    }

    @Async("asyncTaskExecutor")
    public void ack008(HdrAndData fpsMsg, String url, BizRuleSet bizRuleSet, HttpServletResponse response) {

        FpsParam param = fpsParamService.findById();
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 oriHead = fpsMsg.getAppHdr();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        String mgId = MessageUtils.getCTxnId(param.getSendBank());
        fillHeader(oriHead, retHead,param,mgId);
        hdrAndData.setAppHdr(retHead);
        Document origDoc = fpsMsg.getParticipantReportRequest();
        messagePacsService.saveMessagePrxy007(fpsMsg,url,SysParamsContst.INWARD);
        createDocument(origDoc,oriHead,hdrAndData,bizRuleSet,param,mgId,response);
    }

    private void createDocument(Document origDoc, BusinessApplicationHeaderV01 origHead, HdrAndData hdrAndData, BizRuleSet answerSet, FpsParam param, String mgId, HttpServletResponse response) {
        try {
            address.prxy_008_001.Document document = new address.prxy_008_001.Document();
            ParticipantReportResponseV01 ptcptRptRspn = new ParticipantReportResponseV01();
            String bicfi = origDoc.getPtcptRptReq().getGrpHdr().getMsgSndr().getAgt().getFinInstnId().getBICFI();
            GroupHeader60 grpHdr = new GroupHeader60();
            grpHdr.setMsgId(mgId);
            Party12Choice msgRcpt = new Party12Choice();
            address.prxy_008_001.BranchAndFinancialInstitutionIdentification5 agt = new address.prxy_008_001.BranchAndFinancialInstitutionIdentification5();
            address.prxy_008_001.FinancialInstitutionIdentification8 finInstnId = new address.prxy_008_001.FinancialInstitutionIdentification8();
            finInstnId.setBICFI(bicfi);
            agt.setFinInstnId(finInstnId);
            msgRcpt.setAgt(agt);
            grpHdr.setMsgRcpt(msgRcpt);
            grpHdr.setCreDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
            ptcptRptRspn.setGrpHdr(grpHdr);

            DateAndMonthChoice rptPrd = new DateAndMonthChoice();
            XMLGregorianCalendar dt = origDoc.getPtcptRptReq().getRptPrd().getDt();
            if(null != dt){
                rptPrd.setDt(origDoc.getPtcptRptReq().getRptPrd().getDt());
            }else {
                rptPrd.setMnth(origDoc.getPtcptRptReq().getRptPrd().getMnth());
            }
            ptcptRptRspn.setRptPrd(rptPrd);

            address.prxy_008_001.BranchAndFinancialInstitutionIdentification5 ptcpt = new address.prxy_008_001.BranchAndFinancialInstitutionIdentification5();
            address.prxy_008_001.FinancialInstitutionIdentification8 finInstnId2 = new address.prxy_008_001.FinancialInstitutionIdentification8();
            finInstnId2.setBICFI(bicfi);
            ptcpt.setFinInstnId(finInstnId2);
            ptcptRptRspn.setPtcpt(ptcpt);

            ProprietaryReportData4 prtryData = new ProprietaryReportData4();
            ProprietaryData3 data = new ProprietaryData3();
            ActivitySummaryReport01 actvtySummryRpt = new ActivitySummaryReport01();
            prtryData.setTp(ProxyReportType.ACTV);

            actvtySummryRpt.setLookUpReqd("17");
            actvtySummryRpt.setLookUpOnOwnCstmr("28");
            actvtySummryRpt.setPrxyWthZeroLookUp("187");
            actvtySummryRpt.setSucssfullyRegn("4");
            actvtySummryRpt.setUsucssfullyRegn("1");
            actvtySummryRpt.setDeactvdRegn("3");
            actvtySummryRpt.setArchvdRegn("0");
            actvtySummryRpt.setLiveRegn("210");

            if(null != answerSet){
                String RespStatus = answerSet.getResp_status();
                if(RespStatus.equals("Reject")){
                    actvtySummryRpt.setLookUpReqd("0");
                    actvtySummryRpt.setLookUpOnOwnCstmr("0");
                    actvtySummryRpt.setPrxyWthZeroLookUp("0");
                    actvtySummryRpt.setSucssfullyRegn("0");
                    actvtySummryRpt.setUsucssfullyRegn("0");
                    actvtySummryRpt.setDeactvdRegn("0");
                    actvtySummryRpt.setArchvdRegn("0");
                    actvtySummryRpt.setLiveRegn("0");
                    StatusReason1Choice stsRsn = new StatusReason1Choice();
                    stsRsn.setPrtry(answerSet.getRsnCode());
                    //stsRsn.setCd(TransactionRejectReason2Code.RC_02);
                    actvtySummryRpt.setStsRsn(stsRsn);
                }
            }
            data.setActvtySummryRpt(actvtySummryRpt);
            prtryData.setData(data);
            ptcptRptRspn.setPrtryData(prtryData);

            document.setPtcptRptRspn(ptcptRptRspn);
            hdrAndData.setParticipantReportResponse(document);
            // 异常处理
            String xml = ConvertAdrsUtils.marshal(hdrAndData, true);
            log.info("outgoing prxy008 xml ={}", xml);
            // 加签验证
            String signXml =fpsParamService.xmlStringSign(xml);
            // 写入本地文件
            String url = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
            FileUtils.write(signXml,url);
//            Boolean bool = schemaService.validateXmlByXsd2(signXml, SysParamsContst.SCHEMA_PRXY,FileUtils.ADRS_SCHEMA);
            messagePacsService.saveMessagePrxy(hdrAndData,url,SysParamsContst.OUTWARD);
            String urls = param.getPaynowHost() + "/paynow/v1/report/response";
            sendTask(signXml,urls,answerSet,response);
        } catch (Exception e) {
            log.error("HttpClient send error!", e);
        }
    }

    public void sendTask(String xml, String url, BizRuleSet bizRuleSet, HttpServletResponse response){
        HttpClientUtils.sendCallback(url, xml,bizRuleSet);
    }

    private void fillHeader(BusinessApplicationHeaderV01 origHead, BusinessApplicationHeaderV01 retHead,FpsParam param,String mgId) {
        retHead.setBizMsgIdr(mgId);
        retHead.setMsgDefIdr(MessageTypesCAS.PRXY_008_001_01);
        retHead.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        Party9Choice fr = new Party9Choice();
        fillHead(fr, origHead.getTo().getFIId().getFinInstnId().getBICFI());
        retHead.setFr(fr);
        Party9Choice to = new Party9Choice();
        fillHead(to, origHead.getFr().getFIId().getFinInstnId().getBICFI());
        retHead.setTo(to);
    }

    private void fillHead(Party9Choice from, String mid) {
        BranchAndFinancialInstitutionIdentification5 fiid = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 finsId = new FinancialInstitutionIdentification8();
        finsId.setBICFI(mid);
        fiid.setFinInstnId(finsId);
        from.setFIId(fiid);
    }
}
