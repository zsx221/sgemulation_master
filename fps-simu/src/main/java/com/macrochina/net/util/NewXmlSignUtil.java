package com.macrochina.net.util;

import java.io.*;

import java.math.BigInteger;
import java.security.KeyStore;

import java.security.PrivateKey;
import java.util.*;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;

import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;

import javax.xml.crypto.dsig.XMLSignature;

import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import com.macrochina.net.config.BatchTaskDataSourceInitializer;
import com.macrochina.net.service.FpsParamService;
import com.macrochina.net.utils.HSMTools;
import com.macrochina.net.utils.SignTools;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.yaml.snakeyaml.Yaml;


public class NewXmlSignUtil {
    private static Log logger = LogFactory.getLog(NewXmlSignUtil.class);
    private static String keyStoreSignFilePath = "C:\\Users\\wangxin\\Desktop\\opf加签验签\\myss.pfx";
    // 签名证书可以是经过转换后的keystore，也可以是pfx证书
    // 用不同store,初始化实例类型不一样 keystore<==>jks
    // pfx<==>pkcs12
/*    private static String keyStoreSignFilePassword = "123456"; // store 保护密码
    private static String privateKey = "1"; // 别名
    private static String privateKeyPassword = "123456"; // 私钥保护密码*/

    @Autowired
    private static FpsParamService fpsParamService;

    public static Map<String, Object> getParamMap(String path) throws FileNotFoundException {
        File file = ResourceUtils.getFile(path);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        Yaml props = new Yaml();
        Object obj = props.loadAs(in, Map.class);
        Map<String, Object> param = (Map<String, Object>) obj;
        logger.info("paramMap is " + param.toString());
        return param;
    }

