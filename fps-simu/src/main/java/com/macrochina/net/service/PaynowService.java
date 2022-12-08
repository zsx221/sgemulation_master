package com.macrochina.net.service;

import address.head_001_001.BranchAndFinancialInstitutionIdentification5;
import address.head_001_001.FinancialInstitutionIdentification8;
import address.head_001_001.*;
import address.prxy_006_001.*;
import adrs.HdrAndData;
import com.macrochina.net.entity.Address;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import iso.pacs_002_001.TransactionIndividualStatus3Code;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PaynowService {
    @Autowired
    SchemaService schemaService;
    @Autowired
    DbToXmlService dbToXmlService;
    @Autowired
    private AdmnService admnService;
    @Autowired
    private FpsParamService fpsParamService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private MessagePacsService messagePacsService;

    private GroupHeader60 getGrpHdr(address.prxy_005_001.Document doc005) {
        GroupHeader60 grpHdr = new GroupHeader60();
        Party12Choice msgRcpt = new Party12Choice();
        address.prxy_006_001.BranchAndFinancialInstitutionIdentification5 agt = new address.prxy_006_001.BranchAndFinancialInstitutionIdentification5();
        address.prxy_006_001.FinancialInstitutionIdentification8 finInstnId = new address.prxy_006_001.FinancialInstitutionIdentification8();
        String bicfi = doc005.getPrxyNqryReq().getGrpHdr().getMsgSndr().getAgt().getFinInstnId().getBICFI();
        finInstnId.setBICFI(bicfi);
        agt.setFinInstnId(finInstnId);
        msgRcpt.setAgt(agt);
        grpHdr.setMsgId(MessageUtils.genMsgId());
        grpHdr.setCreDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        grpHdr.setMsgRcpt(msgRcpt);
        return grpHdr;
    }

    private OriginalGroupInformation3 getOrgnlGrpInf(BusinessApplicationHeaderV01 oriHead, address.prxy_005_001.Document doc005) {
        OriginalGroupInformation3 orgnlGrpInf = new OriginalGroupInformation3();
        if (doc005.getPrxyNqryReq().getGrpHdr() != null) {
            orgnlGrpInf.setOrgnlCreDtTm(doc005.getPrxyNqryReq().getGrpHdr().getCreDtTm());
        }
        orgnlGrpInf.setOrgnlMsgId(oriHead.getBizMsgIdr());
        orgnlGrpInf.setOrgnlMsgNmId(oriHead.getMsgDefIdr().value());
        return orgnlGrpInf;
    }

    private ProxyEnquiryInformation1 getRspn(FpsParam param, Address address) {
        ProxyEnquiryInformation1 rspn = new ProxyEnquiryInformation1();
        rspn.setRegnId(address.getRegnId());
        if (StringUtils.isNotBlank(address.getDsplNm())) {
            rspn.setDsplNm(address.getDsplNm());
        }
        address.prxy_006_001.BranchAndFinancialInstitutionIdentification5 ptcpt = new address.prxy_006_001.BranchAndFinancialInstitutionIdentification5();
        address.prxy_006_001.FinancialInstitutionIdentification8 finInstnId2 = new address.prxy_006_001.FinancialInstitutionIdentification8();
        finInstnId2.setBICFI(address.getAgt());
        ptcpt.setFinInstnId(finInstnId2);
        rspn.setPtcpt(ptcpt);

        ProxyEnquiryDefintion1 prxyInf = new ProxyEnquiryDefintion1();
        if (StringUtils.isNotBlank(address.getTp())) {
            prxyInf.setTp(address.getTp());
        }
        if (StringUtils.isNotBlank(address.getVal())) {
            prxyInf.setVal(address.getVal());
        }
        prxyInf.setSts(ProxyEnquiryStatusCode.USBC);
        rspn.setPrxyInf(prxyInf);

        ProxyEnquiryAccount1 acctInf = new ProxyEnquiryAccount1();
        address.prxy_006_001.BranchAndFinancialInstitutionIdentification5 agt2 = new address.prxy_006_001.BranchAndFinancialInstitutionIdentification5();
        address.prxy_006_001.FinancialInstitutionIdentification8 finInstnId3 = new address.prxy_006_001.FinancialInstitutionIdentification8();
        finInstnId3.setBICFI(address.getAgt());
        agt2.setFinInstnId(finInstnId3);
        acctInf.setAgt(agt2);
        CashAccount40 acct = new CashAccount40();
        AccountIdentification4Choice id = new AccountIdentification4Choice();
        GenericAccountIdentification1 othr = new GenericAccountIdentification1();
        othr.setId(address.getAcctId());
        id.setOthr(othr);
        acct.setId(id);
        acct.setNm(address.getAcctNm());
        CashAccountType2ChoiceProxy tp =new CashAccountType2ChoiceProxy();
        tp.setPrtry(ProxyAccountType.BANK);
        acct.setTp(tp);
        acctInf.setAcct(acct);
        acctInf.setSts(ProxyAccountStatusCode.ACTV);
        Party30Choice acctHldr = new Party30Choice();
        Organisation22 org = new Organisation22();
        org.setNm(address.getOrgNm());
        org.setRegnDt(XmlIsoDateUtil.convertToXMLCalendar(address.getOrgRegnDt()));
        OrganisationType1Choice tpOfOrg = new OrganisationType1Choice();
        GenericIdentification47 prtry = new GenericIdentification47();
        if (StringUtils.isNotBlank(address.getTpOfOrgId())) {
            prtry.setId(address.getTpOfOrgId());
            prtry.setIssr(address.getTpOfOrgIssr());
            tpOfOrg.setPrtry(prtry);
            org.setTpOfOrg(tpOfOrg);
            acctHldr.setOrg(org);
        }

        //IndividualPerson28为可选
        if (StringUtils.isNotBlank(address.getNm())) {
            IndividualPerson28 person28 = new IndividualPerson28();
            person28.setNm(address.getNm());
            person28.setGvnNm(address.getGvnNm());
            if (StringUtils.isNotBlank(address.getMddlNm())) {
                person28.setMddlNm(address.getMddlNm());
            }
            acctHldr.setIndvPrsn(person28);
        }

        acctInf.setAcctHldr(acctHldr);
        rspn.setAcctInf(acctInf);
        rspn.setRegnSts(ProxyRegistrationStatusCode.ACTV);
        rspn.setRegnDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        rspn.setPreAuthrsd(address.isPreAuthrsd());
        return rspn;
    }

    @Async("asyncAckTaskExecutor")
    public void sendPrxy006(HdrAndData fpsMsg, BizRuleSet bizRuleSet) throws JAXBException {
        address.prxy_006_001.Document doc = new address.prxy_006_001.Document();

        address.prxy_005_001.Document doc005 = fpsMsg.getProxyEnquiryRequest();
        FpsParam param = fpsParamService.findById();
        adrs.HdrAndData hdrAndData = new adrs.HdrAndData();
        BusinessApplicationHeaderV01 oriHead = fpsMsg.getAppHdr();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        fillHeader(oriHead, retHead, param, MessageTypesCAS.PRXY_006_001_01);
        hdrAndData.setAppHdr(retHead);
        messagePacsService.saveMessagePrxy005(fpsMsg);

        ProxyEnquiryResponseV01 prxyNqryRspn = new ProxyEnquiryResponseV01();
        prxyNqryRspn.setGrpHdr(getGrpHdr(doc005));
        prxyNqryRspn.setOrgnlGrpInf(getOrgnlGrpInf(oriHead, doc005));
        ProxyEnquiryResponse1 nqryRspn = new ProxyEnquiryResponse1();
        if (bizRuleSet != null && "Reject".equals(bizRuleSet.getResp_status())) {
            nqryRspn.setPrxRspnSts(ProxyStatusCode.RJCT);
            ProxyStatusChoice proxyStatusChoice = new ProxyStatusChoice();
            proxyStatusChoice.setPrtry(bizRuleSet.getRsnCode());
            nqryRspn.setStsRsnInf(proxyStatusChoice);
        } else {
            nqryRspn.setPrxRspnSts(ProxyStatusCode.ACTC);
            List<Address> list = findAddressByCondition(fpsMsg);
            if (null == list || list.size() < 1) {
                nqryRspn.setPrxRspnSts(ProxyStatusCode.RJCT);
                ProxyStatusChoice proxyStatusChoice = new ProxyStatusChoice();
                if (fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyAcc() != null) {
                    proxyStatusChoice.setPrtry("907");
                } else if (fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getAccOnly() != null) {
                    proxyStatusChoice.setPrtry("930");
                } else if (!StringUtils.isEmpty(fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getRegnId())) {
                    proxyStatusChoice.setPrtry("912");
                } else {
                    proxyStatusChoice.setPrtry("801");
                }
                nqryRspn.setStsRsnInf(proxyStatusChoice);
            } else {
                for (Address address : list) {
                    nqryRspn.getRspn().add(getRspn(param, address));
                }
            }
        }
        prxyNqryRspn.setNqryRspn(nqryRspn);
        doc.setPrxyNqryRspn(prxyNqryRspn);
        hdrAndData.setProxyEnquiryResponse(doc);
        String xml = ConvertAdrsUtils.marshal(hdrAndData, true);
        // 加签验证
        String signXml = fpsParamService.xmlStringSign(xml);
        // 写入本地文件
        String urls = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
        FileUtils.write(signXml, urls);
        messagePacsService.saveMessagePrxy(hdrAndData, urls, SysParamsContst.OUTWARD);
//        String fileName = String.valueOf(System.currentTimeMillis());
//        FileUtils.xmlToFile(signXml, fileName);
//        Boolean bool = schemaService.validateXmlByXsd2(signXml, SysParamsContst.SCHEMA_PRXY,FileUtils.ADRS_SCHEMA);
//        if(bool){
        log.info("outgoing prxy 006 String xml =  " + signXml);
        String url = param.getPaynowHost() + "/paynow/v1/enquiry/response";
        sendTask(signXml, url, bizRuleSet);
//        }
    }


    public void sendPayNowAdmn006(HdrAndData fpsMsg,BizRuleSet bizRuleSet) throws JAXBException {
        adrs.HdrAndData hdrAndData = new adrs.HdrAndData();
        BusinessApplicationHeaderV01 oriHead = fpsMsg.getAppHdr();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        // fillHeader(oriHead, retHead, G3MessageTypes.ADMN_006_001_01);
        FpsParam param = fpsParamService.findById();
        fillHeader(oriHead, retHead, param, MessageTypesCAS.ADMN_006_001_01);
        hdrAndData.setAppHdr(retHead);// 组装 head
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", MessageUtils.genMsgId());
        map.put("creDtTm", XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        map.put("orgnlInstrId", fpsMsg.getEchoRequest().getAdmnEchoReq().getEchoTxInf().getInstrId());
        map.put("instgAgtMmbId", fpsMsg.getEchoRequest().getAdmnEchoReq().getEchoTxInf().getInstgAgt().getFinInstnId().getBIC());
        map.put("instdAgtMmbId", fpsMsg.getEchoRequest().getAdmnEchoReq().getEchoTxInf().getInstdAgt().getFinInstnId().getBIC());
        map.put("txSts", TransactionIndividualStatus3Code.fromValue("ACTC"));
        if(bizRuleSet!=null && "Reject".equals(bizRuleSet.getResp_status())){
            map.put("txSts", TransactionIndividualStatus3Code.fromValue("RJCT"));
        }
        map.put("fnctnCd", fpsMsg.getEchoRequest().getAdmnEchoReq().getEchoTxInf().getFnctnCd());
        address.admn_006_001.Document admn006 = (address.admn_006_001.Document) dbToXmlService.DbToXml(map, new address.admn_006_001.Document());
        hdrAndData.setEchoResponse(admn006);
        String xml = ConvertAdrsUtils.marshal(hdrAndData, true);
        xml = xml.replace("re:re:EchoResponse", "re:EchoResponse");
        String signXml = fpsParamService.xmlStringSign(xml);
        // String signXml = NewXmlSignUtil.xmlStringSign(xml, Constants.OFFORON,"admin", "123456", "", "", "");
        //xmlSignService.xmlStringSign(xml, "admin", "123456", "", "", "");
        // 写入本地文件
      /*  String url = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
        FileUtils.write(signXml, url);*/
        //messagePacsService.saveMessagePrxy(hdrAndData,url,SysParamsContst.OUTWARD);
    /*    String fileName = String.valueOf(System.currentTimeMillis());
        FileUtils.strToXml(signXml, fileName);*/
        Boolean bool = schemaService.validateXmlByXsd2(signXml, SysParamsContst.SCHEMA_PRXY, FileUtils.ADRS_SCHEMA);
        if (bool) {
            //log.info("outgoing  paynow admn 006 String xml =  "+signXml);
            String ip = param.getPaynowHost();
            sendTask(signXml, ip + "/paynow/v1/admin/echo/response", bizRuleSet);
        }
    }

    public void sendTask(String xml, String url, BizRuleSet bizRuleSet) {
        HttpClientUtils.sendCallback(url, xml, bizRuleSet);
    }

    private List<Address> findAddressByCondition(HdrAndData fpsMsg) {
        List<Address> list = null;
//        String to = fpsMsg.getAppHdr().getTo().getFIId().getFinInstnId().getBICFI();
        if (fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getAccOnly() != null) {
            String acctId = fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getAccOnly().getAcct().getId().getOthr().getId();
            list = addressService.findAllByAcctId(acctId, SysParamsContst.PRXY_001);
        } else if (!StringUtils.isEmpty(fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getRegnId())) {
            String regnId = fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getRegnId();
            list = addressService.findAllByRegnId(regnId, SysParamsContst.PRXY_001);
        } else if (fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getNonOwngPrxy() != null) {
            String val = fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getNonOwngPrxy().getPrxyNqry().getVal();
            list = addressService.findAllByBank(val, SysParamsContst.PRXY_001);
        } else if (fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyAcc() != null) {
            String val = fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyAcc().getPrxyNqry().getVal();
            String acctId = fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyAcc().getAcct().getId().getOthr().getId();
            list = addressService.findAllByValAndAcctId(val, acctId, SysParamsContst.PRXY_001);
        } else if (fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyOnly() != null) {
            String val = fpsMsg.getProxyEnquiryRequest().getPrxyNqryReq().getNqry().getPrxyOnly().getPrxyRtrvl().getVal();
            list = addressService.findAllByVal(val, SysParamsContst.PRXY_001);
        }


        //   List<Address> list = addressService.findAllByVal(condition, SysParamsContst.PRXY_005);
        return list;
    }

    private void fillHeader(BusinessApplicationHeaderV01 origHead,
                            BusinessApplicationHeaderV01 retHead,
                            FpsParam param, MessageTypesCAS cas) {
        retHead.setBizMsgIdr(MessageUtils.genMsgId());
        retHead.setMsgDefIdr(cas);
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
