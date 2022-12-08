package com.macrochina.net.service;

import address.head_001_001.MessageTypesCAS;
import address.prxy_001_001.*;
import address.prxy_003_001.GroupHeader69;
import address.prxy_003_001.ProxyLookUp11;
import address.prxy_003_001.ProxyLookUpChoice1;
import bcsis.HdrAndData;
import com.macrochina.net.dao.AddressRepository;
import com.macrochina.net.dao.MessagePacsRepository;
import com.macrochina.net.dao.MessageRepository;
import com.macrochina.net.dto.Admi002Dto;
import com.macrochina.net.dto.MessagePacsDto;
import com.macrochina.net.entity.Address;
import com.macrochina.net.entity.Message;
import com.macrochina.net.entity.MessagePacs;
import com.macrochina.net.util.MessageUtils;
import com.macrochina.net.util.XmlIsoDateUtil;
import com.macrochina.net.utils.SysParamsContst;
import iso.head_001_001.BusinessApplicationHeaderV01;
import iso.head_001_001.G3MessageTypes;
import iso.pacs_008_001.CreditTransferTransactionInformation11;
import iso.pacs_008_001.Document;
import iso.pacs_008_001.GroupHeader33;
import iso.stmt_001_001.GrpHdr;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
public class MessagePacsService {

    final MessageRepository messageRepository;
    final AddressRepository bizAnsweSetRepository;
    final MessagePacsRepository messagePacsRepository;
    final AddressRepository addressRepository;
    final Admi002Service admi002Service;
    final FpsParamService fpsParamService;

    MessagePacsService(MessageRepository messageRepository, AddressRepository bizAnsweSetRepository, MessagePacsRepository messagePacsRepository, AddressRepository addressRepository, Admi002Service admi002Service, FpsParamService fpsParamService) {
        this.messageRepository = messageRepository;
        this.bizAnsweSetRepository = bizAnsweSetRepository;
        this.messagePacsRepository = messagePacsRepository;
        this.addressRepository = addressRepository;
        this.admi002Service = admi002Service;
        this.fpsParamService = fpsParamService;
    }

