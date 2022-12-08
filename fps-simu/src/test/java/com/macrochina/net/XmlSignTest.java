package com.macrochina.net;


public class XmlSignTest {

   /* @Test
    public void test() throws Exception {
        XmlSignProperties pro = new XmlSignProperties();
        pro.setAlias("selfsigned");
        pro.setKeyPasswd("password");
        pro.setStorePasswd("password");
        //pro.setCertFile("C:\\Users\\wangxin\\Desktop\\keystore.p12");
        pro.setCertFile("C:\\Users\\wangxin\\Desktop\\opf加签验签\\lxkCert.key");
        pro.setCertType("PKCS12");
        String xmls = "<message><a>aadsdfsdfsd</a></message>";
        String signXml = XmlSignUtil.generateXMLSignature(xmls, pro);
        System.out.println(signXml);

    }


    @Test
    public void createSign() throws Exception {
        String priKey =  SignTools.readKeyFromFile("C:\\Users\\wangxin\\Desktop\\opf加签验签\\lxkCert.key");
        priKey=priKey.replaceAll("-----BEGIN PRIVATE KEY-----","").replaceAll("-----END PRIVATE KEY-----","");
        byte xmls[] = "<message><a>aadsdfsdfsd</a></message>".getBytes(StandardCharsets.UTF_8);
        PrivateKey privateK=SignTools.readPrivateKeyFromString(priKey,"RSA");
        String sign=SignTools.doSign(privateK ,xmls,"SHA256withRSA");
        System.out.println("加签后的字符串为："+sign);
    }

    @Test
    public void test2() throws Exception {
       String pubKey =  SignTools.readKeyFromFile("C:\\Users\\wangxin\\Desktop\\opf加签验签\\lxkCert.crt");
        pubKey=pubKey.replaceAll("-----BEGIN CERTIFICATE-----","").replaceAll("-----END CERTIFICATE-----","");
        String xmls = "<message><a>aadsdfsdfsd</a></message>";
        String sign = "k130kMv2qXAV32Jit8XZ8v70bXBN4r863UJvPY5m076Lt5oRgvixugsTjt8RGrf0BYCRa6gQgAniQq+ePXBBm/CQQuJyX+PJyRwDu/X9Zgm4hGltc7rVGQVr7WY4dWcsAOnBpBgWGKux2CGVqy4xd5esTnWp2GKxXTR/dEqa+hWqDbLGSeW90NPNraZx4hV/ecbjCX5sL7mh+sb5cBwWBa7Zj9qA2JxeVC9JakEzHv0OuLPV1QfyMu1246O4lrMqBNi39BhX1WCX6hjjuxdEnhFJfXKN2C+qUeCGa1cNMtVqzRzk8VaCITZpwmCZGbzEoKqSS6b8DexpsZoH21IBzQ==";
        byte [] data=xmls.getBytes(StandardCharsets.UTF_8);
        boolean flag= SignTools.verifySignByPublicKey(new String(data,StandardCharsets.UTF_8),pubKey,sign,"RSA","SHA256withRSA");
        System.out.println(flag);

    }

    @Test
    public void aaaa() throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        docBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        docBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        docBuilderFactory.setNamespaceAware(true);

        String priKey =  SignTools.readKeyFromFile("C:\\Users\\wangxin\\Desktop\\opf加签验签\\lxkCert.key");
        priKey=priKey.replaceAll("-----BEGIN PRIVATE KEY-----","").replaceAll("-----END PRIVATE KEY-----","");
        byte xmls[] = "<message><a>aadsdfsdfsd</a></message>".getBytes(StandardCharsets.UTF_8);
        PrivateKey privateK=SignTools.readPrivateKeyFromString(priKey,"RSA");
        Document doc = docBuilderFactory.newDocumentBuilder().parse(new ByteArrayInputStream(xmls));
        Init.init();

       *//* SignedInfo info = new SignedInfo(doc,"http://www.w3.org/2001/04/xmldsig#rsa-sha256","http://www.w3.org/TR/2001/REC-xml-c14n-20010315");

        Manifest mani = new Manifest(doc);
        Transforms transforms = new Transforms(doc);
        transforms.addTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature");
        transforms.addTransform("http://www.w3.org/2006/12/xml-c14n11");
      //  Reference reference = new Reference(doc,"","",mani,transforms,"http://www.w3.org/2001/04/xmlenc#sha256");

        info.addDocument("","",transforms,"http://www.w3.org/2001/04/xmlenc#sha256","","");*//*

        XMLSignature si = new XMLSignature(doc,"http://www.w3.org/2001/04/xmldsig-more#",XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256);
        si.sign(privateK);

        System.out.println(Base64.encode(si.getSignatureValue()));

    }*/



}
