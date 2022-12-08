package com.macrochina.net.utils;

import org.apache.commons.text.StringEscapeUtils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {

    public static String convertMapToKeyValueStr(Map map) {
        String rtn="";
        try {
            if(map==null||map.keySet().size()<=0){
                return rtn;
            }else{
                StringBuffer stringBuffer=new StringBuffer();
                for (Object key:map.keySet()) {
                    Object value=map.get(key);
                    value=value==null?"":value;
                    stringBuffer.append(key.toString()+"="+value.toString()+"&");
                }
                rtn=stringBuffer.toString();
                return  rtn;
            }
        } catch (Exception e) {
            return rtn;
        }
    }



    /**
     * @param input
     * @return
     */
    public static String stringMD5(String input) {
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest =MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes();
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return new String(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }


    public static String getRandomNumber(int length){
        String rtn=new String();
        if(length<=0){
            return  rtn;
        }
        StringBuilder s=new StringBuilder("1");
        for(int i=1;i<=length;i++){
            s.append("0");
        }
        Long lg=Long.valueOf(s.toString());
        rtn=(int)(Math.random() * lg)+lg+"";
        return  rtn;
    }


    /**
     *
     * @param str
     * @param length
     * @return
     */
    public static String appendZeroInLeft(String str,int length){
        if(str==null||str.trim().equals("")){
            return  str;
        }
        String rtn=new String();
        StringBuilder s=new StringBuilder("");
        int tempInt=length-str.length();
        if(tempInt>0){
            for(int i=1;i<=tempInt;i++){
                s.append("0");
            }
        }
        rtn=s.toString()+str;
        return  rtn;
    }

    public static void mains(String[] args) {
        //System.out.println(getRandomNumber(6));
        System.out.println(appendZeroInLeft("1",6));
    }


        public static boolean isDigit(char sch)
        {
            int temp = sch & 0xFF;
            return (temp >= 45) && (temp <= 57);
        }



        public static boolean isLowerCase(char sch)
        {
            int temp = sch & 0xFF;
            return (temp >= 97) && (temp <= 122);
        }



        public static boolean isUppercase(char sch)
        {
            int temp = sch & 0xFF;
            return (temp >= 65) && (temp <= 90);
        }

        public static String bytesToHexString(byte[] bytes)
        {
            StringBuilder sBuilder = new StringBuilder("");
            if ((bytes != null) && (bytes.length > 0)) {
                for (byte byitem : bytes)
                {
                    int tmpv = byitem & 0xFF;
                    String thv = Integer.toHexString(tmpv);
                    if (thv.length() < 2) {
                        sBuilder.append(0);
                    }
                    sBuilder.append(thv);
                }
            }
            return sBuilder.toString();
        }






        public static boolean isEmpty(Object object)
        {
            return null == object;
        }

        public static boolean isNotEmpty(Object object)
        {
            return !isEmpty(object);
        }

        public static boolean isBlank(String string)
        {
            return (isEmpty(string)) || ("".equals(string.trim()));
        }

        public static boolean isNotBlank(String string)
        {
            return (isNotEmpty(string)) && (!"".equals(string.trim()));
        }

        public static boolean isEffective(Integer value)
        {
            return (null != value) && (value.intValue() > 0);
        }

        public static boolean isEffective(Long value)
        {
            return (null != value) && (value.longValue() > 0L);
        }

        public static String toUTF8(String string)
        {
            if (isBlank(string)) {
                throw new IllegalArgumentException("The param can not be null.");
            }
            try
            {
                return new String(string.getBytes("utf-8"), "iso-8859-1");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            return string;
        }

        public static String toGBK(String string)
        {
            if (isBlank(string)) {
                throw new IllegalArgumentException("The param can not be null.");
            }
            try
            {
                return new String(string.getBytes("gbk"), "iso-8859-1");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            return string;
        }

        public static String byteArrayStreamToString(ByteArrayOutputStream baos)
        {
            return byteArrayStreamToString(baos, "utf-8");
        }

        public static String byteArrayStreamToString(ByteArrayOutputStream baos, String charset)
        {
            if (null == baos) {
                throw new IllegalArgumentException("The param baos can not be null.");
            }
            try
            {
                return baos.toString("utf-8");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        public static String excNullToString(String string)
        {
            return excNullToString(string, "");
        }

        public static String excNullToString(String string, String added)
        {
            if (isEmpty(string)) {
                string = added;
            }
            return string;
        }

        public static Object excNullToObject(Object obj)
        {
            return excNullToObject(obj, new Object());
        }

        public static Object excNullToObject(Object obj, Object added)
        {
            if (isEmpty(obj)) {
                obj = added;
            }
            return obj;
        }

        public static String objectToString(Object obj)
        {
            return objectToString(obj, null);
        }

        public static String objectToString(Object obj, String added)
        {
            String result = added;
            if (obj != null) {
                result = String.valueOf(obj);
            }
            return result;
        }

        public static int stringToInt(String string)
        {
            return stringToInt(string, 0);
        }

        public static int stringToInt(String string, int added)
        {
            int result = 0;
            try
            {
                result = Integer.parseInt(string);
            }
            catch (Exception e)
            {
                result = added;
            }
            return result;
        }

        public static int objectToInt(Object obj)
        {
            return objectToInt(obj, 0);
        }

        public static int objectToInt(Object obj, int added)
        {
            if ((obj instanceof Integer)) {
                return ((Integer)obj).intValue();
            }
            if ((obj instanceof Float)) {
                return ((Float)obj).intValue();
            }
            if ((obj instanceof Double)) {
                return ((Double)obj).intValue();
            }
            return stringToInt(objectToString(obj), added);
        }

        public static long stringToLong(String string)
        {
            return stringToLong(string, 0L);
        }

        public static long stringToLong(String string, long added)
        {
            long result = 0L;
            try
            {
                result = Long.parseLong(string);
            }
            catch (Exception e)
            {
                result = added;
            }
            return result;
        }

        public static long objectToLong(Object obj)
        {
            return objectToLong(obj, 0L);
        }

        public static long objectToLong(Object obj, long added)
        {
            long result = 0L;
            try
            {
                result = Long.parseLong(obj.toString());
            }
            catch (Exception e)
            {
                result = added;
            }
            return result;
        }

        public static float stringToFloat(String string)
        {
            return stringToFloat(string, 0.0F);
        }

        public static float stringToFloat(String string, float added)
        {
            float result = 0.0F;
            try
            {
                result = Float.parseFloat(string);
            }
            catch (Exception e)
            {
                result = added;
            }
            return result;
        }

        public static float objectToFloat(Object obj)
        {
            return objectToFloat(obj, 0.0F);
        }

        public static float objectToFloat(Object obj, float added)
        {
            float result = 0.0F;
            try
            {
                result = Float.parseFloat(obj.toString());
            }
            catch (Exception e)
            {
                result = added;
            }
            return result;
        }

        public static double stringToDouble(String string)
        {
            return stringToDouble(string, 0.0D);
        }

        public static double stringToDouble(String string, double added)
        {
            double result = 0.0D;
            try
            {
                result = Double.parseDouble(string);
            }
            catch (Exception e)
            {
                result = added;
            }
            return result;
        }

        public static double objectToDouble(Object obj)
        {
            return objectToDouble(obj, 0.0D);
        }

        public static double objectToDouble(Object obj, double added)
        {
            double result = 0.0D;
            try
            {
                result = Double.parseDouble(obj.toString());
            }
            catch (Exception e)
            {
                result = added;
            }
            return result;
        }

        public static String firstCharToUpperCase(String content)
        {
            if (!isEmpty(content))
            {
                String tou = content.substring(0, 1);
                String wei = content.substring(1);
                content = tou.toUpperCase() + wei;
            }
            return content;
        }

        public static String firstCharToLowerCase(String content)
        {
            if (!isEmpty(content))
            {
                String tou = content.substring(0, 1);
                String wei = content.substring(1);
                content = tou.toLowerCase() + wei;
            }
            return content;
        }

        public static String stringUncode(String param)
        {
            if ((param != null) && (!param.trim().equals(""))) {
                try
                {
                    System.out.println(StringEscapeUtils.unescapeHtml4(param));
                    param = URLDecoder.decode(param, "utf-8");
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
            }
            return param;
        }

        public static String stringEncode(String param)
        {
            if ((param != null) && (!param.trim().equals(""))) {
                try
                {
                    param = URLEncoder.encode(param, "utf-8");
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
            }
            return param;
        }

        public static String format(String resource, String... target)
        {
            if (resource == null) {
                throw new IllegalArgumentException("parameter is null");
            }
            if ((target != null) && (target.length > 0))
            {
                int i = 0;
                for (int k = target.length; i < k; i++) {
                    resource = resource.replace("{" + i + "}", target[i]);
                }
            }
            return resource;
        }

        public static long getTimeUnix()
        {
            return new Date().getTime();
        }

        public static float round(float number)
        {
            return round(number, 2);
        }

        public static float round(float number, int len)
        {
            return round(number, len, RoundingMode.UP);
        }

        public static float round(float number, int len, RoundingMode mode)
        {
            return new BigDecimal(number).setScale(len, mode).floatValue();
        }

        public static double round(double number, int len)
        {
            return round(number, len, RoundingMode.UP);
        }

        public static double round(double number, int len, RoundingMode mode)
        {
            return new BigDecimal(number).setScale(len, mode).doubleValue();
        }


        public static String getUUID()
        {
            String uuid = UUID.randomUUID().toString();
            return uuid.replaceAll("-", "");
        }

        public static String parseTemplate(String content, String key, String value)
        {
            try
            {
                if (content != null) {
                    content = content.replaceAll("#\\{" + key + "\\}", value);
                }
            }
            catch (Exception localException) {}
            return content;
        }

        public static String parseTemplate(String content, Map<String, String> data)
        {
            try
            {
                if ((content != null) && (data != null)) {
                    for (Map.Entry<String, String> entry : data.entrySet()) {
                        content = content.replaceAll("#\\{" + (String)entry.getKey() + "\\}", (String)entry.getValue());
                    }
                }
            }
            catch (Exception localException1) {}
            return content;
        }

        public static String deleteWhitespace(String str)
        {
            if (isEmpty(str)) {
                return str;
            }
            int sz = str.length();
            char[] chs = new char[sz];
            int count = 0;
            for (int i = 0; i < sz; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    chs[(count++)] = str.charAt(i);
                }
            }
            if (count == sz) {
                return str;
            }
            return new String(chs, 0, count);
        }

}