    public void syntaxError(String msg, HdrAndData fpsMsg) {
        Admi002Dto dto = new Admi002Dto();
        dto.setRef(MessageUtils.getCTxnId(fpsParamService.findById().getSendBank()));
        dto.setRjctgPtyRsn("650");
        dto.setAddtlData(msg);
        admi002Service.receive(dto,"Fast",fpsMsg.getAppHdr().getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId(),fpsMsg.getAppHdr().getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
    }

    public void signError(String msg,HdrAndData fpsMsg) {
        Admi002Dto dto = new Admi002Dto();
        dto.setRef(fpsMsg.getAppHdr().getBizMsgIdr());
        dto.setRjctgPtyRsn("690");
        dto.setAddtlData(msg);
        admi002Service.receive(dto,"Fast",fpsMsg.getAppHdr().getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId(),fpsMsg.getAppHdr().getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
    }

    /**
     * save message
     */
    public void saveMessage(HdrAndData fpsMsgEnv, String url,String dirct){
        Message fpsMsg = new Message();
        BusinessApplicationHeaderV01 appHdr = fpsMsgEnv.getAppHdr();
        if(appHdr != null){
            String fr = appHdr.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId();
            String to = appHdr.getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId();
            fpsMsg.setToInstn(to);
            fpsMsg.setFromInstn(fr);
            String msgId = appHdr.getBizMsgIdr();
            fpsMsg.setMsgId(msgId);
        }
        if(dirct.equals(SysParamsContst.OUTWARD)){
            fpsMsg.setDirct(0);// 0:往帐
        }else {
            fpsMsg.setDirct(1);// 1:来账
        }
        if(fpsMsgEnv.getPaymentStatus() != null){
            if(fpsMsgEnv.getPaymentStatus().getFIToFIPmtStsRpt() != null){
                if(fpsMsgEnv.getPaymentStatus().getFIToFIPmtStsRpt().getOrgnlGrpInfAndSts() != null){
                    String orgMsgId = fpsMsgEnv.getPaymentStatus().getFIToFIPmtStsRpt().getOrgnlGrpInfAndSts().getOrgnlMsgId();
                    if(StringUtils.isNotBlank(orgMsgId)){
                        fpsMsg.setOrgMsgId(orgMsgId);
                    }
                }
            }
        }
        String msgDef = appHdr.getMsgDefIdr().value();
        if(msgDef.equals(G3MessageTypes.PACS_002_001_03)){
            if(fpsMsgEnv.getPaymentStatus().getFIToFIPmtStsRpt().getTxInfAndSts().get(0).getTxSts() != null){
                String value = fpsMsgEnv.getPaymentStatus().getFIToFIPmtStsRpt().getTxInfAndSts().get(0).getTxSts().value();
                if(value.equals("ACTC")){
                    fpsMsg.setState(0);
                }else if(value.equals("RJCT")) {
                    fpsMsg.setState(1);
                    String prtry = fpsMsgEnv.getPaymentStatus().getFIToFIPmtStsRpt().getTxInfAndSts().get(0).getStsRsnInf().get(0).getRsn().getPrtry();
                    if(StringUtils.isNotBlank(prtry)){
                        fpsMsg.setErrorCode(prtry);
                    }
                    String cd = fpsMsgEnv.getPaymentStatus().getFIToFIPmtStsRpt().getTxInfAndSts().get(0).getStsRsnInf().get(0).getRsn().getCd();
                    if(StringUtils.isNotBlank(cd)){
                        fpsMsg.setErrorRsn(cd);
                    }
                }
            }else {
                fpsMsg.setState(1);
            }
        }
        fpsMsg.setMsgDef(msgDef);
        fpsMsg.setMsgFile(url);
        fpsMsg.setTag("Fast");
        messageRepository.save(fpsMsg);
    }

    /**
     * save messagePacs
     */
    public void saveMessagePacs008(HdrAndData fpsMsgEnv,String dirct){
        MessagePacs pacs = new MessagePacs();
        Document transfer = fpsMsgEnv.getCreditTransfer();
        List<CreditTransferTransactionInformation11> cdtTrfTxInf = transfer.getFIToFICstmrCdtTrf().getCdtTrfTxInf();
        BusinessApplicationHeaderV01 appHdr = fpsMsgEnv.getAppHdr();
        GroupHeader33 grpHdr = transfer.getFIToFICstmrCdtTrf().getGrpHdr();
        if(dirct.equals(SysParamsContst.OUTWARD)){
            pacs.setDirct(0);// 0:往帐
        }else {
            pacs.setDirct(1);// 1:来账
        }

        String fr = appHdr.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId();
        String to = appHdr.getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId();
        pacs.setToInstn(to);
        pacs.setFromInstn(fr);
        pacs.setBizMsgIdr(appHdr.getBizMsgIdr());
        pacs.setMsgDef(appHdr.getMsgDefIdr().value());//报文类型
        pacs.setCreDt(XmlIsoDateUtil.convertToStrFort(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance())));//创建时间
        pacs.setMsgId(appHdr.getMsgDefIdr().value());
        pacs.setNbOfTxs(grpHdr.getNbOfTxs());//发送笔数
        for (CreditTransferTransactionInformation11 info : cdtTrfTxInf) {
            pacs.setEndEndId(info.getPmtId().getEndToEndId());//端对端id
            pacs.setInstrId(info.getPmtId().getInstrId());//付款标识
            pacs.setTxId(info.getPmtId().getTxId());//交易ID
            pacs.setSvcLvl(info.getInstdAgt().getFinInstnId().getClrSysMmbId().getMmbId());//服务等级
            pacs.setCcy(info.getIntrBkSttlmAmt().getCcy());//币种
            pacs.setSttlmAmt(info.getIntrBkSttlmAmt().getValue());//结算金额
            XMLGregorianCalendar intrBkSttlmDt = info.getIntrBkSttlmDt();
            if(null != intrBkSttlmDt){
                pacs.setIntrBkSttlmDt(XmlIsoDateUtil.convertToStr(info.getIntrBkSttlmDt()));//结算日期
            }else {
                pacs.setIntrBkSttlmDt(XmlIsoDateUtil.convertToStr(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance())));
            }
            pacs.setChrgBr(info.getChrgBr().value());//收费方式
            pacs.setInstgAgtId(info.getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId());
            pacs.setInstdAgtId(info.getInstdAgt().getFinInstnId().getClrSysMmbId().getMmbId());
            pacs.setDbtrNm(info.getDbtr().getNm());
            pacs.setDbtrAcctId(info.getDbtrAcct().getId().getOthr().getId());
            pacs.setCdtrNm(info.getCdtr().getNm());
            pacs.setCdtrAcctId(info.getCdtrAcct().getId().getOthr().getId());
            pacs.setDbtrAgtId(info.getDbtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());
            pacs.setCdtrAgtId(info.getCdtrAgt().getFinInstnId().getClrSysMmbId().getMmbId());
        }
        messagePacsRepository.save(pacs);
    }

