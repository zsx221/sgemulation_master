package com.macrochina.net.service;

import com.alibaba.fastjson.JSONObject;
import com.macrochina.net.controller.Pacs008Controller;
import com.macrochina.net.dto.PostingRequestsDto;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.Message;
import com.macrochina.net.util.HttpClientUtils;
import com.macrochina.net.utils.SysParamsContst;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OPFGatewayService {

    @Autowired
    MessageService messageService;
    private Log log = LogFactory.getLog(Pacs008Controller.class);

    @Async("asyncTaskExecutor")
    public void delaySendCallback(BizRuleSet bizRuleSet, JSONObject jsonObject, Map<String, String> map, String url, PostingRequestsDto postingRequestsDto) {
        if (bizRuleSet != null) {
            Integer isTimeOut = bizRuleSet.getCallbackIsTimeOut();
            //判断是否有超时
            if (isTimeOut != null && bizRuleSet.getCallbackIsTimeOut() == 1) {
                try {
                    Thread.sleep((bizRuleSet.getCallbackTimeOut() == null ? 0 : bizRuleSet.getCallbackTimeOut()) * 1000);
//                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            //为了保证先回ack
            Thread.sleep(50);
            intend(bizRuleSet, jsonObject, map, url, postingRequestsDto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/*    @Async("asyncTaskExecutor")
    public void callback(BizRuleSet bizRuleSet, JSONObject jsonObject, Map<String, String> map, String url, PostingRequestsDto postingRequestsDto) {
        intend(bizRuleSet, jsonObject, map, url, postingRequestsDto);
    }*/

    private void intend(BizRuleSet bizRuleSet, JSONObject jsonObject, Map<String, String> map, String url, PostingRequestsDto postingRequestsDto) {

        log.info("postcallback opfTransactionId :  " + postingRequestsDto.getOpfTransactionId() + " outging url : " + url + " msg :" + jsonObject.toJSONString());

        //如果设置了发送次数和间隔,则不走下面流程
        if (bizRuleSet != null && bizRuleSet.getSendCallbackNum() != null && bizRuleSet.getSendCallbackNum() > 0) {
            sendCallback(bizRuleSet.getSendCallbackNum(), bizRuleSet.getSendIntervalTime(), jsonObject, map, url, postingRequestsDto);
            return;
        }
        HttpResponse reslut = null;
        Message message = new Message();
        int httpCode = -1;

        int i = 1;
        do {
            reslut = HttpClientUtils.doPostHeader(url, jsonObject.toJSONString(), map);
            message.setTag("OpfGateway");
            message.setMsgId(postingRequestsDto.getOpfTransactionId());
            message.setMsgDef("postingcallbacks");
            message.setDirct(Integer.parseInt(SysParamsContst.OUTWARD));
            message.setRemark(jsonObject.toJSONString());
            messageService.save(message);
            if (reslut != null) {
                httpCode = reslut.getStatusLine().getStatusCode();
            }
            log.info("postcallback opfTransactionId :  " + postingRequestsDto.getOpfTransactionId() + "   httpCode" + httpCode);
            i++;
        } while (httpCode != 200 && httpCode != 409 && i < 3);  //重发机制, response状态码为500时,三次
    }


    public void sendCallback(Integer sendCallbackNum, Integer sendIntervalTime, JSONObject jsonObject, Map<String, String> map, String url, PostingRequestsDto postingRequestsDto) {
        log.info("postcallback opfTransactionId :  " + postingRequestsDto.getOpfTransactionId() + " outging url : " + url + " msg :" + jsonObject.toJSONString());
        Message message = new Message();
        for (int i = 0; i < sendCallbackNum; i++) {
            HttpClientUtils.doPostHeader(url, jsonObject.toJSONString(), map);
            message.setTag("OpfGateway");
            message.setMsgId(postingRequestsDto.getOpfTransactionId());
            message.setMsgDef("postingcallbacks");
            message.setDirct(Integer.parseInt(SysParamsContst.OUTWARD));
            message.setRemark(jsonObject.toJSONString());
            messageService.save(message);
            try {
                Thread.sleep(sendIntervalTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
