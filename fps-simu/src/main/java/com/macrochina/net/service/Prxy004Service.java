package com.macrochina.net.service;//package com.macrochina.net.service;

import address.head_001_001.BranchAndFinancialInstitutionIdentification5;
import address.head_001_001.FinancialInstitutionIdentification8;
import address.head_001_001.*;
import address.prxy_003_001.Document;
import address.prxy_004_001.*;
import adrs.HdrAndData;
import com.macrochina.net.dao.AddressRepository;
import com.macrochina.net.entity.Address;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Calendar;


@Slf4j
@Service
public class Prxy004Service {

    private final MessagePacsService messagePacsService;
    private final FpsParamService fpsParamService;
    private final AddressService addressService;
    private final AddressRepository addressRepository;

    @Autowired
    private SchemaService schemaService;

    Prxy004Service(MessagePacsService messagePacsService, FpsParamService fpsParamService, AddressService addressService, AddressRepository addressRepository) {
        this.messagePacsService = messagePacsService;
        this.fpsParamService = fpsParamService;
        this.addressService = addressService;
        this.addressRepository = addressRepository;
    }

    @Async("asyncTaskExecutor")
    public void ack004(HdrAndData fpsMsg, String url, BizRuleSet answerSet) {
        FpsParam param = fpsParamService.findById();
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 oriHead = fpsMsg.getAppHdr();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        String mgId = MessageUtils.getCTxnId(param.getSendBank());
        fillHeader(oriHead, retHead, param, mgId);
        hdrAndData.setAppHdr(retHead);
        Document origDoc = fpsMsg.getProxyLookUp();
        messagePacsService.saveMessagePrxy003(fpsMsg, url, SysParamsContst.INWARD);
        createDocument(origDoc, oriHead, hdrAndData, answerSet, param, mgId,fpsMsg);
    }