    public void saveMessagePrxy(adrs.HdrAndData fpsMsgEnv, String url, String dirct){
        String fr = fpsMsgEnv.getAppHdr().getFr().getFIId().getFinInstnId().getBICFI();
        String to = fpsMsgEnv.getAppHdr().getTo().getFIId().getFinInstnId().getBICFI();
        String msgId = fpsMsgEnv.getAppHdr().getBizMsgIdr();
        Message fpsMsg = new Message();
        if(dirct.equals(SysParamsContst.OUTWARD)){
            fpsMsg.setDirct(0);// 0:往帐
        }else {
            fpsMsg.setDirct(1);// 1:来账
        }
        String msgDef = fpsMsgEnv.getAppHdr().getMsgDefIdr().value();
        String value = null;
        String orgMsgId = null;
        if(msgDef.equals(SysParamsContst.PRXY_002)){
            address.prxy_002_001.Document response = fpsMsgEnv.getProxyRegistrationResponse();
            if(response != null){
                if(response.getPrxyRegnRspn().getRegnRspn().getPrxRspnSts() != null){
                    value = response.getPrxyRegnRspn().getRegnRspn().getPrxRspnSts().value();
                }
                orgMsgId = response.getPrxyRegnRspn().getOrgnlGrpInf().getOrgnlMsgId();
                if(StringUtils.isNotBlank(value)){
                    if(value.equals("ACTC")){
                        fpsMsg.setState(0);
                    }else if(value.equals("RJCT")) {
                        fpsMsg.setState(1);
                        try {
                            if (response.getPrxyRegnRspn().getRegnRspn().getStsRsnInf() != null) {
                                String prtry = response.getPrxyRegnRspn().getRegnRspn().getStsRsnInf().getPrtry();
                                if (StringUtils.isNotBlank(prtry)) {
                                    fpsMsg.setErrorRsn(prtry);
                                }
                                String cd = response.getPrxyRegnRspn().getRegnRspn().getStsRsnInf().getPrtry();
                                if (StringUtils.isNotBlank(cd)) {
                                    fpsMsg.setErrorCode(cd);
                                }
                            }
                        }catch (Exception e){

                        }
                    }
                }
            }
        }else if(msgDef.equals(SysParamsContst.PRXY_004)){
            address.prxy_004_001.Document response = fpsMsgEnv.getProxyLookUpResponse();
            if(response != null){
                if(response.getPrxyLookUpRspn().getLkUpRspn().getRegnRspn().getPrxRspnSts() != null){
                    value = response.getPrxyLookUpRspn().getLkUpRspn().getRegnRspn().getPrxRspnSts().value();
                }
                orgMsgId = response.getPrxyLookUpRspn().getOrgnlGrpInf().getOrgnlMsgId();
            }
            if(StringUtils.isNotBlank(value)){
                if(value.equals("ACTC")){
                    fpsMsg.setState(0);
                }else if(value.equals("RJCT")) {
                    fpsMsg.setState(1);
                    try{
                        if(response.getPrxyLookUpRspn().getLkUpRspn().getRegnRspn().getStsRsnInf()!=null) {
                            String prtry = response.getPrxyLookUpRspn().getLkUpRspn().getRegnRspn().getStsRsnInf().getPrtry();
                            if (StringUtils.isNotBlank(prtry)) {
                                fpsMsg.setErrorRsn(prtry);
                            }
                        }
                    }catch (Exception e) {
                    }
                   /* String cd = response.getPrxyLookUpRspn().getLkUpRspn().getRegnRspn().getStsRsnInf().getCd().value();
                    if(StringUtils.isNotBlank(cd)){
                        fpsMsg.setErrorCode(cd);
                    }*/
                }
            }
        }else if(msgDef.equals(SysParamsContst.PRXY_006)){
            address.prxy_006_001.Document response = fpsMsgEnv.getProxyEnquiryResponse();
            if(response != null){
                if(response.getPrxyNqryRspn().getNqryRspn().getPrxRspnSts()!= null){
                    value =response.getPrxyNqryRspn().getNqryRspn().getPrxRspnSts().value();
                }
                orgMsgId = response.getPrxyNqryRspn().getOrgnlGrpInf().getOrgnlMsgId();
                if(StringUtils.isNotBlank(value)){
                    if(value.equals("ACTC")){
                        fpsMsg.setState(0);
                    }else if(value.equals("RJCT")) {
                        fpsMsg.setState(1);
                        try{
                         if( response.getPrxyNqryRspn().getNqryRspn().getStsRsnInf()!=null) {
                                String prtry = response.getPrxyNqryRspn().getNqryRspn().getStsRsnInf().getPrtry();
                                if (StringUtils.isNotBlank(prtry)) {
                                    fpsMsg.setErrorRsn(prtry);
                                }
                            }
                            }catch (Exception e) {
                         }


                        /*String cd = response.getPrxyNqryRspn().getNqryRspn().getStsRsnInf().getCd().value();
                        if(StringUtils.isNotBlank(cd)){
                            fpsMsg.setErrorCode(cd);
                        }*/
                    }
                }
            }
        }else if(msgDef.equals(SysParamsContst.PRXY_008)){
            address.prxy_008_001.Document response = fpsMsgEnv.getParticipantReportResponse();
            if(response != null){
                if(response.getPtcptRptRspn().getPrtryData().getTp() != null){
                    value = response.getPtcptRptRspn().getPrtryData().getTp().value();
                }
                orgMsgId = response.getPtcptRptRspn().getGrpHdr().getMsgId();
                if(StringUtils.isNotBlank(value)){
                    if(value.equals("ACTV")){
                        fpsMsg.setState(0);
                    }else if(value.equals("RJCT")) {
                        fpsMsg.setState(1);
                        try {
                            if (response.getPtcptRptRspn().getPrtryData().getData().getActvtySummryRpt().getStsRsn() != null) {
                                String prtry = response.getPtcptRptRspn().getPrtryData().getData().getActvtySummryRpt().getStsRsn().getPrtry();
                                if (StringUtils.isNotBlank(prtry)) {
                                    fpsMsg.setErrorRsn(prtry);
                                }
                            }
                        }catch (Exception e){

                        }
                        /*String cd = response.getPtcptRptRspn().getPrtryData().getData().getActvtySummryRpt().getStsRsn().getCd().value();
                        if(StringUtils.isNotBlank(cd)){
                            fpsMsg.setErrorCode(cd);
                        }*/
                    }
                }
            }
        }
        if(StringUtils.isNotBlank(orgMsgId)){
            fpsMsg.setOrgMsgId(orgMsgId);
        }
        fpsMsg.setToInstn(to);
        fpsMsg.setFromInstn(fr);
        fpsMsg.setMsgDef(msgDef);
        fpsMsg.setMsgId(msgId);
        fpsMsg.setMsgFile(url);
        fpsMsg.setTag("Paynow");
        messageRepository.save(fpsMsg);
    }

