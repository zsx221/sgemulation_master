package com.macrochina.net.controller;

import bcsis.HdrAndData;
import com.macrochina.net.dto.Apikey;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.service.*;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

@Api(tags = "admn报文接收处理")
@RestController
@CrossOrigin
@RequestMapping("/fast/v1/admin")
public class AdmnController {
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private XmlSignService xmlSignService;
    @Autowired
    private AdmnService admnService;
    @Autowired
    private MessagePacsService messagePacsService;

    @Autowired
    RuleService ruleService;
    private Log log = LogFactory.getLog(AdmnController.class);
    @ApiOperation(value = "Receive admn 001",notes = "")
    @RequestMapping(value="/sign-on/request",method = RequestMethod.POST )
    @ResponseBody
    public void receive001(@RequestBody String xml,HttpServletRequest request, HttpServletResponse response) throws JAXBException {
        log.info("incoming admn 001 String xml =  "+xml);
        String url = FileUtils.INWARD_RT_REQ+ MessageUtils.getXmlName();
        HdrAndData had = admnService.receive(xml,url);
        try {
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_ADMN_001, FileUtils.FPS_SCHEMA);
            if (bool) {

                // 验签
                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if (sign) {
                    String api_key = request.getHeader("api-key");
                    Apikey apikey = new Apikey();
                    apikey.setApiKey(api_key);
                    BizRuleSet bizRuleSet = ruleService.ruleOption(SysParamsContst.ADMN_001,apikey);
                    boolean flag = RetryAfter429.retryAfter(response,bizRuleSet);
                    if(!flag){ //httpcode为r429时直接return
                        return;
                    }
                    boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
                    if (sendACK) {
                        return;
                    }
                    // 保存报文以及接收到错误报文处理
                    //FileUtils.write(xml, url);
                    admnService.sendAdmn002(had,bizRuleSet);
                    HttpClientUtils.sendACKAfter(bizRuleSet);
                }else {
                    //报文签名错误
                    messagePacsService.signError(xml, had);
                    return;
                }
            } else {
                messagePacsService.syntaxError(xml, had);
                return;
            }
        }catch (Exception e){
            messagePacsService.syntaxError(xml, had);
            log.error("syntax error!", e);
        }
        response.setStatus(HttpStatus.ACCEPTED.value());
    }

    @ApiOperation(value = "Receive admn 002",notes = "")
    @RequestMapping(value="/sign-on/response",method = RequestMethod.POST)
    @ResponseBody
    public void receive002(@RequestBody String xml) throws JAXBException {
        log.info("incoming admn 002 String xml =  "+xml);
        String url = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
        Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_ADMN_002,FileUtils.FPS_SCHEMA);
        if(bool){
            // 验签
            boolean sign = xmlSignService.verifySignByPublicKey(xml,Constants.OFFORON);
            if(sign){
                FileUtils.write(xml,url);
                admnService.receive(xml,url);
            }
        }
    }


    @ApiOperation(value = "Receive admn 003",notes = "")
    @RequestMapping(value="/sign-off/request",method = RequestMethod.POST)
    @ResponseBody
    public void receive003(@RequestBody String xml,HttpServletRequest request, HttpServletResponse response) throws JAXBException {
        log.info("incoming admn 003 String xml =  "+xml);
        String url = FileUtils.INWARD_RT_REQ + MessageUtils.getXmlName();
        HdrAndData had = admnService.receive(xml,url);
        try {
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_ADMN_003, FileUtils.FPS_SCHEMA);
            if (bool) {

                // 验签
                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if (sign) {
                    String api_key = request.getHeader("api-key");
                    Apikey apikey = new Apikey();
                    apikey.setApiKey(api_key);
                    BizRuleSet bizRuleSet = ruleService.ruleOption(SysParamsContst.ADMN_003,apikey);
                    boolean flag = RetryAfter429.retryAfter(response,bizRuleSet);
                    if(!flag){ //httpcode为r429时直接return
                        return;
                    }
                    boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
                    if (sendACK) {
                        return;
                    }
                    // 保存报文以及接收到错误报文处理
                    FileUtils.write(xml, url);
                    admnService.sendAdmn004(had,bizRuleSet);
                    HttpClientUtils.sendACKAfter(bizRuleSet);
                }else {
                    //报文签名错误
                    messagePacsService.signError(xml, had);
                    return;
                }
            } else {
                //报文签名错误
                messagePacsService.syntaxError(xml, had);
                return;
            }
        }catch (Exception e){
            log.error("syntax error!", e);
        }
        response.setStatus(HttpStatus.ACCEPTED.value());
    }


    @ApiOperation(value = "Receive admn 004",notes = "")
    @RequestMapping(value="/sign-off/response",method = RequestMethod.POST)
    @ResponseBody
    public void receive004(@RequestBody String xml) throws JAXBException {
        log.info("incoming admn 004 String xml =  "+xml);
        String url =  FileUtils.INWARD_RT_REQ + MessageUtils.getXmlName();
        Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_ADMN_004,FileUtils.FPS_SCHEMA);
        if(bool){
            // 验签
            boolean sign = xmlSignService.verifySignByPublicKey(xml,Constants.OFFORON);
            if(sign){
                FileUtils.write(xml,url);
                admnService.receive(xml,url);
            }
        }
    }




    @ApiOperation(value = "Receive admn 005",notes = "")
    @RequestMapping(value="/echo/request",method = RequestMethod.POST)
    @ResponseBody
    public void receive005(@RequestBody String xml, HttpServletRequest request, HttpServletResponse response) throws JAXBException {
       // log.info("incoming admn 005 String xml =  "+xml);
        String url = FileUtils.INWARD_RT_REQ + MessageUtils.getXmlName();
        HdrAndData had = admnService.receive(xml, url);
        try {
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_ADMN_005, FileUtils.FPS_SCHEMA);
            if (bool) {

                // 验签
                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if (sign) {
                    String api_key = request.getHeader("api-key");
                    Apikey apikey = new Apikey();
                    apikey.setApiKey(api_key);
                    BizRuleSet bizRuleSet = ruleService.ruleOption(SysParamsContst.ADMN_005,apikey);
                    boolean flag = RetryAfter429.retryAfter(response,bizRuleSet);
                    if(!flag){ //httpcode为r429时直接return
                        return;
                    }
                    boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
                    if (sendACK) {
                        return;
                    }
                    // 保存报文以及接收到错误报文处理
                    FileUtils.write(xml, url);
                    admnService.sendAdmn006(had,bizRuleSet);
                    HttpClientUtils.sendACKAfter(bizRuleSet);
                }else {
                    //报文签名错误
                    messagePacsService.signError(xml, had);
                    return;
                }
            } else {
                //报文签名错误
                messagePacsService.syntaxError(xml, had);
                return;
            }
        }catch (Exception e){
            messagePacsService.syntaxError(xml, had);
            log.error("syntax error!", e);
        }
        response.setStatus(HttpStatus.ACCEPTED.value());
    }


    @ApiOperation(value = "Receive admn 006",notes = "")
    @RequestMapping(value="/echo/response",method = RequestMethod.POST)
    @ResponseBody
    public void receive006(@RequestBody String xml) throws JAXBException {

        //log.info("incoming admn 006 String xml =  "+xml);
        String url = FileUtils.OUTWARD_RT_REQ + MessageUtils.getXmlName();
        Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_ADMN_006,FileUtils.FPS_SCHEMA);
        if(bool){
            // 验签
            boolean sign = xmlSignService.verifySignByPublicKey(xml,Constants.OFFORON);
            if(sign){
                FileUtils.write(xml,url);
                admnService.receive(xml,url);
            }
        }
    }
}
