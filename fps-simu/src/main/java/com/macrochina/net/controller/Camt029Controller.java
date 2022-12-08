package com.macrochina.net.controller;


import bcsis.HdrAndData;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.service.MessagePacsService;
import com.macrochina.net.service.RuleService;
import com.macrochina.net.service.SchemaService;
import com.macrochina.net.service.XmlSignService;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import iso.head_001_001.G3MessageTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(tags = "camt029")
@RestController
@RequestMapping("/fast/v1")
public class Camt029Controller {

    @Autowired
    private XmlSignService xmlSignService;
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private MessagePacsService messagePacsService;
    @Autowired
    RuleService ruleService;

    @ApiOperation(value = "camt029")
    @PostMapping(value = "/payment/credit-cancellation-status/send")
    public void receiveMessage(@RequestBody String xml, HttpServletResponse response) {
        log.info("receive String xml =  "+xml);
        String url = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName() ;
        FileUtils.write(xml, url);
        response.setStatus(HttpStatus.ACCEPTED.value());
        HdrAndData fpsMsg = ConvertUtils.unmarshal(xml);
        messagePacsService.saveMessage(fpsMsg,url, SysParamsContst.INWARD);
        BizRuleSet bizRuleSet = ruleService.ruleOption("camt.029.001.03",fpsMsg.getResolutionOfInvestigation());
        if(bizRuleSet!=null) {
            Integer isTimeOut = bizRuleSet.getIsTimeOut();
            if (isTimeOut != null && bizRuleSet.getIsTimeOut() == 1) {
                try {
                    Thread.sleep((bizRuleSet.getTimeOut() == null ? 0 : bizRuleSet.getTimeOut()) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        boolean flag = RetryAfter429.retryAfter(response,bizRuleSet);
        if(!flag){ //httpcode为r429时直接return
            return;
        }
        try {
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_CAMT_029, FileUtils.FPS_SCHEMA);
            if(bool){
                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if (!sign) {
                    //报文签名错误
                    messagePacsService.signError(xml,fpsMsg);
                    return;
                }
            }else {
                //报文格式错误
                messagePacsService.syntaxError(xml,fpsMsg);
                return;
            }
        }catch (Exception e){
            log.error("sign error!", e);
        }
    }
}