    public Address saveMessagePrxy001(adrs.HdrAndData fpsMsgEnv,String url, String dirct){
        Address address = new Address();
        address.prxy_001_001.Document registration = fpsMsgEnv.getProxyRegistration();
        GroupHeader59 grpHdr = registration.getPrxyRegn().getGrpHdr();
        ProxyRegistration1 regn = registration.getPrxyRegn().getRegn();
        address.head_001_001.BusinessApplicationHeaderV01 appHdr = fpsMsgEnv.getAppHdr();
        String msgDefIdr = appHdr.getMsgDefIdr().value();
        String to = fpsMsgEnv.getAppHdr().getTo().getFIId().getFinInstnId().getBICFI();
        address.setBic(to);
        address.setMsgDef(msgDefIdr);
        if(grpHdr != null){
            address.setMsgId(grpHdr.getMsgId());
            address.setCreDtTm(grpHdr.getCreDtTm().toString());
            address.setAgt(grpHdr.getMsgSndr().getAgt().getFinInstnId().getBICFI());
        }
        if(regn != null){
            address.setRegnTp(regn.getRegnTp().value());
            if(null != regn.getRegnSubTp()){
                address.setRegnSubTp(regn.getRegnSubTp().value());
            }
            ProxyDefintion1 prxy = regn.getPrxy();
            if(prxy != null){
                address.setTp(regn.getPrxy().getTp());
                address.setVal(regn.getPrxy().getVal());
            }
            ProxyRegistrationAccount1 prxyRegn = regn.getPrxyRegn();
            if(prxyRegn != null){
                address.setDsplNm(prxyRegn.getDsplNm());
                if(prxyRegn.getAgt() != null){
                    address.setAgt(prxyRegn.getAgt().getFinInstnId().getBICFI());
                }
                String regnId = prxyRegn.getRegnId();
                if(StringUtils.isNotBlank(regnId)){
                    address.setRegnId(regnId);
                }
                if(prxyRegn != null){
                    CashAccount40 acct = prxyRegn.getAcct();
                    if(acct != null){
                        address.setAcctId(acct.getId().getOthr().getId());
                        address.setAcctNm(acct.getNm());
                        if(acct.getTp() != null){
                            ProxyAccountType prtry = acct.getTp().getPrtry();
                            if(prtry != null){
                                address.setAcctPrtry(prtry.value());
                            }
                        }
                    }
                }
                if(prxyRegn.getAcctHldr() != null){
                    Organisation22 org = prxyRegn.getAcctHldr().getOrg();
                    if(org != null){
                        address.setOrgNm(org.getNm());
                        address.setOrgRegnDt(org.getRegnDt().toString());
                        address.setTpOfOrgId(org.getTpOfOrg().getPrtry().getId());
                        address.setTpOfOrgIssr(org.getTpOfOrg().getPrtry().getIssr());
                    }
                    IndividualPerson28 indvPrsn = prxyRegn.getAcctHldr().getIndvPrsn();
                    if(indvPrsn != null){
                        address.setGvnNm(indvPrsn.getGvnNm());
                        address.setNm(indvPrsn.getNm());
                        String mddlNm = indvPrsn.getMddlNm();
                        if(StringUtils.isNotBlank(mddlNm)){
                            address.setMddlNm(mddlNm);
                        }
                    }
                }
                if(prxyRegn.isPreAuthrsd() != null){
                    address.setPreAuthrsd(prxyRegn.isPreAuthrsd());
                }
            }
        }
        Address save = addressRepository.save(address);
        return save;
    }

