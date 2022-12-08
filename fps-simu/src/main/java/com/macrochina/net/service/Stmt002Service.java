package com.macrochina.net.service;

import bcsis.HdrAndData;
import com.macrochina.net.dto.Admi004Dto;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import iso.head_001_001.*;
import iso.stmt_002_001.ClearingSystemMemberIdentification2;
import iso.stmt_002_001.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Slf4j
@Service
public class Stmt002Service {

    private final MessagePacsService messagePacsService;
    private final FpsParamService fpsParamService;
    private final Admi004Service admi004Service;

    Stmt002Service(MessagePacsService messagePacsService, FpsParamService fpsParamService, Admi004Service admi004Service) {
        this.messagePacsService = messagePacsService;
        this.fpsParamService = fpsParamService;
        this.admi004Service = admi004Service;
    }

    @Async("asyncTaskExecutor")
    public void ack(HdrAndData fpsMsg,String url, BizRuleSet answerSet) {
        FpsParam param = fpsParamService.findById();
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 oriHead = fpsMsg.getAppHdr();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        String mgId = MessageUtils.getCTxnId(param.getSendBank());
        fillHeader(oriHead, retHead,param,mgId);
        hdrAndData.setAppHdr(retHead);
        iso.stmt_001_001.Document origDoc = fpsMsg.getBankStatementRequest();
        messagePacsService.saveMessageStmt001(fpsMsg,url,SysParamsContst.INWARD);
        createDocument(origDoc,oriHead,hdrAndData,answerSet,param,mgId);
    }

