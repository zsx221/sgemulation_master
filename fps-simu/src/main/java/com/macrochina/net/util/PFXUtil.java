package com.macrochina.net.util;


import java.io.*;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Enumeration;

public class PFXUtil {

    /**
     * 获取RSA算法的keyFactory
     *
     * @return
     */
    private static KeyFactory getKeyFactory() throws Exception {
        return getKeyFactory("RSA");
    }

    /**
     * 获取指定算法的keyFactory
     *
     * @param algorithm
     * @return
     */
    private static KeyFactory getKeyFactory(String algorithm) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory;
    }

    /**
     * 根据pfx证书获取keyStore
     *
     * @param pfxData
     * @param password
     * @return
     * @throws Exception
     */
    public static KeyStore getKeyStore(FileInputStream  pfxData, String password) throws Exception {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(pfxData, password.toCharArray());
        return keystore;
    }

    /**
     * 根据pfx证书得到私钥
     *
     * @param pfxData
     * @param password
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyByPfx(FileInputStream  pfxData, String password) throws Exception {
        PrivateKey privateKey = null;
        KeyStore keystore = getKeyStore(pfxData, password);
        Enumeration<String> enums = keystore.aliases();
        String keyAlias = "";
        while (enums.hasMoreElements()) {
            keyAlias = enums.nextElement();
            if (keystore.isKeyEntry(keyAlias)) {
                privateKey = (PrivateKey) keystore.getKey(keyAlias, password.toCharArray());
            }
        }
        return privateKey;
    }

    /**
     * 根据pfx证书得到私钥
     *
     * @param pfxPath
     * @param password
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyByPfx(String pfxPath, String password) throws Exception {
        //File pfxFile = new File(pfxPath);
        FileInputStream fis = new FileInputStream(pfxPath);
        return getPrivateKeyByPfx(fis, password);
    }

    /**
     * 根据私钥字节数组获取私钥对象
     *
     * @param privateKeyByte
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(byte[] privateKeyByte) throws Exception {
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyByte);
        KeyFactory keyFactory = getKeyFactory();
        privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 根据私钥Base64字符串获取私钥对象
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String privateKeyStr) throws Exception {
        byte[] privateKeyByte = Base64.getDecoder().decode(privateKeyStr);
        return getPrivateKey(privateKeyByte);
    }

    /**
     * 根据公钥字节数组获取公钥
     *
     * @param publicKeyByte 公钥字节数组
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(byte[] publicKeyByte) throws Exception {
        PublicKey publicKey = null;
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyByte);
        KeyFactory keyFactory = getKeyFactory();
        publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 根据公钥base64字符串获取公钥
     *
     * @param publicKeyStr Base64编码后的公钥字节数组
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String publicKeyStr) throws Exception {
        byte[] publicKeyByte = Base64.getDecoder().decode(publicKeyStr);
        return getPublicKey(publicKeyByte);
    }

    /**
     * 根据pfx证书获取证书对象
     *
     * @param pfxData  pfx的字节数组
     * @param password pfx证书密码
     * @return
     * @throws Exception
     */
    public static X509Certificate getX509Certificate(FileInputStream  pfxData, String password) throws Exception {
        X509Certificate x509Certificate = null;
        KeyStore keystore = getKeyStore(pfxData, password);
        Enumeration<String> enums = keystore.aliases();
        String keyAlias = "";
        while (enums.hasMoreElements()) {
            keyAlias = enums.nextElement();
            if (keystore.isKeyEntry(keyAlias)) {
                x509Certificate = (X509Certificate) keystore.getCertificate(keyAlias);
            }
        }
        return x509Certificate;
    }

    /**
     * 根据pfx证书获取证书对象
     *
     * @param pfxPath  pfx证书路径
     * @param password pfx证书密码
     * @return
     * @throws Exception
     */
    public static X509Certificate getX509Certificate(String pfxPath, String password) throws Exception {
        //File pfxFile = new File(pfxPath);
        FileInputStream in=new FileInputStream(pfxPath);
        return getX509Certificate(in, password);
    }
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

    //生成pkcs12

    /**
     * 根据私钥、公钥证书、密码生成pkcs12
     *
     * @param privateKey      私钥
     * @param x509Certificate 公钥证书
     * @param password        需要设置的密钥
     * @return
     * @throws Exception
     */
    public static byte[] generatorPkcx12(PrivateKey privateKey, X509Certificate x509Certificate, String password)
            throws Exception {
        Certificate[] chain = {x509Certificate};
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(null, password.toCharArray());
        keystore.setKeyEntry(x509Certificate.getSerialNumber().toString(), privateKey, password.toCharArray(), chain);
        ByteArrayOutputStream bytesos = new ByteArrayOutputStream();
        keystore.store(bytesos, password.toCharArray());
        byte[] bytes = bytesos.toByteArray();
        return bytes;
    }

    //合成pfx

    /**
     * 根据私钥、公钥证书、密钥，保存为pfx文件
     *
     * @param privateKey      私钥
     * @param x509Certificate 公钥证书
     * @param password        打开pfx的密钥
     * @param saveFile        保存的文件
     * @return
     * @throws Exception
     */
    public static String generatorPFX(PrivateKey privateKey, X509Certificate x509Certificate, String password, File
            saveFile) throws Exception {
        //判断文件是否存在
        if (!saveFile.exists()) {
            //判断文件的目录是否存在
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            saveFile.createNewFile();
        }
        byte[] pkcs12Byte = generatorPkcx12(privateKey, x509Certificate, password);
        //FileUtils.writeByteArrayToFile(saveFile, pkcs12Byte);
        return saveFile.getPath();
    }


    public static void mains(String[] args) {
        String pfxPath ="C:\\Users\\Lenovo\\Desktop\\myss.pfx";
        String password = "123456";
        try {
            //私钥：pfx文件中获取私钥对象
            PrivateKey privateKey  = getPrivateKeyByPfx(pfxPath, password);
            byte[] privateKeyByte = privateKey.getEncoded();
            String privateKeyStr = Base64.getEncoder().encodeToString(privateKeyByte);
            System.out.println("私钥Base64字符串：" + privateKeyStr);
            //=====私钥Base64字符串转私钥对象
            PrivateKey privateKey2 = getPrivateKey(privateKeyStr);
            System.out.println("私钥Base64字符串2：" + Base64.getEncoder().encode(privateKey2.getEncoded()));
            //证书：从pfx文件中获取证书对象
            X509Certificate certificate = getX509Certificate(pfxPath, password);

            System.out.println("证书主题：" + certificate.getSubjectDN().getName());
            String publicKeyStr = Base64.getEncoder().encodeToString(certificate.getPublicKey().getEncoded());
            System.out.println("公钥Base64字符串：" + publicKeyStr);
            //=====根据公钥Base64字符串获取公钥对象
            System.out.println("公钥Base64字符串2：" + Base64.getEncoder().encodeToString(getPublicKey(publicKeyStr).getEncoded()));
//        //PFX：合成pfx（需要私钥、公钥证书）
//        String savePath = generatorPFX(privateKey, certificate, "1", new File
//                ("C:\\Users\\irving\\Desktop\\idrv4.pfx"));
//        System.out.println(savePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