    public void saveMessagePrxy003(adrs.HdrAndData fpsMsgEnv,String url, String dirct){
        Address address = new Address();
        address.prxy_003_001.Document registration = fpsMsgEnv.getProxyLookUp();
        GroupHeader69 grpHdr = registration.getPrxyLookUp().getGrpHdr();
        ProxyLookUpChoice1 lookUp = registration.getPrxyLookUp().getLookUp();
        address.head_001_001.BusinessApplicationHeaderV01 appHdr = fpsMsgEnv.getAppHdr();
        String msgDefIdr = appHdr.getMsgDefIdr().value();
        address.setMsgDef(msgDefIdr);
        if(grpHdr != null){
            address.setMsgId(grpHdr.getMsgId());
            address.setCreDtTm(grpHdr.getCreDtTm().toString());
            address.setAgt(grpHdr.getMsgSndr().getAgt().getFinInstnId().getBICFI());
        }
        if(lookUp != null){
            ProxyLookUp11 prxyOnly = lookUp.getPrxyOnly();
            if(prxyOnly != null){
                address.setAcctId(prxyOnly.getId());
                address.setTp(prxyOnly.getPrxyRtrvl().getTp());
                address.setVal(prxyOnly.getPrxyRtrvl().getVal());
//                address.setTp(prxyOnly.getPrxyRqstr().getTp());
//                address.setVal(prxyOnly.getPrxyRqstr().getVal());
                addressRepository.save(address);
            }
        }
    }


