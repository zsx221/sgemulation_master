//package com.macrochina.net.util;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.http.Consts;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * 利用HttpClient進行post請求的工具類
// * @ClassName: HttpClientUtil
// * @Description: TODO
// * @author Devin <xxx>
// * @date 2017年2月7日 下午1:43:38
// *
// */
//public class HttpClientUtil {
//    public static String doPost(String url,String xml){
//        HttpClient httpClient = null;
//        HttpPost httpPost = null;
//        String result = null;
//        try{
//            httpClient = new SSLClient();
//            httpPost = new HttpPost(url);
//            Map<String,String> map=new HashMap<>();
//            map.put("Api-key","BANKBCS1234");
//
//            map.put("Accept","application/xml");
//            map.put("Content-type","application/xml");
//         /*   map.put("Date",XmlIsoDateUtil.convertToXMLGregorianCalendarMillsec(Calendar.getInstance()).toString());
//           */
//            map.put("X-bcs-origin","ke111");
//            map.put("x-bcs-instruction-id", MessageUtils.genMsgId());
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                httpPost.setHeader(entry.getKey(), entry.getValue());
//            }
//            StringEntity entity=new StringEntity(xml, Consts.UTF_8);
//            //设置请求参数
//            httpPost.setEntity(entity);
//            HttpResponse response = httpClient.execute(httpPost);
//            result =String.valueOf( response.getStatusLine().getStatusCode());
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return result;
//    }
//
//    public static String doGet(String url){
//        HttpClient httpClient = null;
//        HttpGet httpGet = null;
//        String result = null;
//        try{
//            httpClient = new SSLClient();
//            httpGet = new HttpGet(url);
//            Map<String,String> map=new HashMap<>();
//            map.put("Api-key","BANKBCS1234");
//            map.put("Accept","application/xml");
//            map.put("Content-type","application/xml");
//
//            map.put("X-bcs-origin","ke111");
//            map.put("x-bcs-instruction-id", MessageUtils.genMsgId());
//            /*
//            map.put("Accept","application/xml");
//            map.put("Content-type","application/xml");*/
//         /*   map.put("Date",XmlIsoDateUtil.convertToXMLGregorianCalendarMillsec(Calendar.getInstance()).toString());
//            map.put("X-bcs-origin","ke111");
//            map.put("x-bcs-instruction-id","Instruction ID of the transaction");*/
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                httpGet.setHeader(entry.getKey(), entry.getValue());
//            }
//            HttpResponse response = httpClient.execute(httpGet);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return result;
//    }
//
//
//
//    /**
//     * @Author fangyu
//     * @Description url发送路径   jsonObject 需要发送的json  map 头部需要设置
//     * @Date 2021/9/2 20:11
//     * @Param [url, jsonObject, map]
//     * @return java.lang.String
//     **/
//    public static HttpResponse doPostHeader(String url, String json,Map<String,String> map){
//        HttpClient httpClient = null;
//        HttpPost httpPost = null;
//        try{
//            httpClient = new SSLClient();
//
//            httpPost = new HttpPost(url);
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                httpPost.setHeader(entry.getKey(), entry.getValue());
//            }
//            StringEntity entity=new StringEntity(json, Consts.UTF_8);
//            entity.setContentEncoding("UTF-8");
//            entity.setContentType("application/json");
//            //设置请求参数
//            httpPost.setEntity(entity);
//            HttpResponse response = httpClient.execute(httpPost);
//            return response;
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void mains(String[] args) {
//        String url = "https://18.139.209.254:31001/fast/v1/payment/credit-transfer-status/send";
//        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<Message xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Payment.Status.01.xsd\"\n" +
//                "         xmlns=\"urn:bcsis\"\n" +
//                "         xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">\n" +
//                "\t<AppHdr>\n" +
//                "\t\t<head:Fr>\n" +
//                "\t\t\t<head:FIId>\n" +
//                "\t\t\t\t<head:FinInstnId>\n" +
//                "\t\t\t\t\t<head:ClrSysMmbId>\n" +
//                "\t\t\t\t\t\t<head:MmbId>SACHSGS0XXX</head:MmbId>\n" +
//                "\t\t\t\t\t</head:ClrSysMmbId>\n" +
//                "\t\t\t\t</head:FinInstnId>\n" +
//                "\t\t\t</head:FIId>\n" +
//                "\t\t</head:Fr>\n" +
//                "\t\t<head:To>\n" +
//                "\t\t\t<head:FIId>\n" +
//                "\t\t\t\t<head:FinInstnId>\n" +
//                "\t\t\t\t\t<head:ClrSysMmbId>\n" +
//                "\t\t\t\t\t\t<head:MmbId>TRBUSG50XXX</head:MmbId>\n" +
//                "\t\t\t\t\t</head:ClrSysMmbId>\n" +
//                "\t\t\t\t</head:FinInstnId>\n" +
//                "\t\t\t</head:FIId>\n" +
//                "\t\t</head:To>\n" +
//                "\t\t<head:BizMsgIdr>B20210803TRBUSGS0BAD0000001</head:BizMsgIdr>\n" +
//                "\t\t<head:MsgDefIdr>pacs.002.001.03</head:MsgDefIdr>\n" +
//                "\t\t<head:CreDt>2021-09-02T07:40:22Z</head:CreDt>\n" +
//                "\t\t<head:Sgntr>\n" +
//                "\t\t\t<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
//                "\t\t\t\t<SignedInfo>\n" +
//                "\t\t\t\t\t<CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
//                "\t\t\t\t\t<SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig#rsa-sha256\"/>\n" +
//                "\t\t\t\t\t<Reference URI=\"\">\n" +
//                "\t\t\t\t\t\t<Transforms>\n" +
//                "\t\t\t\t\t\t\t<Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n" +
//                "\t\t\t\t\t\t\t<Transform Algorithm=\"http://www.w3.org/2006/12/xml-c14n11\"/>\n" +
//                "\t\t\t\t\t\t</Transforms>\n" +
//                "\t\t\t\t\t\t<DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
//                "\t\t\t\t\t\t<DigestValue>W4cSmRn02zepszcCyiOrTReMrvg=</DigestValue>\n" +
//                "\t\t\t\t\t</Reference>\n" +
//                "\t\t\t\t</SignedInfo>\n" +
//                "\t\t\t\t<SignatureValue>\n" +
//                "jg3T4TLzJcude3UOasZN+o9qqeXvKaQjbomRqyw+rMkr70J9/Aovsn5EDdk8BtRBtlzjiSAIv+Ms\n" +
//                "+0/ubOrQHY/5vjMLqvT2ZaFfrocY+gRP6zHeCKc+2/DlLVhf0rQTxdJlMcR6hyuLRQrJNKDXOYud\n" +
//                "SsLZNi7uZsA6QV8AyNs=\n" +
//                "</SignatureValue>\n" +
//                "\t\t\t\t<KeyInfo>\n" +
//                "\t\t\t\t\t<X509Data>\n" +
//                "\t\t\t\t\t\t<X509SubjectName>CN=Test Bank, OU=Test Bank, O=Test Bank, C=SG</X509SubjectName>\n" +
//                "\t\t\t\t\t\t<X509IssuerSerial>\n" +
//                "\t\t\t\t\t\t\t<X509IssuerName>CN=BCS,OU=ACH,O=BCS,C=SG</X509IssuerName>\n" +
//                "\t\t\t\t\t\t\t<X509SerialNumber>1302511280</X509SerialNumber>\n" +
//                "\t\t\t\t\t\t</X509IssuerSerial>\n" +
//                "\t\t\t\t\t</X509Data>\n" +
//                "\t\t\t\t</KeyInfo>\n" +
//                "\t\t\t</Signature>\n" +
//                "\t\t</head:Sgntr>\n" +
//                "\t</AppHdr>\n" +
//                "\t<PaymentStatus>\n" +
//                "\t\t<ps:FIToFIPmtStsRpt>\n" +
//                "\t\t\t<ps:GrpHdr>\n" +
//                "\t\t\t\t<ps:MsgId>B20210803TRBUSGS0BAD0000001</ps:MsgId>\n" +
//                "\t\t\t\t<ps:CreDtTm>2021-09-02T07:40:22.022Z</ps:CreDtTm>\n" +
//                "\t\t\t</ps:GrpHdr>\n" +
//                "\t\t\t<ps:OrgnlGrpInfAndSts>\n" +
//                "\t\t\t\t<ps:OrgnlMsgId>20210803TRBUSGS0BAD0000001</ps:OrgnlMsgId>\n" +
//                "\t\t\t\t<ps:OrgnlMsgNmId>pacs.008.001.02</ps:OrgnlMsgNmId>\n" +
//                "\t\t\t\t<ps:OrgnlCreDtTm>2021-08-03T06:16:00.000</ps:OrgnlCreDtTm>\n" +
//                "\t\t\t\t<ps:OrgnlNbOfTxs>1</ps:OrgnlNbOfTxs>\n" +
//                "\t\t\t</ps:OrgnlGrpInfAndSts>\n" +
//                "\t\t\t<ps:TxInfAndSts>\n" +
//                "\t\t\t\t<ps:OrgnlInstrId>15c2e4a7-2985-4fa4-bb20210902163721</ps:OrgnlInstrId>\n" +
//                "\t\t\t\t<ps:TxSts>ACTC</ps:TxSts>\n" +
//                "\t\t\t\t<ps:AccptncDtTm>2021-08-03T06:16:07.000</ps:AccptncDtTm>\n" +
//                "\t\t\t\t<ps:ClrSysRef>001</ps:ClrSysRef>\n" +
//                "\t\t\t\t<ps:InstgAgt>\n" +
//                "\t\t\t\t\t<ps:FinInstnId>\n" +
//                "\t\t\t\t\t\t<ps:ClrSysMmbId>\n" +
//                "\t\t\t\t\t\t\t<ps:MmbId>BKBBSGSGXXX</ps:MmbId>\n" +
//                "\t\t\t\t\t\t</ps:ClrSysMmbId>\n" +
//                "\t\t\t\t\t</ps:FinInstnId>\n" +
//                "\t\t\t\t</ps:InstgAgt>\n" +
//                "\t\t\t\t<ps:InstdAgt>\n" +
//                "\t\t\t\t\t<ps:FinInstnId>\n" +
//                "\t\t\t\t\t\t<ps:ClrSysMmbId>\n" +
//                "\t\t\t\t\t\t\t<ps:MmbId>BKAASGSGXXX</ps:MmbId>\n" +
//                "\t\t\t\t\t\t</ps:ClrSysMmbId>\n" +
//                "\t\t\t\t\t</ps:FinInstnId>\n" +
//                "\t\t\t\t</ps:InstdAgt>\n" +
//                "\t\t\t\t<ps:OrgnlTxRef>\n" +
//                "\t\t\t\t\t<ps:IntrBkSttlmDt>2021-09-02</ps:IntrBkSttlmDt>\n" +
//                "\t\t\t\t</ps:OrgnlTxRef>\n" +
//                "\t\t\t</ps:TxInfAndSts>\n" +
//                "\t\t</ps:FIToFIPmtStsRpt>\n" +
//                "\t</PaymentStatus>\n" +
//                "</Message>\n";
//        HttpClientUtil.doPost(url,xml);
//    }
//}