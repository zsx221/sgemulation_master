package com.macrochina.net.service;



import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.ldap.LDAPUser;
import com.macrochina.net.ldap.LdapServices;
import com.macrochina.net.util.SignUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class XmlSignService {
    private static Log logger = LogFactory.getLog(XmlSignService.class);
    @Autowired
    LdapServices ldapServices;
    @Autowired
    private  FpsParamService fpsParamService;

/*    @Autowired
    private XmlSignRepository xmlSignRepository;*/

    public static int filter(String s,String sub){// s:xml   sub :</Message>  计算</Message>出现的次数
        int old_length=s.length();
        String replace="";
        if (s.contains(sub)){
            replace = s.replace(sub, "");//将需要查找的字符串替换为空
        }
        int new_length= replace.length();//用原来字符串的长度去减替换过的字符串就是该字符串中字符出现的次数
        int count=(old_length-new_length)/(sub.length());//因为是字符串中字符出现的次数,所以要除以字符串你的长度最后就是字符串在另一个字符串中出现的次数
        return count;
    }

    public boolean verifySignByPublicKey(String xml,String off){//通过公钥来验证
        logger.info("The current log4j version is 2.17.0");
        FpsParam param = fpsParamService.findById();
        String isSign = null;
        if(param!=null){
            isSign = param.getSignatureVerification();
        }
        if("0".equals(isSign)){
            logger.info("verifySignByPublicKey return true");
            return true;
        }
        String publicKey = null;
        boolean flag = false;
        try {
        /*    xml = signReplce(xml);
            if(xml.contains("%2B")){
                xml = xml.replace("%2B","+");
            }*/
            logger.info("Attestation xml is  :"+xml);
         /*   String x509SubjectName = getX509SubjectNameValue(xml);
            if(StringUtils.isEmpty(x509SubjectName)){
                logger.info("No x509SubjectName information ");
                return false;
            }
            LDAPUser user = ldapServices.findOne(x509SubjectName);
            if (null != user && !StringUtils.isEmpty(user.getSshPublicKey())) {
                publicKey = user.getSshPublicKey();
            } else {
                logger.info("No public key information is configured ");*/
                //取本地公钥验签失败
            publicKey = "MIIDijCCAnICCQD2SaOcLUvStTANBgkqhkiG9w0BAQsFADCBhjEsMCoGA1UEAwwjVEVTVF9HQ19TQ19CQU5LX1NPTFVUSU9OU19TR19IU01fMDExDTALBgNVBAsMBDk3MzMxDTALBgNVBAsMBDAwMQoxKzApBgNVBAoMIlNpbmdhcG9yZSBBdXRvbWF0ZWQgQ2xlYXJpbmcgSG91c2UxCzAJBgNVBAYTAlNHMB4XDTIxMDczMTA3MDMzN1oXDTIyMDczMTA3MDMzN1owgYYxLDAqBgNVBAMMI1RFU1RfR0NfU0NfQkFOS19TT0xVVElPTlNfU0dfSFNNXzAxMQ0wCwYDVQQLDAQ5NzMzMQ0wCwYDVQQLDAQwMDEKMSswKQYDVQQKDCJTaW5nYXBvcmUgQXV0b21hdGVkIENsZWFyaW5nIEhvdXNlMQswCQYDVQQGEwJTRzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKuHjgfRMoDSLWeHGEojgo7b3P8KUtsoBe7aW8F0ebPnRePTXcd9+gh0bPZKpeOHmzeoZqrWii7/eAZHFaTfq19511lzZqoK1gB0t7Ls4aCsRx0dEPQoCy0FvkcCMOWV87G/t29pv3+nclh3Upa2HiHoXGXwXg3Ml5gACgAUOinHKHzwCoZbQGL/EFRpA6IF7kvgZU4fpzw5bp3N5N6D9JEPc/exWahatCDQCexkmMYxcgH3gy1tq60ZJxUOPc59zeq3rYvKdizOeh/HMRgIoTk/ReOMgNB++29xGc8kbOcCeBSDebhCLD7mdw7D4NLinV2xUIh7LAm++E6EevPZpw8CAwEAATANBgkqhkiG9w0BAQsFAAOCAQEAc/fcMwp0fuzlA6ME3UiJzTXLqN6NYZF1VY7clo9BmFapFMoPVtKI8BB//+8+rtMMBUef+RhMm+frIcNY1tkNtr81ioEOQk2bu5Jup2CtL+I7a9C+EgKUSd11v3NUiiXBtRml0AOsJB3d2aIwv8iM20eErxnUJIhenfoED0bhY2QAWzqzsZU4xMQtk9gnQvdkvC31r5E7JkF3rtwGxyf7CvggeV6t41vNUKMjnt4RwgKFPjsqa11QkS71UACDWzBhp6QQYZJ3MEY0RTv76B/j6vMIYa+ke7e3Jd6SOXksVYBBYSA2eqjXpMIUq9MGu4ujNBfTDk98xRveNHsKVci/Yw==";
               // return false;//publicKey =  "MIIDijCCAnICCQD2SaOcLUvStTANBgkqhkiG9w0BAQsFADCBhjEsMCoGA1UEAwwjVEVTVF9HQ19TQ19CQU5LX1NPTFVUSU9OU19TR19IU01fMDExDTALBgNVBAsMBDk3MzMxDTALBgNVBAsMBDAwMQoxKzApBgNVBAoMIlNpbmdhcG9yZSBBdXRvbWF0ZWQgQ2xlYXJpbmcgSG91c2UxCzAJBgNVBAYTAlNHMB4XDTIxMDczMTA3MDMzN1oXDTIyMDczMTA3MDMzN1owgYYxLDAqBgNVBAMMI1RFU1RfR0NfU0NfQkFOS19TT0xVVElPTlNfU0dfSFNNXzAxMQ0wCwYDVQQLDAQ5NzMzMQ0wCwYDVQQLDAQwMDEKMSswKQYDVQQKDCJTaW5nYXBvcmUgQXV0b21hdGVkIENsZWFyaW5nIEhvdXNlMQswCQYDVQQGEwJTRzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKuHjgfRMoDSLWeHGEojgo7b3P8KUtsoBe7aW8F0ebPnRePTXcd9+gh0bPZKpeOHmzeoZqrWii7/eAZHFaTfq19511lzZqoK1gB0t7Ls4aCsRx0dEPQoCy0FvkcCMOWV87G/t29pv3+nclh3Upa2HiHoXGXwXg3Ml5gACgAUOinHKHzwCoZbQGL/EFRpA6IF7kvgZU4fpzw5bp3N5N6D9JEPc/exWahatCDQCexkmMYxcgH3gy1tq60ZJxUOPc59zeq3rYvKdizOeh/HMRgIoTk/ReOMgNB++29xGc8kbOcCeBSDebhCLD7mdw7D4NLinV2xUIh7LAm++E6EevPZpw8CAwEAATANBgkqhkiG9w0BAQsFAAOCAQEAc/fcMwp0fuzlA6ME3UiJzTXLqN6NYZF1VY7clo9BmFapFMoPVtKI8BB//+8+rtMMBUef+RhMm+frIcNY1tkNtr81ioEOQk2bu5Jup2CtL+I7a9C+EgKUSd11v3NUiiXBtRml0AOsJB3d2aIwv8iM20eErxnUJIhenfoED0bhY2QAWzqzsZU4xMQtk9gnQvdkvC31r5E7JkF3rtwGxyf7CvggeV6t41vNUKMjnt4RwgKFPjsqa11QkS71UACDWzBhp6QQYZJ3MEY0RTv76B/j6vMIYa+ke7e3Jd6SOXksVYBBYSA2eqjXpMIUq9MGu4ujNBfTDk98xRveNHsKVci/Yw==";
         //   }
            //   }
            //如果</Message>出现多次说明是admi002报文，不处理加签串
            int count = filter(xml,"</Message>");
            //<head:Sgntr>的报文把加签串放到最后，不然验签找不到加签串
            if(!xml.contains("<head:Sgntr>") && count==1) {
                xml = proccessXml(xml);
            }
            logger.info("publicKey string is  :"+publicKey);
            flag = SignUtils.isXmlDigitalSignatureValid(xml, publicKey, false);
            logger.info("Attestation results: "+flag);
        }catch (Exception e){
            logger.info("verifySignByPublicKey  error. "+e.getMessage());
          //  e.printStackTrace();
            return false;
        }
        return flag;
    }


    private static String signReplce(String sign){
        if(!StringUtils.isEmpty(sign)){
            sign = sign.replaceAll("#13;","");
            sign = sign.replace("+","%2B");
            // sign = sign.replace("<head:Sgntr>","<head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">");
            sign = sign.replace("&","");
            /*int start = sign.indexOf("<SignatureValue>");
            if(start>0){
                int end = sign.indexOf("</SignatureValue>");
                String signStr = sign.substring((start+"<SignatureValue>".length()),end);
                signStr = signStr.replace("+","%2B");
                signStr = signStr.replace("&","");
                sign = sign.replace(sign.substring((start+"<SignatureValue>".length()),end).toString(),signStr);
            }*/
        }
        return sign;

    }

    private String getX509SubjectNameValue(String xml){
        if(xml.indexOf("<X509SubjectName>")>-1){
            int start = xml.indexOf("<X509SubjectName>")+"<X509SubjectName>".length();

            int end = xml.indexOf("</X509SubjectName>")-"</X509SubjectName>".length();
            String x509SubjectName = xml.substring((start),"</X509SubjectName>".length()+end);
            return x509SubjectName;
        }
        return null;
    }
    //加签的内容放到最后去
    private String proccessXml(String xml){
        StringBuffer sign = new StringBuffer(xml);
        int start = sign.toString().indexOf("<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">");
        if(start>0){
            int end = sign.indexOf("</Signature>");
            String signStr = sign.substring((start),"</Signature>".length()+end);
            StringBuffer s = sign.delete((start),"</Signature>".length()+end);
            int MsgEnd = s.toString().indexOf("</Message>");
            StringBuffer ss = s.delete(MsgEnd,s.length());
            ss.append(signStr).append("</Message>");
            logger.info("---------"+ss);
            return ss.toString();

        }
        return xml;
    }

    /**
     * insert into xmlsign (id,className,field) values(1,'com.macrochina.net.common.iso.pacs002.Document','fiToFIPmtStsRpt.orgnlGrpInfAndSts.orgnlMsgId');
     * insert into xmlsign (id,className,field) values(2,'com.macrochina.net.common.iso.pacs002.Document','fiToFIPmtStsRpt.orgnlGrpInfAndSts.orgnlMsgId');
     * @param xmlEntity 报文实体document
     * @return 返回需要加签的已经拼接的字段值
     */
    /*public String xmlEntitySign(Object xmlEntity){
        if(xmlEntity==null){
            return null;
        }
        String packageName = xmlEntity.getClass().getName();
        List<XmlSign> rtn = this.findBySignFieldName(packageName);
        if(null== rtn || rtn.size()<1){
            logger.info("find XmlSign no data. ");
            return null;
        }
        StringBuffer buf = new StringBuffer();
        for(XmlSign sign : rtn){
            if(StringUtils.isEmpty(sign.getField())){
                logger.info("sign.getField() not is null. ");
                return null;
            }
            Object fieldValue = getReflectValue(xmlEntity, sign.getField());
            if(fieldValue!=null && !StringUtils.isEmpty(fieldValue)){
                buf.append(fieldValue);
            }else{
                logger.info("fieldValue is null. ");
                return null;
            }
        }
        return buf.toString();
    }

    *//**
     * 根据实体包名+类名查询出当前报文需要加签的字段
     * @return
     *//*
    private List<XmlSign> findBySignFieldName(String className){
        List<XmlSign> rtn = null;
        if (StringUtils.isEmpty(className)) {
            return rtn;
        }
        rtn = this.xmlSignRepository.findByClassName(className);
        if (rtn == null) {
            logger.error("查询错误!className:" + className);
        }
        return rtn;
    }

    public static Object getReflectValue(Object xmlEntity, String srcClassName) {
        int index = srcClassName.indexOf(".");
        if(index<1){
            //已经到具体的属性了
            Object obj = BeanUtils.getObjFieldValue(xmlEntity, srcClassName);
            return obj;
        }
        String srcField = srcClassName.substring(0, index);
        String endField = srcClassName.substring(index, srcClassName.length());
        Object obj = BeanUtils.getObjFieldValue(xmlEntity, srcField);
        Object res=getReflectValue(obj, endField.substring(1,endField.length()));
        return res;
    }*/



}