    public void saveMessagePrxy005(adrs.HdrAndData fpsMsgEnv){
        Address address = new Address();
        address.prxy_005_001.Document registration = fpsMsgEnv.getProxyEnquiryRequest();
        address.prxy_005_001.GroupHeader59 grpHdr = registration.getPrxyNqryReq().getGrpHdr();
        if(grpHdr != null){
            address.setMsgId(grpHdr.getMsgId());
            address.setCreDtTm(grpHdr.getCreDtTm().toString());
            address.setAgt(grpHdr.getMsgSndr().getAgt().getFinInstnId().getBICFI());
        }
        Address add  = addressRepository.findOneByMsgDef(MessageTypesCAS.PRXY_005_001_01.value());
        if(add!=null){
            add.setMsgId(grpHdr.getMsgId());
            add.setCreDtTm(grpHdr.getCreDtTm().toString());
            add.setAgt(grpHdr.getMsgSndr().getAgt().getFinInstnId().getBICFI());
        }
        address.setMsgDef(MessageTypesCAS.PRXY_005_001_01.value());

        if(fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getAccOnly()!=null){
            String acctId = fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getAccOnly().getAcct().getId().getOthr().getId();
            address.setAcctId(acctId);
            if(add!=null){
                add.setAcctId(acctId);
            }
        }else if(!StringUtils.isEmpty(fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getRegnId())){
            String regnId = fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getRegnId();
            address.setRegnId(regnId);
            if(add!=null){
                add.setRegnId(regnId);
            }
        }else if(fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getNonOwngPrxy()!=null){
            String val = fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getNonOwngPrxy().getPrxyNqry().getVal();
            String tp = fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getNonOwngPrxy().getPrxyNqry().getTp();
            address.setVal(val);
            address.setTp(tp);
            if(add!=null){
                add.setVal(val);
                add.setTp(tp);
            }
        }else if(fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyAcc()!=null){
            String val = fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyAcc().getPrxyNqry().getVal();
            String tp = fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyAcc().getPrxyNqry().getTp();
            String acctId = fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyAcc().getAcct().getId().getOthr().getId();
            address.setVal(val);
            address.setTp(tp);
            address.setAcctId(acctId);
            if(add!=null){
                add.setVal(val);
                add.setTp(tp);
                add.setAcctId(acctId);
            }
        }else if(fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyOnly()!=null){
            String val = fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyOnly().getPrxyRtrvl().getVal();
            String tp = fpsMsgEnv.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyOnly().getPrxyRtrvl().getTp();
            address.setVal(val);
            address.setTp(tp);
            if(add!=null){
                add.setVal(val);
                add.setTp(tp);
            }
        }
        if(add==null){
            addressRepository.save(address);
        }
    }

