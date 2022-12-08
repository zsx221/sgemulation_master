package com.macrochina.net.utils;

import org.apache.logging.log4j.util.Strings;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class SignTools {
    /**
     *
     * @param publicKeyFile 公钥文件路径
     * @return
     */
    public static PublicKey getPublicKeyByPath(String publicKeyFile){
        try {
            if (Strings.isEmpty(publicKeyFile)) {
                return null;
            }
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            FileInputStream bais = new FileInputStream(publicKeyFile);
            X509Certificate Cert = (X509Certificate) certificatefactory.generateCertificate(bais);
            PublicKey pks = Cert.getPublicKey();
            return pks;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param signingKey 加签的私钥
     * @param data  要加签的数据
     * @param algorithm SHA256withRSA等等
     * @param provider 如果使用HSM，值为Cavium
     */
    public static String doSign(PrivateKey signingKey,byte[] data,String algorithm,String provider) {
        String res=null;
        if(signingKey==null||data==null||data.length<1||Strings.isEmpty(algorithm)||Strings.isEmpty(provider)){
            return res;
        }
        try {
            Signature signatureInstance = Signature.getInstance(algorithm, provider);
            signatureInstance.initSign(signingKey);
            signatureInstance.update(data);
            byte[] b=signatureInstance.sign();
            res=new String(Base64.getEncoder().encode(b));
        } catch (SignatureException ex) {
            ex.printStackTrace();
        } catch (NoSuchProviderException | InvalidKeyException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     *
     * @param signingKey 加签的私钥
     * @param data  要加签的数据
     * @param algorithm SHA256withRSA等等
     */
    public static String doSign(PrivateKey signingKey,byte[] data,String algorithm) {
        String res=null;
        if(signingKey==null||data==null||data.length<1||Strings.isEmpty(algorithm)){
            return res;
        }
        try {
            Signature signatureInstance = Signature.getInstance(algorithm);
            signatureInstance.initSign(signingKey);
            signatureInstance.update(data);
            byte[] b=signatureInstance.sign();
            res=new String(Base64.getEncoder().encode(b));
        } catch (SignatureException ex) {
            ex.printStackTrace();
        } catch (InvalidKeyException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static KeyStore getKeyStore(String provider){
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance(provider);
            keyStore.load(null, null);

        } catch (KeyStoreException | CertificateException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyStore;
    }

    /**
     *
     * @param provider 如果使用HSM，值为Cavium
     * @param alias 私钥别名
     * @return
     */
    public static PrivateKey getPrivateKey(String provider,String alias){
        PrivateKey res=null;
        if(Strings.isEmpty(provider)||Strings.isEmpty(alias)){
            return res;
        }

        try {
            KeyStore keyStore = getKeyStore(provider);
            res = (PrivateKey) keyStore.getKey(alias, null);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     *
     * @param user  登录HSM用户名
     * @param password 登录HSM密码
     * @param partition HSM存储分区，值为PARTITION_1
     * @param method   登录方式，值为explicit
     * @param provider 如果使用HSM，值为Cavium
     * @param alias    密钥别名
     * @param data     加签数据
     * @param algorithm 加签算法
     * @return
     */
    public static String sign(String user,String password,String partition,String method,String provider,String alias,byte[] data,String algorithm){
        String res=null;
        try{
            HSMTools.login(user,password,partition,method);
        }catch (Exception e){
            return res;
        }
        PrivateKey pk=getPrivateKey(provider,alias);
        if(pk!=null){
            res=doSign(pk,data,algorithm,provider);
        }else{
            return res;
        }

        return res;

    }

    /**
     * 公钥验签
     * @param content 报文
     * @param publicKey 公钥
     * @param sign 签名值
     * @param keyAlgorithm
     * @param sign signAlgorithm
     * @return 验签是否通过
     */
    public static boolean verifySignByPublicKey(String content,String publicKey,String sign,String keyAlgorithm,String signAlgorithm)
    {
        try
        {
            PublicKey pubKey=getPublicKeyByStr(publicKey);
            Signature signature = Signature.getInstance(signAlgorithm);//SHA256withRSA
            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            return signature.verify(Base64.getDecoder().decode(sign));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 从文件中读取公钥或私钥
     * @param filePath 文件路径
     * @return 公钥或私钥
     */
    public static String readKeyFromFile(String filePath)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while((readLine = br.readLine()) != null)
            {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从字符串中加载私钥
     * @return 私钥
     */
    public static RSAPrivateKey readPrivateKeyFromString(String privateKeyStr,String algorithm)
    {
        try
        {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);//RSA
            return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeySpecException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取公钥
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKeyByStr(String key)  {
        PublicKey publicKey=null;
        try{
            byte[] keyBytes = Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8));
            CertificateFactory certificatefactory=CertificateFactory.getInstance("X.509");
            InputStream in = new ByteArrayInputStream(keyBytes);
            X509Certificate Cert = (X509Certificate)certificatefactory.generateCertificate(in);
            publicKey = Cert.getPublicKey();
        }catch (Exception e){
            return  publicKey;
        }
        return publicKey;
    }

    public static void mains(String[] args) {
        /*String privateKeyPath = "C:\\Users\\Lenovo\\Desktop\\myss_pri.pem";
        String publicKeyPath = "C:\\Users\\Lenovo\\Desktop\\myss.crt";

        String privateKey=readKeyFromFile(privateKeyPath);
        String publicKey=readKeyFromFile(publicKeyPath);

        privateKey=privateKey.replaceAll("-----BEGIN PRIVATE KEY-----","").replaceAll("-----END PRIVATE KEY-----","");
        publicKey=publicKey.replaceAll("-----BEGIN CERTIFICATE-----","").replaceAll("-----END CERTIFICATE-----","");

        System.out.println("----------------privateKey-----------------");
        System.out.println(privateKey);
        System.out.println("----------------publicKey-----------------");
        System.out.println(publicKey);

        PrivateKey privateK=readPrivateKeyFromString(privateKey,"RSA");
        byte [] data="我是需要加签的报文字符串".getBytes(StandardCharsets.UTF_8);
        String sign=doSign(privateK,data,"SHA256withRSA");
        System.out.println("加签后的字符串为："+sign);


        //verify
        boolean flag= verifySignByPublicKey(new String(data,StandardCharsets.UTF_8),publicKey,sign,"RSA","SHA256withRSA");

        System.out.println("验签结果为:"+flag);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");*/


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>HSM Start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        String user="demo";
        String password="demo@123";
        String partition="PARTITION_1";
        String method="explicit";
        String provider="Cavium";
        String alias="lxkCertrsa2048";
        byte [] data="我是需要加签的报文字符串".getBytes(StandardCharsets.UTF_8);
        String algorithm="SHA256withRSA";
        String sign=sign(user,password,partition,method,provider,alias,data,algorithm);
        System.out.println("sing:"+sign);
        String publicKeyPath = "/home/centos/lxk/lxkCert.crt";
        String publicKey=readKeyFromFile(publicKeyPath);
        System.out.println(publicKey);
        publicKey=publicKey.replaceAll("-----BEGIN CERTIFICATE-----","").replaceAll("-----END CERTIFICATE-----","");
        boolean flag= verifySignByPublicKey(new String(data,StandardCharsets.UTF_8),publicKey,sign,"RSA","SHA256withRSA");
        System.out.println("---------------------");
        System.out.println("验签结果:"+flag);
    }


}
