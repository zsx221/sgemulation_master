package com.macrochina.net.service;

import address.head_001_001.BranchAndFinancialInstitutionIdentification5;
import address.head_001_001.FinancialInstitutionIdentification8;
import address.head_001_001.*;
import address.prxy_001_001.Document;
import address.prxy_001_001.ProxyDefintion1;
import address.prxy_001_001.*;
import address.prxy_002_001.Party12Choice;
import address.prxy_002_001.ProxyRegistrationAccount1;
import address.prxy_002_001.ProxyRegistrationSubType1Code;
import address.prxy_002_001.ProxyRegistrationType1Code;
import address.prxy_002_001.*;
import adrs.HdrAndData;
import com.macrochina.net.dao.AddressRepository;
import com.macrochina.net.dao.MessageRepository;
import com.macrochina.net.entity.Address;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
@SuppressWarnings("all")
public class Prxy002Service {

    private final DbToXmlService dbToXmlService;
    private final MessagePacsService messagePacsService;
    private final FpsParamService fpsParamService;
    private final AddressRepository addressRepository;
    private final MessageRepository messageRepository;

    @Autowired
    private SchemaService schemaService;

    Prxy002Service(DbToXmlService dbToXmlService, MessagePacsService messagePacsService, FpsParamService fpsParamService, AddressRepository addressRepository, MessageRepository messageRepository) {
        this.dbToXmlService = dbToXmlService;
        this.messagePacsService = messagePacsService;
        this.fpsParamService = fpsParamService;
        this.addressRepository = addressRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * 回复 prxy002
     */
    @Async("asyncTaskExecutor")
    public void ack(HdrAndData fpsMsg, String url, BizRuleSet bizRuleSet) {
        // 保存 prxy 002
        FpsParam param = fpsParamService.findById();
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 oriHead = fpsMsg.getAppHdr();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        String mgId = MessageUtils.getCTxnId(param.getSendBank());
        fillHeader(oriHead, retHead, param, mgId);
        hdrAndData.setAppHdr(retHead);
        Document origDoc = fpsMsg.getProxyRegistration();
        String rendId = MessageUtils.getRendId();
        String regnTp = origDoc.getPrxyRegn().getRegn().getRegnTp().value();
        if (regnTp.equals("NEWR")) {
            origDoc.getPrxyRegn().getRegn().getPrxyRegn().setRegnId(rendId);
        }
        createDocument(origDoc, oriHead, hdrAndData, param, mgId, rendId, bizRuleSet, fpsMsg);
    }

    private void createDocument(Document origDoc, BusinessApplicationHeaderV01 origHead, HdrAndData hdrAndData,
                                FpsParam param, String mgId, String regnId, BizRuleSet bizRuleSet, HdrAndData fpsMsg) {
        try {
            address.prxy_002_001.Document document = new address.prxy_002_001.Document();
            String to = fpsMsg.getAppHdr().getTo().getFIId().getFinInstnId().getBICFI();
            ProxyRegistrationResponseV01 prxyRegnRspn = new ProxyRegistrationResponseV01();
            GroupHeader60 grpHdr = new GroupHeader60();
            grpHdr.setMsgId(mgId);
            grpHdr.setCreDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
            Party12Choice msgRcpt = new Party12Choice();
            address.prxy_002_001.BranchAndFinancialInstitutionIdentification5 agt = new address.prxy_002_001.BranchAndFinancialInstitutionIdentification5();
            address.prxy_002_001.FinancialInstitutionIdentification8 finInstnId = new address.prxy_002_001.FinancialInstitutionIdentification8();
            finInstnId.setBICFI(origDoc.getPrxyRegn().getGrpHdr().getMsgSndr().getAgt().getFinInstnId().getBICFI());
            agt.setFinInstnId(finInstnId);
            msgRcpt.setAgt(agt);
            grpHdr.setMsgRcpt(msgRcpt);
            prxyRegnRspn.setGrpHdr(grpHdr);

            OriginalGroupInformation3 orgnlGrpInf = new OriginalGroupInformation3();
            orgnlGrpInf.setOrgnlMsgId(origDoc.getPrxyRegn().getGrpHdr().getMsgId());
            orgnlGrpInf.setOrgnlMsgNmId(origHead.getMsgDefIdr().value());
            orgnlGrpInf.setOrgnlCreDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
            prxyRegnRspn.setOrgnlGrpInf(orgnlGrpInf);

            ProxyRegistrationResponse1 regnRspn = new ProxyRegistrationResponse1();
            String value = origDoc.getPrxyRegn().getRegn().getRegnTp().value();
            if (StringUtils.isNotBlank(value)) {
                regnRspn.setOrgnlRegnTp(ProxyRegistrationType1Code.fromValue(value));
            }
            address.prxy_001_001.ProxyRegistrationSubType1Code regnSubTp = origDoc.getPrxyRegn().getRegn().getRegnSubTp();
            if (regnSubTp != null) {
                regnRspn.setOrgnlRegnSubTp(ProxyRegistrationSubType1Code.fromValue(regnSubTp.value()));
            }
            ProxyDefintion1 prxy = origDoc.getPrxyRegn().getRegn().getPrxy();
            String regnTp = origDoc.getPrxyRegn().getRegn().getRegnTp().value();

            regnRspn.setPrxRspnSts(ProxyStatusCode.ACTC);

            if (prxy != null) {
                address.prxy_002_001.ProxyDefintion1 orgnlPrxy = new address.prxy_002_001.ProxyDefintion1();
                orgnlPrxy.setTp(prxy.getTp());
                orgnlPrxy.setVal(prxy.getVal());
                regnRspn.setOrgnlPrxy(orgnlPrxy);
            }

            ProxyStatusChoice stsRsnInf = new ProxyStatusChoice();
            if (bizRuleSet != null && "Reject".equals(bizRuleSet.getResp_status())) {
                //stsRsnInf.setCd(ExternalStatusReason1Code.RC_02);
                stsRsnInf.setPrtry(bizRuleSet.getRsnCode());
                regnRspn.setStsRsnInf(stsRsnInf);
                regnRspn.setPrxRspnSts(ProxyStatusCode.RJCT);
            }
            if (regnTp.equals("AMND")) {  //修改
                Address ads = null;
                String renId = "";
                if (origDoc.getPrxyRegn().getRegn() != null) {
                    if (origDoc.getPrxyRegn().getRegn().getPrxyRegn() != null) {
                        if (origDoc.getPrxyRegn().getRegn().getPrxyRegn().getRegnId() != null) {
                            renId = origDoc.getPrxyRegn().getRegn().getPrxyRegn().getRegnId();
                            ads = addressRepository.findByRegnId(renId);
                        }
                    }
                }
                if (null != ads) {
                    //代理账号信息, 修改 regn的内容
                    ads.setTp(prxy.getTp());
                    ads.setVal(prxy.getVal());
                    //bic
                    address.prxy_001_001.ProxyRegistrationAccount1 prxyRegn2 = fpsMsg.getProxyRegistration().getPrxyRegn().getRegn().getPrxyRegn();
                    ads.setDsplNm(prxyRegn2.getDsplNm());
                    if (null != prxyRegn2.getAgt()) {
                        ads.setAgt(prxyRegn2.getAgt().getFinInstnId().getBICFI());
                    }
                    //账号信息
                    if (null != prxyRegn2.getAcct()) {
                        ads.setAcctId(prxyRegn2.getAcct().getId().getOthr().getId());
                        ads.setAcctPrtry(prxyRegn2.getAcct().getTp().getPrtry().value());
                        ads.setAcctNm(prxyRegn2.getAcct().getNm());
                    }
                    //个人
                    if (null != prxyRegn2.getAcctHldr()) {
                        IndividualPerson28 indvPrsn = prxyRegn2.getAcctHldr().getIndvPrsn();
                        if (null != indvPrsn) {
                            ads.setNm(indvPrsn.getNm());
                            ads.setGvnNm(indvPrsn.getNm());
                            if (StringUtils.isNotBlank(indvPrsn.getMddlNm())) {
                                ads.setMddlNm(indvPrsn.getMddlNm());
                            }
                        }
                    }

                    //公司
                    if (null != prxyRegn2.getAcctHldr()) {
                        Organisation22 org = prxyRegn2.getAcctHldr().getOrg();
                        if (null != org) {
                            ads.setOrgNm(org.getNm());
                            ads.setOrgRegnDt(org.getRegnDt().toXMLFormat());
                            String issr = org.getTpOfOrg().getPrtry().getIssr();
                            if (StringUtils.isNotBlank(issr)) {
                                ads.setTpOfOrgIssr(issr);
                            }
                            String id = org.getTpOfOrg().getPrtry().getId();
                            if (StringUtils.isNotBlank(id)) {
                                ads.setTpOfOrgId(id);
                            }
                        }
                    }
                    ProxyRegistrationAccount1 prxyRegn = new ProxyRegistrationAccount1();
                    prxyRegn.setRegnId(ads.getRegnId());
                    regnRspn.getPrxyRegn().add(prxyRegn);
                    ads.setPreAuthrsd(prxyRegn2.isPreAuthrsd());
                    ads.setStatus("ACTC");
                    addressRepository.save(ads);
                } else {
                    stsRsnInf.setCd(ExternalStatusReason1Code.RC_02);
//                stsRsnInf.setPrtry(bizRuleSet.getRsnCode());
                    regnRspn.setStsRsnInf(stsRsnInf);
                    regnRspn.setPrxRspnSts(ProxyStatusCode.RJCT);
                }
            }
            if (regnTp.equals("DEAC")) {  //取消
                List<Address> addressList = null;
                if (origDoc.getPrxyRegn().getRegn() != null) {
                    if (addressList == null) {
                        if (origDoc.getPrxyRegn().getRegn().getPrxyRegn().getAcct() != null && null != prxy.getVal()) {
                            String val = prxy.getVal();
                            String acctId = origDoc.getPrxyRegn().getRegn().getPrxyRegn().getAcct().getId().getOthr().getId();
                            addressList = addressRepository.findListByPrxyAndAcctId(val,acctId);  //账号id + 代理id 取消
                        }
                    }
                    if (addressList == null && origDoc.getPrxyRegn().getRegn().getPrxyRegn() != null) {
                        if (origDoc.getPrxyRegn().getRegn().getPrxyRegn().getRegnId() != null) {
                            String renId = origDoc.getPrxyRegn().getRegn().getPrxyRegn().getRegnId();
                            addressList = addressRepository.findListByRegnId(renId); //仅注册id取消
                        }
                    }
                    if (addressList == null && origDoc.getPrxyRegn().getRegn().getPrxyRegn().getAcct() != null) {
                        String acctId = origDoc.getPrxyRegn().getRegn().getPrxyRegn().getAcct().getId().getOthr().getId();
                        addressList = addressRepository.findByListAcctId(acctId);  //仅账号id取消
                    }
                    if (addressList == null && prxy != null) {
                        addressList = addressRepository.findListByPrxy(prxy.getVal()); //仅代理id取消
                    }
                }
                if (addressList.size() > 0) {
                    List<ProxyRegistrationAccount1> list = new ArrayList<>();
                    for (int i = 0; i < addressList.size(); i++) {
                        ProxyRegistrationAccount1 prxyRegn = new ProxyRegistrationAccount1();
                        prxyRegn.setRegnId(addressList.get(i).getRegnId());
                        list.add(prxyRegn);
                    }
                    regnRspn.setPrxyRegn(list);
                    Address address = addressList.get(0);//取消注册之后这条数据会变为RJCT，需要重新注册
                    address.setStatus("RJCT");
                    addressRepository.save(address);
                } else {
                    stsRsnInf.setCd(ExternalStatusReason1Code.RC_02);
//                stsRsnInf.setPrtry(bizRuleSet.getRsnCode());
                    regnRspn.setStsRsnInf(stsRsnInf);
                    regnRspn.setPrxRspnSts(ProxyStatusCode.RJCT);  //拒绝代码
                }
            }
            if (regnTp.equals("NEWR")) { //注册
                String acctId = origDoc.getPrxyRegn().getRegn().getPrxyRegn().getAcct().getId().getOthr().getId();
                //一个acctId可以对应多个prxyId(唯一)
//                int num1 = 0;
//                if (StringUtils.isNotBlank(acctId)) {
//                    num1 = addressRepository.findAddressByAcctId(acctId, to);
//                }
                int num2 = 0;
                if (null != prxy) {
                    num2 = addressRepository.findAddressByPrxy(prxy.getVal());
                }
                String renId = origDoc.getPrxyRegn().getRegn().getPrxyRegn().getRegnId();
                int num3 = addressRepository.findAddressByRegnId(renId);
                if (num2 > 0 || num3 > 0) {
                    stsRsnInf.setPrtry("710");
                    regnRspn.setStsRsnInf(stsRsnInf);
                    regnRspn.setPrxRspnSts(ProxyStatusCode.RJCT);
                }

//                Address address = addressRepository.findByRegnId(renId,to);//???
                ProxyRegistrationAccount1 prxyRegn = new ProxyRegistrationAccount1();
                prxyRegn.setRegnId(renId);
                regnRspn.getPrxyRegn().add(prxyRegn);
            }
            /*if (bizRuleSet != null && "Reject".equals(bizRuleSet.getResp_status())) {
                stsRsnInf.setCd(ExternalStatusReason1Code.RC_02);
                regnRspn.setStsRsnInf(stsRsnInf);
                regnRspn.setPrxRspnSts(ProxyStatusCode.RJCT);
            }*/
            prxyRegnRspn.setRegnRspn(regnRspn);

            document.setPrxyRegnRspn(prxyRegnRspn);
            hdrAndData.setProxyRegistrationResponse(document);
            // 异常处理
            String xml = ConvertAdrsUtils.marshal(hdrAndData, true);
            log.info("outgoing prxy002 xml ={}", xml);
            // 加签验证
            String signXml = fpsParamService.xmlStringSign(xml);
            // 写入本地文件
            String url = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
            FileUtils.write(signXml, url);
            address.prxy_002_001.Document response = hdrAndData.getProxyRegistrationResponse();
            String status = response.getPrxyRegnRspn().getRegnRspn().getPrxRspnSts().value();
            String var = response.getPrxyRegnRspn().getRegnRspn().getPrxRspnSts().value();
            if (regnTp.equals("DEAC") && var.equals("RJCT")) {
                status = "RJCT";
            }
            Address adrs = messagePacsService.saveMessagePrxy001(fpsMsg, url, SysParamsContst.INWARD);
            adrs.setStatus(status);
            addressRepository.save(adrs);
            messagePacsService.saveMessagePrxy(hdrAndData, url, SysParamsContst.OUTWARD);

//            Boolean bool = schemaService.validateXmlByXsd2(signXml, SysParamsContst.SCHEMA_PRXY,FileUtils.ADRS_SCHEMA);
//            if(bool){
            String urls = param.getPaynowHost() + "/paynow/v1/registration/response";
            sendTask(signXml, urls, bizRuleSet);
//            }
        } catch (Exception e) {
            log.error("HttpClient send error!", e);
        }
    }

    public void sendTask(String xml, String url, BizRuleSet bizRuleSet) {
        HttpClientUtils.sendCallback(url, xml, bizRuleSet);
    }


    private void fillHeader(BusinessApplicationHeaderV01 origHead, BusinessApplicationHeaderV01 retHead, FpsParam param, String mgId) {
        retHead.setBizMsgIdr(mgId);
        retHead.setMsgDefIdr(MessageTypesCAS.PRXY_002_001_01);
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