    /**
     * 加签
     *
     * @param xml
     * @param off
     * @return
     * @throws Exception
     */
    // TODO: 2022/12/19  
    public static String signByEnveloed(String xml, String off,
                                        String user, String password,
                                        String partition, String method, String alias) throws Exception {

        // Create a DOM XMLSignatureFactory that will be used to
        // generate the enveloped signature.
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        // Create a Reference to the enveloped document (in this case,
        // you are signing the whole document, so a URI of "" signifies
        // that, and also specify the SHA1 digest algorithm and
        // the ENVELOPED Transform.
        Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA256, null),
                //Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null,
                Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null,
                null); // 这里与上面有点区别注意
        // Create the SignedInfo.
        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                //fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));
                fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", null), Collections.singletonList(ref));

        // Load the KeyStore and get the signing key and certificate.
        System.out.println(KeyStore.getDefaultType() + "----------------------");
        KeyStore ks = null;
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        List<Object> x509Content = new ArrayList<>();
        PrivateKey key1 = null;
        if ("on".equals(off)) {
            String subjectName = null;
            String issuerName = null;
            String serialNumber = null;
            try {
                Map<String, Object> maps = getParamMap("/data/cert_config.yml");
                subjectName = (String) maps.get("subjectName");
                logger.info(" cert_config subjectName :" + subjectName);
                issuerName = (String) maps.get("issuerName");
                logger.info(" cert_config issuerName :" + issuerName);
                serialNumber = maps.get("serialNumber") == null ? "" : maps.get("serialNumber").toString();
                logger.info(" cert_config serialNumber :" + serialNumber);
            } catch (Exception e) {
                logger.info("No cert_config.yml ");
                subjectName = "CN=TRUST_BANK_SG_HSM_01,OU=001,OU=9733,O=Singapore Automated Clearing House,C=SG";
                issuerName = "CN=SACH_OCA4, O=Singapore Automated Clearing House,C=SG";
                serialNumber = "2200065216";
            }
            x509Content.add(subjectName);
            x509Content.add(kif.newX509IssuerSerial(issuerName, new BigInteger(serialNumber)));
            if (!HSMTools.isLoggedIn()) {//判断已经登录的情况下不再重复登录
                HSMTools.login(user, password, partition, method);
            }
            key1 = SignTools.getPrivateKey("Cavium", alias);
        } else {
            //取本地加密文件
         /*   String privateKey = SignTools.readKeyFromFile(getResourceClass()+"/cer/lxkCert.key");
            System.out.println("私钥能获取到"+privateKey);
            privateKey=privateKey.replaceAll("-----BEGIN PRIVATE KEY-----","").replaceAll("-----END PRIVATE KEY-----","");
*/
            x509Content.add("CN=TRUST_BANK_SG_HSM_01,OU=9733,OU=001,O=Singapore Automated Clearing House,C=SG");
            x509Content.add(kif.newX509IssuerSerial("CN=UAT_OCA4,O=Singapore Automated Clearing House,C=SG", new BigInteger("2200020715")));
            // TODO: 2022/12/19  私有需要配置化，需要继续优化
//            String privateKey = BatchTaskDataSourceInitializer.getPrivateKey();
//            if (privateKey == null || privateKey.length() == 0)
//                throw new NullPointerException("文件的私钥为空");
//            privateKey = privateKey.replaceAll("-----BEGIN PRIVATE KEY-----", "").replaceAll("-----END PRIVATE KEY-----", "");
            String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCrh44H0TKA0i1nhxhKI4KO29z/ClLbKAXu2lvBdHmz50Xj013HffoIdGz2SqXjh5s3qGaq1oou/3gGRxWk36tfeddZc2aqCtYAdLey7OGgrEcdHRD0KAstBb5HAjDllfOxv7dvab9/p3JYd1KWth4h6Fxl8F4NzJeYAAoAFDopxyh88AqGW0Bi/xBUaQOiBe5L4GVOH6c8OW6dzeTeg/SRD3P3sVmoWrQg0AnsZJjGMXIB94MtbautGScVDj3Ofc3qt62LynYsznofxzEYCKE5P0XjjIDQfvtvcRnPJGznAngUg3m4Qiw+5ncOw+DS4p1dsVCIeywJvvhOhHrz2acPAgMBAAECggEAOv5oLvAYOo4O56hjBjyoTpH3tA/M52sgf0HChQqWQPWt5+luO4psUDQatap+235sN/xHM0KOTBO5KA+Sth3nRvMUycMS8DDB4W5DL9qgzy3xV2YDyrVrApUTGED8qDmmkt+pKzxnjHP+s8Z2NTd1rZvJC5ZcAsr26GQ7DEPorS0tu9hIAeJQvBpdBz5KlkWpsty+Zo18DLFFUkx4N638+R+V9c1/CdGkwbYLTh/nt2d5BW3F9nJ62bIbL6GLdVFWpl/qG4tZqHs1DqKQ8H2F77opY0YL/dUuX/5AQmBIuufIIgNdRubBGnLRKRpy4XHAkUrooTz5TsKNbQkvwmX0QQKBgQDc4zD2JnrjOF2aB/ai38YWfZOGwAdjJrDb7W9Ot2u+1SVRRIple28lYspZ6SN94WIGflo2tUOT/BZKNPCk2tqIixcr3ZVfuXaJd9WNMSNOnpLvTbkuIQIg4AUC/ZyA0GJ9LOR/zC3Df0uopXDTqYWc9XFUaL5bPYKhEMOzwjyY1wKBgQDGy8mqoi2oGWCewkFiHYQgCL3Le3g7EG4ntJL8DSD4dWZ9IsfbyYLEFbdmRv/7gl8zwsa889If6Y+kND6LeMj6gfF1PR1cAZ8cqDiEM8gJuD9K8FRDPZSciNPlBQr0F+Qi9DX+qqjgIDDGvA65b0X9sTeO9APuPVveCakCTauEiQKBgQDIXkqWrxdAMGwwAlNHMcgltKnzTRQma/14sqwKcrYJOJ4OVydXoR2Ug0XdQ2/Pi3h5PXqmL81qWvz5OYaldGVSASH2x5ztZXZSguKznkRR1+SfsRhu8MBWg//n05h4/Q6qT+PCzbshoMJH7DMpvZgXZkn0cMNkTqrFYki0Mlj3pwKBgQCvbVxMKIuTwLlDv/aEx7k1r6EU9KMXtBsRDMd8h3Vl6+wCV3P4ny23LdbG+bahUt4CRkqsqZjusmQgqj3bRD5O2eai58iFzaSEXGprN2q6MQ4hak1hfPEs5Y3flwCggIjA07LmReF6zs1cQs3Xk7s28XEuxGTHkvwDx5COSM4nyQKBgGDNmFrdgFy3E0myoyBumYzUYQThxck7gSw87CyHz6IufjlW3QtUfjWhoJ/onC6CyWq9sefQEE60rC6jdrOSaUZDSd7Ed9jxxcTkqEUALmsEhuQOaAm4669RboB1x0pGukhg9qM1quWti5+SlFn+aMGS4XuyZuWH0mNQ9Z5xLE/F";
            key1 = SignTools.readPrivateKeyFromString(privateKey, "RSA");
        }
        X509Data xd = kif.newX509Data(x509Content);
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));
        // Create the XMLSignature, but don't sign it yet.
        XMLSignature signature = fac.newXMLSignature(si, ki);
        // Instantiate the document to be signed.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
        NodeList node = doc.getElementsByTagName("AppHdr");
        //  Element eltName = doc.createElement("head:Sgntr");
    /*    if(xml.contains("head:BizMsgIdr xmlns:head=")||xml.contains("head:MsgDefIdr xmlns:head=")||xml.contains("head:CreDt xmlns:head=")){
            eltName.setAttribute("xmlns:head","urn:iso:std:iso:20022:tech:xsd:head.001.001.01");
        }*/
        NodeList node1 = doc.getElementsByTagName("head:Sgntr");
        if (null == node1 || node1.getLength() < 1) {
            Element eltName = doc.createElement("head:Sgntr");
            if (xml.contains("head:BizMsgIdr xmlns:head=") || xml.contains("head:MsgDefIdr xmlns:head=") || xml.contains("head:CreDt xmlns:head=")) {
                eltName.setAttribute("xmlns:head", "urn:iso:std:iso:20022:tech:xsd:head.001.001.01");
            }
            node.item(0).appendChild(eltName);
            //  return "";
        }
        // NodeList node1 = doc.getElementsByTagName("Message");
        //alias 自己测试的时候用 "SHA256withRSA"，对应导入私钥到hsm的时候给的别名
        System.out.println("私钥串：" + Base64.getEncoder().encodeToString(key1.getEncoded()));
        DOMSignContext dsc = new DOMSignContext(key1, node1.item(0));
        // DOMSignContext dsc = new DOMSignContext(ks.getKey("SHA256withRSA",null), node1.item(0));
        signature.sign(dsc);
        return docToString(doc);
    }


    /**
     * XML org.w3c.dom.Document 转 String
     */
    public static String docToString(Document doc) {
        // XML转字符串
        String xmlStr = "";
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty("encoding", "UTF-8");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            t.transform(new DOMSource(doc), new StreamResult(bos));
            xmlStr = bos.toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return xmlStr;
    }


    public static String xmlStringSign(String xml, String off, String user, String password,
                                       String partition, String method,
                                       String alias) {
        String userName = null;
        String pwd = null;
        if ("on".equals(off)) {
            if (StringUtils.isEmpty(user)) {
                return "HSM User info can not be empty ";
            }
            if (StringUtils.isEmpty(password)) {
                return "HSM password can not be empty ";
            }

            if (StringUtils.isEmpty(alias)) {
                return "HSM alias can not be empty ";
            }
            if (StringUtils.isEmpty(partition)) {
                partition = "PARTITION_1";
            }
            if (StringUtils.isEmpty(method)) {
                method = "explicit";
            }
            userName = user;
            pwd = password;
        }
        logger.info("request xmlStringSign params off=" + off + " ,user=" + user +
                " ,password=" + password + " ,partition=" + partition + " ,method=" + method + " ,alias=" + alias);
        try {
            logger.info("sign before is: " + xml);
            xml = xml.replaceAll("(?<=>)\\s+(?=<)", "");
            String sign = NewXmlSignUtil.signByEnveloed(xml, off, userName, pwd, partition, method, alias);
            sign = signReplce(sign);
            logger.info("sign after is: " + sign);
            return sign;
        } catch (Exception e) {
            logger.info("xmlStringSign sign error. ");
        }
        return "";
    }


    /**
     * 去掉加签后的报文中的换行符号，将+号转义，否则http post请求会将+号转为空格，从而验签失败
     *
     * @return
     */
    private static String signReplce(String sign) {
        if (!StringUtils.isEmpty(sign)) {
            sign = sign.replaceAll("#13;", "");
            //  sign = sign.replace("+","%2B").replaceAll("\n","").replaceAll("\r","");
            // sign = sign.replace("<head:Sgntr>","<head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">");
            sign = sign.replace("&", "");
            int start = sign.indexOf("<SignatureValue>");
            if (start > 0) {
                int end = sign.indexOf("</SignatureValue>");
                String signStr = sign.substring((start + "<SignatureValue>".length()), end);
                signStr = signStr./*replace("+","%2B").*/replaceAll("\n", "").replaceAll("\r", "");
                sign = sign.replace(sign.substring((start + "<SignatureValue>".length()), end).toString(), signStr);
            }
            int start2 = sign.indexOf("<DigestValue>");
            if (start2 > 0) {
                int end = sign.indexOf("</DigestValue>");
                String signStr = sign.substring((start2 + "<DigestValue>".length()), end);
                signStr = signStr./*replace("+","%2B").*/replaceAll("\n", "").replaceAll("\r", "");
                sign = sign.replace(sign.substring((start2 + "<DigestValue>".length()), end).toString(), signStr);
            }
        }
        return sign;

    }

    public static String getResourceClass() {
        return NewXmlSignUtil.class.getClassLoader().getResource("").getPath();
    }

    public static void mains(String[] args) throws Exception {
     /*   String key = FileUtils.loadFile("C:\\Users\\wangxin\\Desktop\\opf加签验签\\lxkCert.jks");
        System.out.println(key);*/
/*
       String ss = FileUtils.loadFile("C:\\\\Users\\\\wangxin\\\\Desktop\\\\admn.006.001.01-Echo Response.xml");


        String xmls = xmlStringSign(ss, "off", "demo",
                "demo@123",
                "", "", "");
        System.out.println(
                xmls
        );*/
        String sign = "<Message><SignatureValue>+123323+</SignatureValue><aaa></aaa></Message>";
        int start = sign.indexOf("<SignatureValue>");
        if (start > 0) {
            int end = sign.indexOf("</SignatureValue>");
            String signStr = sign.substring((start + "<SignatureValue>".length()), end);
            signStr = signStr.replace("+", "%2B");
            // signStr = signStr.replace("&","");
            sign = sign.replace(sign.substring((start + "<SignatureValue>".length()), end).toString(), signStr);
        }
        System.out.println(sign);

        /*   //String xmls = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Message xmlns=\"urn:bcsis\" xmlns:er=\"urn:iso:std:iso:20022:tech:xsd:admn.005.001.01\" xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:bcsis G3.Real-Time.Echo.Request.01.xsd\"><AppHdr><head:Fr><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>TRBUSG50XXX</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>SACHSGS0XXX</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr>B20210902TRBUSGS0BAD5134001</head:BizMsgIdr><head:MsgDefIdr>admn.005.001.01</head:MsgDefIdr><head:CreDt>2021-09-02T23:42:27.027Z</head:CreDt><head:Sgntr><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><DigestValue>D2ybGNAvyFtTKfbBgIBjatwBaWhRaT7ZoV/XJtN2vSA=</DigestValue></Reference></SignedInfo><SignatureValue>g6LVM0Ro/kqRgqwXJzdN4PUtecSEpoZXusBUJaaw/+FffmNAiN0H/itWl6RYkJPq+gHFMnCRA8qqSF0jEASAz6wdDywa/KaZYfb4OiiSyc1KNRYjDXR+ygo78saOEF+b5qAmx8PIEzp8TAnZjl3f5LGVBtCTotDUP+NNC/eNpzITbfdRnjPzQncp5y/tYob80PrK8rw7NKxQuNlHUvPELLpC8YVxSEhSIM+nIVjVlYeKAR0LYvB8LB/q26u+rynfvJ7F1BCLlQbFt7tVal8P3UvvVSwXTNHZGyDcjovAcYCEDWTONKSuukyGksjMsKDLZeIvrr0IXUcOd+YKq44eYg==</SignatureValue><KeyInfo><X509Data><X509SubjectName>CN=error param</X509SubjectName><X509IssuerSerial><X509IssuerName>CN=UAT_OCA4,O=Singapore Automated Clearing House,C=SG</X509IssuerName><X509SerialNumber>2200020715</X509SerialNumber></X509IssuerSerial></X509Data></KeyInfo></Signature></head:Sgntr></AppHdr><EchoRequest><er:AdmnEchoReq><er:GrpHdr><er:MsgId>B20210902TRBUSGS0BAD5134001</er:MsgId><er:CreDtTm>2021-09-02T23:42:27.027</er:CreDtTm></er:GrpHdr><er:EchoTxInf><er:FnctnCd>731</er:FnctnCd><er:InstrId>20210902TRBUSGS0BAD5134001</er:InstrId><er:InstgAgt><er:FinInstnId><er:ClrSysMmbId><er:MmbId>TRBUSG50XXX</er:MmbId></er:ClrSysMmbId></er:FinInstnId></er:InstgAgt><er:InstdAgt><er:FinInstnId><er:ClrSysMmbId><er:MmbId>SACHSGS0XXX</er:MmbId></er:ClrSysMmbId></er:FinInstnId></er:InstdAgt></er:EchoTxInf></er:AdmnEchoReq></EchoRequest></Message>";
         */
        //String xmls = FileUtils.loadFile("C:\\Users\\wangxin\\Desktop\\admn.006.001.01-Echo Response.xml");


       /* String pubKey = SignTools.readKeyFromFile(getResourceClass() + "/cer/lxkCert.crt");
        pubKey = pubKey.replaceAll("-----BEGIN CERTIFICATE-----", "").replaceAll("-----END CERTIFICATE-----", "");
        System.out.println(xmls);
        boolean b = SignUtils.isXmlDigitalSignatureValid(xmls, pubKey, false);

        System.out.println("验签完成，结果为：" + b);*/

    }

}
