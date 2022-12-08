package com.macrochina.net.util;

import iso.head_001_001.*;

import java.util.Calendar;

public class MessageHeadUtils {

    public static BusinessApplicationHeaderV01 fillHeaderValue(String msgId, String serviceCode, String msgDefIdr, String fromMmbId, String toMmbId) {
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        Party9Choice fr = new Party9Choice();
        fillHeadFrom(fr,fromMmbId);
        retHead.setFr(fr);

        Party9Choice to = new Party9Choice();
        fillHeadTo(to,toMmbId);
        retHead.setTo(to);

        retHead.setBizMsgIdr(msgId);
        retHead.setMsgDefIdr(G3MessageTypes.fromValue(msgDefIdr));
        retHead.setBizSvc(serviceCode);
        retHead.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        return retHead;
    }

    public static void fillHeadFrom(Party9Choice fr, String fromMmbId) {
        BranchAndFinancialInstitutionIdentification5 fiid = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 finsId = new FinancialInstitutionIdentification8();
        ClearingSystemMemberIdentification2 mbid = new ClearingSystemMemberIdentification2();
        mbid.setMmbId(fromMmbId);
        finsId.setClrSysMmbId(mbid);
        fiid.setFinInstnId(finsId);
        fr.setFIId(fiid);
    }

    public static void fillHeadTo(Party9Choice to, String toMmbId) {
        BranchAndFinancialInstitutionIdentification5 fiid = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 finsId = new FinancialInstitutionIdentification8();
        ClearingSystemMemberIdentification2 mbid = new ClearingSystemMemberIdentification2();
        mbid.setMmbId(toMmbId);
        finsId.setClrSysMmbId(mbid);
        fiid.setFinInstnId(finsId);
        to.setFIId(fiid);
    }
}
