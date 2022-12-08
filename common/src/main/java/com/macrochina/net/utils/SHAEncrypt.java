package com.macrochina.net.utils;

import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAEncrypt {
    /**
     *  利用Apache的工具类实现SHA-256加密
     *  所需jar包下載 http://pan.baidu.com/s/1nuKxYGh
     * @param str 加密的字符串，encdeStr密钥
     * @return 加密后的字符
     */
    public static String String2SHA256(String str,String encdeStr) {
        if(StringUtils.isEmpty(str)||StringUtils.isEmpty(encdeStr)){
            return null;
        }
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    public static void mains(String[] args) {
        String s=SHAEncrypt.String2SHA256("wdm666","apeit#@!");
        System.out.println(s);
    }
}
