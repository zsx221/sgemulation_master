package com.macrochina.net.controller;

import adrs.HdrAndData;
import com.macrochina.net.dto.Apikey;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.service.*;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "心跳报文接收处理")
@RestController
@CrossOrigin
@RequestMapping("/paynow/v1")
public class PaynowHeartbeatController {
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private XmlSignService xmlSignService;
    @Autowired
    private PaynowService paynowService;
    @Autowired
    private AdmnService admnService;
    @Autowired
    private MessagePacsService messagePacsService;
    @Autowired
    private MessageService messageService;
    private Log log = LogFactory.getLog(PaynowHeartbeatController.class);
    @Autowired
    RuleService ruleService;
    @ApiOperation(value = "health heartbeat",notes = "")
    @RequestMapping(value="/health/heartbeat",method = RequestMethod.GET)
    @ResponseBody
    public void heartbeat(HttpServletRequest request, HttpServletResponse response) throws JAXBException {

        String api_key = request.getHeader("api-key");
        Apikey apikey = new Apikey();
        apikey.setApiKey(api_key);
        BizRuleSet bizRuleSet = ruleService.ruleOption("Health Check", apikey);
        boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
        if (sendACK) {
            return;
        }
        if(bizRuleSet!=null && StringUtils.isNotEmpty(bizRuleSet.getHttpResp())){
            response.setStatus(Integer.valueOf(bizRuleSet.getHttpResp()));
        }else {
            response.setStatus(204);
        }
    }


    @ApiOperation(value = "Receive admn 005",notes = "")
    @RequestMapping(value="/admin/echo/request",method = RequestMethod.POST)
    @ResponseBody
    public void receive005(@RequestBody String xml,HttpServletRequest request, HttpServletResponse response) throws JAXBException {
        //log.info("receive paynow admn 005 String xml =  "+xml);
        String url = FileUtils.INWARD_RT_REQ+ MessageUtils.getXmlName();
        adrs.HdrAndData hda = ConvertAdrsUtils.unmarshal(xml);
        try {
            //messagePacsService.saveMessagePrxy(hda, url, "1");
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_PRXY, FileUtils.ADRS_SCHEMA);
            if (bool) {
                // 验签
                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if (sign) {
                    String api_key = request.getHeader("api-key");
                    Apikey apikey = new Apikey();
                    apikey.setApiKey(api_key);
                    BizRuleSet bizRuleSet = ruleService.ruleOption(SysParamsContst.ADMN_005, apikey);
                    boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
                    if (sendACK) {
                        return;
                    }
                    // 保存报文以及接收到错误报文处理
                    FileUtils.write(xml, url);
                    paynowService.sendPayNowAdmn006(hda,bizRuleSet);
                    HttpClientUtils.sendACKAfter(bizRuleSet);

                }else{
                    messageService.signErrors(xml, hda);
                    return;
                }
            } else {
                //报文签名错误
                messageService.syntaxErrors(xml, hda);
                return;
            }
        }catch (Exception e){
            messageService.syntaxErrors(xml, hda);
            log.error("syntax error!", e);
        }
        response.setStatus(HttpStatus.ACCEPTED.value());
    }
}