    private void createDocument(iso.stmt_001_001.Document origDoc, BusinessApplicationHeaderV01 origHead,HdrAndData hdrAndData, BizRuleSet answerSet,FpsParam param,String mgId) {
        try {
            Document doc = new Document();
            BankStmtResp bankStmtResp = new BankStmtResp();

            GrpHdr grpHdr = new GrpHdr();
            grpHdr.setMsgId(mgId);
            grpHdr.setCreDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
            bankStmtResp.setGrpHdr(grpHdr);

            StmtResp stmtResp = new StmtResp();
            stmtResp.setCcy(origDoc.getBankStmtReq().getStmtReq().getCcy());
            stmtResp.setOrgnlInstrId(origDoc.getBankStmtReq().getStmtReq().getInstrId());

            BranchAndFinancialInstitutionIdentification4 instgAgt = new BranchAndFinancialInstitutionIdentification4();
            FinancialInstitutionIdentification7 finInstnId = new FinancialInstitutionIdentification7();
            ClearingSystemMemberIdentification2 clrSysMmbId = new ClearingSystemMemberIdentification2();
            if(origDoc.getBankStmtReq() != null){
                if(origDoc.getBankStmtReq().getStmtReq() != null){
                    if(origDoc.getBankStmtReq().getStmtReq().getInstgAgt() != null) {
                        if (origDoc.getBankStmtReq().getStmtReq().getInstgAgt().getFinInstnId() != null) {
                            if (origDoc.getBankStmtReq().getStmtReq().getInstgAgt().getFinInstnId().getClrSysMmbId() != null) {
                                if (origDoc.getBankStmtReq().getStmtReq().getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId() != null) {
                                    clrSysMmbId.setMmbId(origDoc.getBankStmtReq().getStmtReq().getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId());
                                }
                            }
                        }
                    }
                }
            }
            finInstnId.setClrSysMmbId(clrSysMmbId);
            instgAgt.setFinInstnId(finInstnId);
            stmtResp.setInstgAgt(instgAgt);

            BranchAndFinancialInstitutionIdentification4 instdAgt = new BranchAndFinancialInstitutionIdentification4();
            FinancialInstitutionIdentification7 finInstnId2 = new FinancialInstitutionIdentification7();
            ClearingSystemMemberIdentification2 clrSysMmbId2 = new ClearingSystemMemberIdentification2();
            if(origDoc.getBankStmtReq() != null){
                if(origDoc.getBankStmtReq().getStmtReq() != null){
                    if(origDoc.getBankStmtReq().getStmtReq().getInstdAgt() != null){
                        if(origDoc.getBankStmtReq().getStmtReq().getInstdAgt().getFinInstnId() != null){
                            if(origDoc.getBankStmtReq().getStmtReq().getInstdAgt().getFinInstnId().getClrSysMmbId() != null){
                                if(origDoc.getBankStmtReq().getStmtReq().getInstdAgt().getFinInstnId().getClrSysMmbId().getMmbId() != null){
                                    clrSysMmbId2.setMmbId(origDoc.getBankStmtReq().getStmtReq().getInstdAgt().getFinInstnId().getClrSysMmbId().getMmbId());
                                }
                            }
                        }
                    }
                }
            }
            finInstnId2.setClrSysMmbId(clrSysMmbId2);
            instdAgt.setFinInstnId(finInstnId2);
            stmtResp.setInstdAgt(instdAgt);
            stmtResp.setSts(TransactionGroupStatus3Code.ACTC);
            if(answerSet != null){
                String RespStatus = answerSet.getResp_status();
                if(RespStatus.equals("Reject")){
                    stmtResp.setSts(TransactionGroupStatus3Code.RJCT);
                }
            }
            bankStmtResp.setStmtResp(stmtResp);
            doc.setBankStmtResp(bankStmtResp);
            hdrAndData.setBankStatementResponse(doc);
            // 异常处理
            String xml = ConvertUtils.marshal(hdrAndData, true);
            log.info("outgoing stmt002 xml ={}", xml);
            // 加签验证
            String signXml =  fpsParamService.xmlStringSign(xml);
            // 写入本地文件
            String url = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
            FileUtils.write(signXml,url);
            messagePacsService.saveMessage(hdrAndData,url,SysParamsContst.OUTWARD);
            String urls = param.getHostAdr() + "/fast/v1/report/ad-hoc-statement/response";
            sendTask(signXml,urls);

            Admi004Dto admi004Dto = new Admi004Dto();
            admi004Dto.setEvtCd("986");
            List<String> evtParam = new ArrayList<>();
            evtParam.add("Notification");
            evtParam.add(param.getAcceptBank());
            evtParam.add("BANK");
            evtParam.add("COMPLETED");//1、COMPLETED 2、REJECTED 3、UNABLE TO PRODUCE
            admi004Dto.setEvtParam(evtParam);
            admi004Service.receiveNotification(admi004Dto);
        } catch (Exception e) {
            log.error("HttpClient send error!", e);
        }
    }

    public void sendTask(String xml,String url){
        HttpClientUtils.doPost(url,xml);
    }

    private void fillHeader(BusinessApplicationHeaderV01 origHead, BusinessApplicationHeaderV01 retHead,FpsParam param,String mgId) {
        retHead.setBizMsgIdr(mgId);
        retHead.setMsgDefIdr(G3MessageTypes.STMT_002_001_01);
        retHead.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        retHead.setBizSvc(origHead.getBizSvc());
        Party9Choice fr = new Party9Choice();
        fillHead(fr, origHead.getTo().getFIId().getFinInstnId().getBICFI());
        retHead.setFr(fr);
        Party9Choice to = new Party9Choice();
        fillHead(to, origHead.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
        retHead.setTo(to);
    }

    private void fillHead(Party9Choice from, String mid) {
        BranchAndFinancialInstitutionIdentification5 fiid = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 finsId = new FinancialInstitutionIdentification8();
        iso.head_001_001.ClearingSystemMemberIdentification2 mbid = new iso.head_001_001.ClearingSystemMemberIdentification2();
        mbid.setMmbId(mid);
        finsId.setClrSysMmbId(mbid);
        fiid.setFinInstnId(finsId);
        from.setFIId(fiid);
    }
}
