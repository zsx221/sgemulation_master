package com.macrochina.net.controller;

import bcsis.HdrAndData;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.Message;
import com.macrochina.net.entity.MessagePacs;
import com.macrochina.net.service.*;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "pacs002接收处理")
@RestController
@CrossOrigin
@RequestMapping("/fast/v1/payment/credit-transfer-status/")
@Slf4j
public class Pacs002Controller {

    @Autowired
    Pacs002Service pacs002Service;
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private XmlSignService xmlSignService;

    @Autowired
    private MessagePacsService messagePacsService;

    @Autowired
    private Camt056Service camt056Service;

    @Autowired
    RuleService ruleService;

    @ApiOperation(value = "Receive credit payment status",notes = "")
    @RequestMapping(value="/send",method = RequestMethod.POST)
    @ResponseBody
    public void send(@RequestBody String xml, HttpServletResponse response){
        response.setStatus(HttpStatus.ACCEPTED.value());
        log.info("incoming pacs002 :"+xml);
        String fileUrl = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
        Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_PACS_002,FileUtils.FPS_SCHEMA);
        HdrAndData fpsMsg = ConvertUtils.unmarshal(xml);
        messagePacsService.saveMessage(fpsMsg,fileUrl, SysParamsContst.INWARD);
        if(bool){
            // 验签
            boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
            if(sign){
                BizRuleSet bizRuleSet = ruleService.ruleOption("pacs.002.001.03",fpsMsg.getPaymentStatus());
                if(bizRuleSet!=null) {
                    boolean flag = RetryAfter429.retryAfter(response,bizRuleSet);
                    if(!flag){ //httpcode为r429时直接return
                        return;
                    }
                    boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
                    if (sendACK) {
                        return;
                    }
                    boolean sendAdmi002 = "1".equals(bizRuleSet.getSendAdmi002()); //是否发送admi002
                    if(sendAdmi002){
                        messagePacsService.signError(xml, fpsMsg);
                        HttpClientUtils.sendACKAfter(bizRuleSet);
                        return;
                    }
                    boolean sendCamt056 = "1".equals(bizRuleSet.getSendCamt056()); //是否发送admi002
                    if(sendCamt056){
                        camt056Service.sendCamt056(fpsMsg);
                        HttpClientUtils.sendACKAfter(bizRuleSet);
                        return;
                    }
                }
                // 保存报文以及接收到错误报文处理
                FileUtils.write(xml,fileUrl);
            }else{
                //报文格式错误
                messagePacsService.signError(xml,fpsMsg);
                return;
            }
        }else{
            //报文格式错误
            messagePacsService.syntaxError(xml,fpsMsg);
            return;
        }
    }
}