    private void createDocument(Document origDoc, BusinessApplicationHeaderV01 origHead, HdrAndData hdrAndData, BizRuleSet answerSet, FpsParam param, String mgId,HdrAndData fpsMsg) {
        try {
            address.prxy_004_001.Document document = new address.prxy_004_001.Document();
            ProxyLookUpResponseV01 prxyLookUpRspn = new ProxyLookUpResponseV01();
            GroupHeader60 grpHdr = new GroupHeader60();
            grpHdr.setMsgId(MessageUtils.genMsgId());
            Party12Choice msgRcpt = new Party12Choice();
            address.prxy_004_001.BranchAndFinancialInstitutionIdentification5 agt = new address.prxy_004_001.BranchAndFinancialInstitutionIdentification5();
            address.prxy_004_001.FinancialInstitutionIdentification8 finInstnId = new address.prxy_004_001.FinancialInstitutionIdentification8();
//            String bicfi = address.getAgt();
            String bicfi = origDoc.getPrxyLookUp().getGrpHdr().getMsgSndr().getAgt().getFinInstnId().getBICFI();
            finInstnId.setBICFI(bicfi);
            agt.setFinInstnId(finInstnId);
            msgRcpt.setAgt(agt);
            grpHdr.setMsgRcpt(msgRcpt);
            grpHdr.setCreDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
            prxyLookUpRspn.setGrpHdr(grpHdr);

            OriginalGroupInformation3 orgnlGrpInf = new OriginalGroupInformation3();
            orgnlGrpInf.setOrgnlCreDtTm(origDoc.getPrxyLookUp().getGrpHdr().getCreDtTm());
            orgnlGrpInf.setOrgnlMsgId(origHead.getBizMsgIdr());
            orgnlGrpInf.setOrgnlMsgNmId(origHead.getMsgDefIdr().value());
            prxyLookUpRspn.setOrgnlGrpInf(orgnlGrpInf);

            ProxyLookUpResponse1 lkUpRspn = new ProxyLookUpResponse1();
            lkUpRspn.setOrgnlId(MessageUtils.getOrgnlId(param.getAcceptBank()));
            ProxyDefintion1 orgnlPrxyRtrvl = new ProxyDefintion1();
            orgnlPrxyRtrvl.setTp(origDoc.getPrxyLookUp().getLookUp().getPrxyOnly().getPrxyRtrvl().getTp());
            String val = origDoc.getPrxyLookUp().getLookUp().getPrxyOnly().getPrxyRtrvl().getVal();
            orgnlPrxyRtrvl.setVal(val);
            lkUpRspn.setOrgnlPrxyRtrvl(orgnlPrxyRtrvl);

            ProxyLookUpRegistration1 regnRspn = new ProxyLookUpRegistration1();
            regnRspn.setPrxRspnSts(ProxyStatusCode.ACTC);//规则验证
            regnRspn.setLkUpRef(RandomStringUtils.randomNumeric(12));//todo 需要确认
            if (answerSet != null) {
                String RespStatus = answerSet.getResp_status();
                if (RespStatus.equals("Reject")) {
                    regnRspn.setPrxRspnSts(ProxyStatusCode.RJCT);
                    ProxyStatusChoice stsRsnInf = new ProxyStatusChoice();
                    stsRsnInf.setPrtry(answerSet.getRsnCode());
                    regnRspn.setStsRsnInf(stsRsnInf);
                    regnRspn.setLkUpRef(RandomStringUtils.randomNumeric(12));
                }
            }
            //查找数据库注册信息
            String to = fpsMsg.getAppHdr().getTo().getFIId().getFinInstnId().getBICFI();
            Address address = addressRepository.findByPrxy(val);
            if (null != address) {
                GroupHeader60 grpHdr2 = new GroupHeader60();
                grpHdr2.setMsgId(MessageUtils.genMsgId());
                Party12Choice msgRcpt2 = new Party12Choice();
                address.prxy_004_001.BranchAndFinancialInstitutionIdentification5 agt2 = new address.prxy_004_001.BranchAndFinancialInstitutionIdentification5();
                address.prxy_004_001.FinancialInstitutionIdentification8 finInstnId2 = new address.prxy_004_001.FinancialInstitutionIdentification8();
                String bicfi2 = address.getAgt();
                finInstnId2.setBICFI(bicfi2);
                agt2.setFinInstnId(finInstnId2);
                msgRcpt2.setAgt(agt2);
                grpHdr2.setMsgRcpt(msgRcpt2);
                grpHdr2.setCreDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
                prxyLookUpRspn.setGrpHdr(grpHdr2);

                ProxyDefintion1 prxy = new ProxyDefintion1();
                prxy.setTp(address.getTp());
                prxy.setVal(address.getVal());
                regnRspn.setPrxy(prxy);

                ProxyLookUpAccount1 regn = new ProxyLookUpAccount1();
                String regnId = address.getRegnId();
                if (StringUtils.isNotBlank(regnId)) {
                    regn.setRegnId(regnId);
                }
                String dsplNm = address.getDsplNm();
                if (StringUtils.isNotBlank(dsplNm)) {
                    regn.setDsplNm(dsplNm);
                }
//                address.prxy_004_001.BranchAndFinancialInstitutionIdentification5 agt1 = new address.prxy_004_001.BranchAndFinancialInstitutionIdentification5();
//                address.prxy_004_001.FinancialInstitutionIdentification8 finInstnId1 = new address.prxy_004_001.FinancialInstitutionIdentification8();
//                finInstnId1.setBICFI(bicfi);
//                agt1.setFinInstnId(finInstnId1);
                regn.setAgt(agt);
                CashAccount40 acct = new CashAccount40();
                AccountIdentification4Choice id = new AccountIdentification4Choice();
                GenericAccountIdentification1 othr = new GenericAccountIdentification1();
                othr.setId(address.getAcctId());
                id.setOthr(othr);
                acct.setId(id);
                CashAccountType2ChoiceProxy tp = new CashAccountType2ChoiceProxy();
                if (address.getAcctPrtry() != null) {
                    tp.setPrtry(ProxyAccountType.fromValue(address.getAcctPrtry()));
                }
                acct.setTp(tp);
                acct.setNm(address.getNm());
//                regn.setRegnSts(ProxyRegistrationStatusCode.ACTV);
                regn.setPreAuthrsd(address.isPreAuthrsd());
                regn.setAcct(acct);
                regnRspn.setRegn(regn);
            }
            if (address == null) {
                regnRspn.setPrxRspnSts(ProxyStatusCode.RJCT);
                ProxyStatusChoice stsRsnInf = new ProxyStatusChoice();
                stsRsnInf.setPrtry("801");
                regnRspn.setStsRsnInf(stsRsnInf);
                regnRspn.setLkUpRef(RandomStringUtils.randomNumeric(12));
            }

            lkUpRspn.setRegnRspn(regnRspn);

            prxyLookUpRspn.setLkUpRspn(lkUpRspn);


            document.setPrxyLookUpRspn(prxyLookUpRspn);
            hdrAndData.setProxyLookUpResponse(document);
            // 异常处理
            String xml = ConvertAdrsUtils.marshal(hdrAndData, true);
            log.info("outgoing prxy004 xml ={}", xml);
            // 加签验证
            String signXml = fpsParamService.xmlStringSign(xml);
            // 写入本地文件
            String url = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
            FileUtils.write(signXml, url);
            messagePacsService.saveMessagePrxy(hdrAndData, url, SysParamsContst.OUTWARD);
//            Boolean bool = schemaService.validateXmlByXsd2(signXml, SysParamsContst.SCHEMA_PRXY,FileUtils.ADRS_SCHEMA);
//            if(bool){
                String urls = param.getPaynowHost() + "/paynow/v1/lookup/response";
                sendTask(signXml, urls,answerSet);
//            }
        } catch (Exception e) {
            log.error("HttpClient send error!", e);
        }
    }

    public void sendTask(String xml, String url,BizRuleSet bizRuleSet) {
        HttpClientUtils.sendCallback(url, xml,bizRuleSet);
    }


    private void fillHeader(BusinessApplicationHeaderV01 origHead, BusinessApplicationHeaderV01 retHead, FpsParam param, String mgId) {
        retHead.setBizMsgIdr(mgId);
        retHead.setMsgDefIdr(MessageTypesCAS.PRXY_004_001_01);
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
