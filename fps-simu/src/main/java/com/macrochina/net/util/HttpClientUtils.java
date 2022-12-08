package com.macrochina.net.util;

import com.macrochina.net.entity.BizRuleSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用HttpClient進行post請求的工具類
 * @ClassName: HttpClientUtil
 * @Description: TODO
 * @author Devin <xxx>
 * @date 2017年2月7日 下午1:43:38
 *
 */
@Slf4j
public class HttpClientUtils {

    private static final String FAST = "/fast/";
    private static final String PAYNOW = "/fast/";
    public static String doPost(String url,String xml){
        log.info(url);
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            Map<String,String> map=new HashMap<>();
            int i = url.indexOf(FAST);
            if(i > 0) {
                map.put("Api-key","BANKBCS1234");
            }else{
                map.put("Api-key","BANKCAS5678");
            }
            map.put("Accept","application/xml");
            map.put("Content-type","application/xml");
            /*   map.put("Date",XmlIsoDateUtil.convertToXMLGregorianCalendarMillsec(Calendar.getInstance()).toString());
             */
            map.put("X-bcs-origin","ke111");
            map.put("x-bcs-instruction-id", MessageUtils.genMsgId());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
            StringEntity entity=new StringEntity(xml, Consts.UTF_8);
            //设置请求参数
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            result =String.valueOf( response.getStatusLine().getStatusCode());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static String doGet(String url){

        log.info(url);
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpGet = new HttpGet(url);
            Map<String,String> map=new HashMap<>();
            int i = url.indexOf(FAST);
            if(i > 0) {
                map.put("Api-key","BANKBCS1234");
            }else{
                map.put("Api-key","BANKCAS5678");
            }
            map.put("Accept","application/xml");
            map.put("Content-type","application/xml");

            map.put("X-bcs-origin","ke111");
            map.put("x-bcs-instruction-id", MessageUtils.genMsgId());
            /*
            map.put("Accept","application/xml");
            map.put("Content-type","application/xml");*/
         /*   map.put("Date",XmlIsoDateUtil.convertToXMLGregorianCalendarMillsec(Calendar.getInstance()).toString());
            map.put("X-bcs-origin","ke111");
            map.put("x-bcs-instruction-id","Instruction ID of the transaction");*/
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
            HttpResponse response = httpClient.execute(httpGet);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }



    /**
     * @Author fangyu
     * @Description url发送路径   jsonObject 需要发送的json  map 头部需要设置
     * @Date 2021/9/2 20:11
     * @Param [url, jsonObject, map]
     * @return java.lang.String
     **/
    public static HttpResponse doPostHeader(String url, String json,Map<String,String> map){

        log.info(url);
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        try{
            httpClient = new SSLClient();

            httpPost = new HttpPost(url);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
            StringEntity entity=new StringEntity(json, Consts.UTF_8);
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            //设置请求参数
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            return response;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void sendCallback(String url, String xml, BizRuleSet bizRuleSet){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (bizRuleSet != null) {
            Integer isTimeOut = bizRuleSet.getCallbackIsTimeOut();
            //判断是否有超时
            if (isTimeOut != null && bizRuleSet.getCallbackIsTimeOut() == 1) {
                try {
                    Thread.sleep((bizRuleSet.getCallbackTimeOut() == null ? 0 : bizRuleSet.getCallbackTimeOut()) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        doPost(url, xml);
    }

    /**
     * @Author fangyu
     * @Description //TODO 之前
     * @Date 2022/3/24 15:57
     * @Param [response, bizRuleSet]
     * @return boolean
     **/
    public static boolean sendACKBefore(HttpServletResponse response, BizRuleSet bizRuleSet) {
        if (bizRuleSet != null) {
            String httpResp = bizRuleSet.getHttpResp();
            String status = bizRuleSet.getResp_status();
            if (status.equals("HTTP Code")) {
                if (StringUtils.isBlank(httpResp) || httpResp.equals("202")) {
                    response.setStatus(HttpStatus.ACCEPTED.value());
                } else {
                    response.setStatus(Integer.parseInt(httpResp));
                    return true;
                }
            }
        }
        response.setStatus(HttpStatus.ACCEPTED.value());
        return false;
    }
    /**
     * @Author fangyu
     * @Description //TODO 之前
     * @Date 2022/3/24 15:57
     * @Param [response, bizRuleSet]
     * @return boolean
     **/
    public static void sendACKAfter( BizRuleSet bizRuleSet) {
        if (bizRuleSet != null) {
            Integer isTimeOut = bizRuleSet.getIsTimeOut();
            //判断是否ack超时
            if (isTimeOut != null && bizRuleSet.getIsTimeOut() == 1) {
                try {
                    Thread.sleep((bizRuleSet.getTimeOut() == null ? 0 : bizRuleSet.getTimeOut()) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}