package com.macrochina.net.controller;

import com.macrochina.net.entity.Res;
import com.macrochina.net.service.FpsParamService;
import com.macrochina.net.service.XmlSignService;
import com.macrochina.net.util.NewXmlSignUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Sign")
public class SignController {
    @Autowired
    private XmlSignService service;


    @Autowired
    private FpsParamService fpsParamService;

    @ApiOperation(value = "加签验签", notes = "")
    @RequestMapping("/signAndVerifySign")
    public Res signAndVerifySign(@RequestParam String xml) {
        return Res.Success(service.verifySignByPublicKey(NewXmlSignUtil.xmlStringSign(xml, "off", "wx", "1234", "", "", ""), ""));
    }

    @ApiOperation(value = "加签", notes = "")
    @RequestMapping("/signXml")
    public String signXml(@RequestParam("xml") String xml, String off, String user,
                          String password, String partition, String method,
                          String alias) {
      /*  String isSign = fpsParamService.findById().getIsSign();
        if("0".equals(isSign)){
        //    logger.info("No sign operation is performed");
            return xml;
        }*/
        String sign = NewXmlSignUtil.xmlStringSign(xml, off, user, password, partition, method, alias);
        return sign;
    }

    @ApiOperation(value = "验签", notes = "")
    @RequestMapping("/verifySignByPublicKey")
    public boolean verifySignByPublicKey(@RequestBody String xml) {
        return service.verifySignByPublicKey(xml, "");
    }

   /* public static void mains(String[] args) {
        String sign = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Message xmlns=\"urn:bcsis\" xmlns:er=\"urn:iso:std:iso:20022:tech:xsd:admn.005.001.01\" xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Echo.Request.01.xsd\"><AppHdr><head:Fr><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>TRBUSG50XXX</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>SACHSGS0XXX</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr>B20210902TRBUSGS0BAD1010001</head:BizMsgIdr><head:MsgDefIdr>admn.005.001.01</head:MsgDefIdr><head:CreDt>2021-09-02T10:39:46.046Z</head:CreDt><head:Sgntr><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><DigestValue>4Vmts5Vcbt30kNDjc77MWXwHKN9M979UZL4x7xAZIFA=</DigestValue></Reference></SignedInfo><SignatureValue>c6UA2w0iMeLcHgeN9QD+RmxAPYNi8QLSSg6vwvBjitdX5JMdBqmw9LNwmqggcz8eo2oA7fMs3+GG\n" +
                "Uq07gsXGN1nlZsPcdctNSASiZ5aefm+q56vhgSs/nLobqDxlO2qEJxIyyN38DootCnHutTawiY0C\n" +
                "JzmgVbWOtoapIYsqsphJT+8zgzJSK94yMlvn0B1w1Ox5VCcm/UKZHuoRwLaXdgl5Jro+M6LgI7zN\n" +
                "W+1HFWmqnaiRJ2w5cuSAZ3+9FqfROqh9HeNS8wgp+q+oUfnnW51BHDcjYPKNXvr5yhsEGLRLle/X\n" +
                "JpMmQGL/B44It4jLbKUWcNq4v4MtzM27RE1c6g==</SignatureValue><KeyInfo><X509Data><X509SubjectName>CN=TEST_GC_SC_BANK_SOLUTIONS_SG_HSM_02,OU=001,OU=9733,O=Singapore Automated Clearing House,C=SG</X509SubjectName><X509IssuerSerial><X509IssuerName>CN=UAT_OCA4,O=Singapore Automated Clearing House,C=SG</X509IssuerName><X509SerialNumber>2200020715</X509SerialNumber></X509IssuerSerial></X509Data></KeyInfo></Signature></head:Sgntr></AppHdr><EchoRequest><er:AdmnEchoReq><er:GrpHdr><er:MsgId>B20210902TRBUSGS0BAD1010001</er:MsgId><er:CreDtTm>2021-09-02T10:39:46.046</er:CreDtTm></er:GrpHdr><er:EchoTxInf><er:FnctnCd>731</er:FnctnCd><er:InstrId>20210902TRBUSGS0BAD1010001</er:InstrId><er:InstgAgt><er:FinInstnId><er:ClrSysMmbId><er:MmbId>TRBUSG50XXX</er:MmbId></er:ClrSysMmbId></er:FinInstnId></er:InstgAgt><er:InstdAgt><er:FinInstnId><er:ClrSysMmbId><er:MmbId>SACHSGS0XXX</er:MmbId></er:ClrSysMmbId></er:FinInstnId></er:InstdAgt></er:EchoTxInf></er:AdmnEchoReq></EchoRequest></Message>";

        sign = sign.replaceAll("#13;","");
        int start = sign.indexOf("<SignatureValue>");
        int end = sign.indexOf("</SignatureValue>");
        String signStr = sign.substring((start+"<SignatureValue>".length()),end);
        signStr = signStr.replace("+","%2B");
        sign = sign.replace(sign.substring((start+"<SignatureValue>".length()),end).toString(),signStr);
        System.out.println(sign);
    }*/
}