    public void saveMessagePrxy007(adrs.HdrAndData fpsMsg, String url, String inward) {
        Address address = new Address();
        address.head_001_001.BusinessApplicationHeaderV01 appHdr = fpsMsg.getAppHdr();
        address.prxy_007_001.Document participantReportRequest = fpsMsg.getParticipantReportRequest();
        address.prxy_007_001.GroupHeader59 grpHdr = participantReportRequest.getPtcptRptReq().getGrpHdr();
        address.setAgt(grpHdr.getMsgSndr().getAgt().getFinInstnId().getBICFI());
        address.setCreDtTm(appHdr.getCreDt().toString());
        address.setRegnSts(participantReportRequest.getPtcptRptReq().getRptTp().value());
        address.setMsgId(appHdr.getBizMsgIdr());
        address.setMsgDef(MessageTypesCAS.PRXY_007_001_01.value());
        addressRepository.save(address);
    }

    public void saveMessageStmt001(bcsis.HdrAndData fpsMsg, String url, String inward) {
        MessagePacs pacs = new MessagePacs();
        BusinessApplicationHeaderV01 appHdr = fpsMsg.getAppHdr();
        iso.stmt_001_001.Document request = fpsMsg.getBankStatementRequest();
        GrpHdr grpHdr = request.getBankStmtReq().getGrpHdr();
        pacs.setBizMsgIdr(grpHdr.getMsgId());
        pacs.setMsgId(appHdr.getBizMsgIdr());
        pacs.setMsgDef(G3MessageTypes.STMT_001_001_01.value());
        pacs.setCreDtTm(appHdr.getCreDt().toString());
        if(request.getBankStmtReq() != null){
            if(request.getBankStmtReq().getStmtReq() != null){
                if(request.getBankStmtReq().getStmtReq().getInstgAgt() != null) {
                    if (request.getBankStmtReq().getStmtReq().getInstgAgt().getFinInstnId() != null) {
                        if (request.getBankStmtReq().getStmtReq().getInstgAgt().getFinInstnId().getClrSysMmbId() != null) {
                            if (request.getBankStmtReq().getStmtReq().getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId() != null) {
                                pacs.setInstgAgtId(request.getBankStmtReq().getStmtReq().getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId());
                            }
                        }
                    }
                }
            }
        }
        if(request.getBankStmtReq() != null){
            if(request.getBankStmtReq().getStmtReq() != null){
                if(request.getBankStmtReq().getStmtReq().getInstdAgt() != null){
                    if(request.getBankStmtReq().getStmtReq().getInstdAgt().getFinInstnId() != null){
                        if(request.getBankStmtReq().getStmtReq().getInstdAgt().getFinInstnId().getClrSysMmbId() != null){
                            if(request.getBankStmtReq().getStmtReq().getInstdAgt().getFinInstnId().getClrSysMmbId().getMmbId() != null){
                                pacs.setInstdAgtId(request.getBankStmtReq().getStmtReq().getInstdAgt().getFinInstnId().getClrSysMmbId().getMmbId());
                            }
                        }
                    }
                }
            }
        }
        pacs.setCcy(request.getBankStmtReq().getStmtReq().getCcy());
        pacs.setDirct(0);
        pacs.setSvcLvl("");
        messagePacsRepository.save(pacs);
    }

