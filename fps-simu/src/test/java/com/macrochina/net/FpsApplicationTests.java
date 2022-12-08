package com.macrochina.net;

import adrs.HdrAndData;
import com.macrochina.net.util.ConvertAdrsUtils;
import com.macrochina.net.util.MessageUtils;
import com.macrochina.net.util.XmlIsoDateUtil;
import address.head_001_001.BusinessApplicationHeaderV01;
import address.head_001_001.MessageTypesCAS;
import address.head_001_001.Party9Choice;
import address.head_001_001.SignatureEnvelope;
import address.prxy_001_001.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FpsApplicationTests {

    @Test
    public void prxy001() throws JAXBException {
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 appHdr = getBusinessApplicationHeaderV01();
        SignatureEnvelope sgntr = new SignatureEnvelope();
//        sgntr.setAny("kfklfkdlf");
//        appHdr.setSgntr(sgntr);

        hdrAndData.setAppHdr(appHdr);

        Document document = new Document();
        ProxyRegistrationV01 prxyRegn = new ProxyRegistrationV01();
        ProxyRegistration1 regn = new ProxyRegistration1();
        ProxyDefintion1 prxy = new ProxyDefintion1();
        prxy.setTp("qq");
        prxy.setVal("dsd");
        regn.setPrxy(prxy);
        regn.setRegnTp(ProxyRegistrationType1Code.AMND);
        ProxyRegistrationAccount1 prxyRegn1 = new ProxyRegistrationAccount1();
        prxyRegn1.setDsplNm("asa");
        BranchAndFinancialInstitutionIdentification5 agt1 = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 finInstnId1 = new FinancialInstitutionIdentification8();
        finInstnId1.setBICFI("d11");
        agt1.setFinInstnId(finInstnId1);
        prxyRegn1.setAgt(agt1);
        CashAccount40 acct = new CashAccount40();
        AccountIdentification4Choice id = new AccountIdentification4Choice();
        GenericAccountIdentification1 othr = new GenericAccountIdentification1();
        othr.setId("1100017951219900");
        id.setOthr(othr);
        acct.setId(id);
        CashAccountType2ChoiceProxy tp = new CashAccountType2ChoiceProxy();
        tp.setPrtry(ProxyAccountType.BANK);
        acct.setTp(tp);
        acct.setNm("Mr A N Smith");
        prxyRegn1.setAcct(acct);
        Party30Choice acctHldr = new Party30Choice();
        IndividualPerson28 indvPrsn = new IndividualPerson28();
        indvPrsn.setNm("Smith");
        indvPrsn.setGvnNm("Andrew");
        indvPrsn.setMddlNm("Nigel");
        acctHldr.setIndvPrsn(indvPrsn);
        prxyRegn1.setAcctHldr(acctHldr);
        prxyRegn1.setPreAuthrsd(true);
        regn.setPrxyRegn(prxyRegn1);
        prxyRegn.setRegn(regn);

        GroupHeader59 grpHdr = new GroupHeader59();
        grpHdr.setMsgId(MessageUtils.genMsgId());
        grpHdr.setCreDtTm(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        Party12Choice msgSndr = new Party12Choice();
        BranchAndFinancialInstitutionIdentification5 agt = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 finInstnId = new FinancialInstitutionIdentification8();
        finInstnId.setBICFI("dsdsd");
        agt.setFinInstnId(finInstnId);
        msgSndr.setAgt(agt);
        grpHdr.setMsgSndr(msgSndr);
        prxyRegn.setGrpHdr(grpHdr);

        document.setPrxyRegn(prxyRegn);
        hdrAndData.setProxyRegistration(document);

        String xml = ConvertAdrsUtils.marshal(hdrAndData, true);
        System.out.println(xml);

        HdrAndData fpsMsg = ConvertAdrsUtils.unmarshal(xml);
        System.out.println(fpsMsg);
    }

    private BusinessApplicationHeaderV01 getBusinessApplicationHeaderV01() {
        BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();
        Party9Choice fr  =new Party9Choice();
        address.head_001_001.BranchAndFinancialInstitutionIdentification5 fiId = new address.head_001_001.BranchAndFinancialInstitutionIdentification5();
        address.head_001_001.FinancialInstitutionIdentification8 finInstnId2 = new address.head_001_001.FinancialInstitutionIdentification8();
        finInstnId2.setBICFI("BANKSGSGXXX");
        fiId.setFinInstnId(finInstnId2);
        fr.setFIId(fiId);
        appHdr.setFr(fr);

        Party9Choice to = new Party9Choice();
        address.head_001_001.BranchAndFinancialInstitutionIdentification5 fiId3 = new address.head_001_001.BranchAndFinancialInstitutionIdentification5();
        address.head_001_001.FinancialInstitutionIdentification8 finInstnId8 = new address.head_001_001.FinancialInstitutionIdentification8();
        finInstnId8.setBICFI("SACHSGS1XXX");
        fiId3.setFinInstnId(finInstnId8);
        to.setFIId(fiId3);
        appHdr.setTo(to);
        appHdr.setBizMsgIdr("B20161011BANKSGSGB0001");
        appHdr.setMsgDefIdr(MessageTypesCAS.PRXY_001_001_01);
        appHdr.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        return appHdr;
    }

}
