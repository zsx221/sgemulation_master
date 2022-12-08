package com.macrochina.net.jaxb;

import java.util.HashMap;
import java.util.Map;

public   final  class XmlNsConstnt {
    public static final Map<String,String> map=new HashMap<String,String>();
    public static final Map<String,String> prefix=new HashMap<String,String>();
    static {
        map.put("Message","");
        map.put("AppHdr","");
//        map.put("FIId","head");
//        map.put("BICFI","head");
//        map.put("Fr","head");
//        map.put("to","head");
//        map.put("BizMsgIdr","head");
//        map.put("MsgDefIdr","head");
//        map.put("CreDt","head");
        map.put("Signature","");
        //pacs008
        map.put("CreditTransfer","");
        map.put("FIToFICstmrCdtTrf","ct");
        //pacs002
        map.put("PaymentStatus","");
        map.put("FIToFIPmtStsRpt","ps");
        //camt056
        map.put("PaymentCancellation","");
        map.put("FIToFIPmtCxlReq","pc");

        //prxy.001
        map.put("ProxyRegistration","");
        map.put("PrxyRegn","p1");
        //prxy.002
        map.put("ProxyRegistrationResponse","");
        map.put("PrxyRegnRspn","p2");
        //prxy.003
        map.put("ProxyLookUp","");
        map.put("PrxyLookUp","p3");
        //prxy.004
        map.put("ProxyLookUpResponse","");
        map.put("PrxyLookUpRspn","p4");
        //prxy.005
        map.put("ProxyEnquiryRequest","");
        map.put("PrxyNqryReq","p5");
        //prxy.006
        map.put("ProxyEnquiryResponse","");
        map.put("PrxyNqryRspn","p6");
        //prxy.007
        map.put("ParticipantReportRequest","");
        map.put("PtcptRptReq","p7");
        //prxy.008
        map.put("ParticipantReportResponse","");
        map.put("PtcptRptRspn","p8");


        //admn.002
        map.put("SignOnResponse","");
        map.put("AdmnSignOnResp","rs");

        //admn.004
        map.put("SignOffResponse","");
        map.put("AdmnSignOffResp","rf");

        //admn.005
        map.put("EchoRequest","");
        map.put("AdmnEchoReq","er");

        //admn.006
        map.put("EchoResponse","");
        map.put("AdmnEchoResp","re");

//        map.put("auto-generated_for_wildcard","n1");

        /**
         * 前缀
         */
        prefix.put("mr","mr");//admi.002.001.01
        prefix.put("ne","ne");//admi.004.001.01
        prefix.put("sr","sr");//admn.001.001.01
        prefix.put("rs","rs");//admn.002.001.01
        prefix.put("fr","fr");//admn.003.001.01
        prefix.put("rf","rf");//admn.004.001.01
        prefix.put("er","er");//admn.005.001.01
        prefix.put("re","re");//admn.006.001.01
        prefix.put("ri","ri");//camt.029.001.03
        prefix.put("bs","bs");//camt.053.001.02
        prefix.put("pc","pc");//camt.056.001.01
        prefix.put("head","head");//head.001.001.01
        prefix.put("ps","ps");//pacs.002.001.03
        prefix.put("xs","xs");//pacs003、pacs007
        prefix.put("ct","ct");//pacs.008.001.02
        prefix.put("br","br");//stmt.001.001.01
        prefix.put("bp","bp");//stmt.002.001.01
        prefix.put("p1","p1");//prxy.001.001.01
        prefix.put("p2","p2");//prxy.002.001.01
        prefix.put("p3","p3");//prxy.003.001.01
        prefix.put("p4","p4");//prxy.004.001.01
        prefix.put("p5","p5");//prxy.005.001.01
        prefix.put("p6","p6");//prxy.006.001.01
        prefix.put("p7","p7");//prxy.007.001.01
        prefix.put("p8","p8");//prxy.008.001.01
    }








}