    public Page<MessagePacs> findAllMessagePacsByCdn(Integer page, Integer size, MessagePacsDto model) {
        Page<MessagePacs> rtn = null;
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size, Sort.by("id").descending());
        Specification<MessagePacs> messagePacss = new Specification<MessagePacs>() {

            @Override
            public Predicate toPredicate(Root<MessagePacs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (!org.springframework.util.StringUtils.isEmpty(model.getBiz_msg_idr())){
                    Predicate predicate = null;
                    Path<String> bizMsgIdr = root.get("bizMsgIdr");
                    predicate = criteriaBuilder.like(bizMsgIdr,"%" + model.getBiz_msg_idr()+"%" );
                    predicates.add(predicate);
                }
                if (!org.springframework.util.StringUtils.isEmpty(model.getDbtr_nm())){
                    Predicate predicate = null;
                    Path<String> dbtrNm = root.get("dbtrNm");
                    if (predicate == null){
                        predicate = criteriaBuilder.like(dbtrNm,"%" + model.getDbtr_nm()+"%" );
                    }else {
                        predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(dbtrNm,"%" + model.getDbtr_nm()+"%" ));
                    }
                    predicates.add(predicate);
                }
                if (!org.springframework.util.StringUtils.isEmpty(model.getCdtr_nm())){
                    Predicate predicate = null;
                    Path<String> cdtrNm = root.get("cdtrNm");
                    if (predicate == null){
                        predicate = criteriaBuilder.like(cdtrNm,"%" + model.getCdtr_nm()+"%" );
                    }else {
                        predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(cdtrNm,"%" + model.getCdtr_nm()+"%" ));
                    }
                    predicates.add(predicate);
                }
                if (!org.springframework.util.StringUtils.isEmpty(model.getState())){
                    Predicate predicate = null;
                    Path<String> state = root.get("state");
                    if (predicate == null){
                        predicate = criteriaBuilder.equal(state,model.getState() );
                    }else {
                        predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(state,model.getState()));
                    }
                    predicates.add(predicate);
                }
                if (!org.springframework.util.StringUtils.isEmpty(model.getCcy())){
                    Predicate predicate = null;
                    Path<String> ccy = root.get("ccy");
                    if (predicate == null){
                        predicate = criteriaBuilder.like(ccy,"%" + model.getCcy()+"%" );
                    }else {
                        predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(ccy,"%" + model.getCcy()+"%" ));
                    }
                    predicates.add(predicate);
                }
                if (!org.springframework.util.StringUtils.isEmpty(model.getCre_dt()))
                {
                    Predicate predicate = null;
                    Path<String> creDt = root.get("creDt");
                    if (predicate == null){
                        predicate = criteriaBuilder.like(creDt,"%" + model.getCre_dt()+"%" );
                    }else {
                        predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(creDt,"%" + model.getCre_dt()+"%" ));
                    }
                    predicates.add(predicate);
                }
                if(predicates!=null){
                    criteriaQuery.where(predicates.toArray(new Predicate[0]));
                }
                return criteriaQuery.getRestriction();

            }
        };
        rtn = messagePacsRepository.findAll(messagePacss, pageable);

        return rtn;
    }

    public void deleteMessagePacs(String ids){
        List<MessagePacs> messagePacsList = new ArrayList<MessagePacs>();
        if (StringUtils.isNotEmpty(ids)) {
            for(String id : ids.split(",")) {
                MessagePacs messagePacs = new MessagePacs();
                messagePacs.setId(Long.valueOf(id));
                messagePacsList.add(messagePacs);
            }
        }
        this.messagePacsRepository.deleteInBatch(messagePacsList);
    }


    public MessagePacs findByMsgDefAndBizMsgIdr(String msgId) {
       return messagePacsRepository.findByMsgDefAndBizMsgIdr("pacs.008.001.02",msgId);
    }
}
