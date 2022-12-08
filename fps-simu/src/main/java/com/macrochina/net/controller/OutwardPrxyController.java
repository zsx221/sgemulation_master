package com.macrochina.net.controller;

import adrs.HdrAndData;
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

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

@Api(tags = "往帐Prxy")
@RestController
@RequestMapping("/paynow/v1")
public class OutwardPrxyController {

    @Autowired
    RuleService ruleService;
    @Autowired
    Admi002Service admi002Service;
    private Log log = LogFactory.getLog(OutwardPrxyController.class);
    @Autowired
    private Prxy002Service prxy002Service;
    @Autowired
    private Prxy004Service prxy004Service;
    @Autowired
    private Prxy008Service prxy008Service;
    @Autowired
    private MessageService messageService;
    @Autowired
    private XmlSignService xmlSignService;
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private MessagePacsService messagePacsService;


    @Autowired
    private PaynowService paynowService;
    @ApiOperation(value = "prxy001")
    @PostMapping(value = "/registration/request")
    public void request(@RequestBody String xml, HttpServletResponse response) {
        log.info("incoming receive prxy001 String xml =  " + xml);
        String url = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName();
        FileUtils.write(xml, url);//保存文件
        HdrAndData fpsMsg = null;
        try {
            fpsMsg = ConvertAdrsUtils.unmarshal(xml);
            // 保存 messagePrxy001
            messagePacsService.saveMessagePrxy(fpsMsg, url, SysParamsContst.INWARD);
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_PRXY, FileUtils.ADRS_SCHEMA);
            if (bool) {

                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if(sign){
                    BizRuleSet bizRuleSet = ruleService.ruleOption(SysParamsContst.PRXY_001, fpsMsg.getProxyRegistration());
                    boolean flag = RetryAfter429.retryAfter(response, bizRuleSet);
                    if (!flag) { //httpcode为429时直接return
                        return;
                    }
                    boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
                    if (sendACK) {
                        return;
                    }
                    if(bizRuleSet!=null) {
                        boolean sendAdmi002 = "1".equals(bizRuleSet.getSendAdmi002()); //是否发送admi002
                        if(sendAdmi002){
                            messageService.signErrors(xml, fpsMsg);
                            HttpClientUtils.sendACKAfter(bizRuleSet);
                            return;
                        }
                    }
                    prxy002Service.ack(fpsMsg, url, bizRuleSet);
                    HttpClientUtils.sendACKAfter(bizRuleSet);
                }else {
                    //报文签名错误
                    messageService.signErrors(xml, fpsMsg);
                    return;
                }

            } else {

                response.setStatus(HttpStatus.ACCEPTED.value());
                //报文格式错误
                messageService.syntaxErrors(xml, fpsMsg);
                return;
            }
        } catch (Exception e) {

            response.setStatus(HttpStatus.ACCEPTED.value());
           // messageService.syntaxErrors(xml, fpsMsg);
            log.error("syntax error!", e);
        }
    }


    @ApiOperation(value = "prxy003")
    @PostMapping(value = "/lookup/request")
    public void prxy003(@RequestBody String xml, HttpServletResponse response) {
        log.info("incoming receive prxy003 String xml =  " + xml);
        String url = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName();
        FileUtils.write(xml, url);//保存文件
        HdrAndData fpsMsg = null;
        try {
            fpsMsg = ConvertAdrsUtils.unmarshal(xml);
            // 保存 messagePrxy003
            messagePacsService.saveMessagePrxy(fpsMsg, url, SysParamsContst.INWARD);
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_PRXY, FileUtils.ADRS_SCHEMA);
            if (bool) {

                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);

                if(sign){
                    BizRuleSet bizRuleSet = ruleService.ruleOption(SysParamsContst.PRXY_003, fpsMsg.getProxyLookUp());
                    boolean flag = RetryAfter429.retryAfter(response, bizRuleSet);
                    if (!flag) { //httpcode为r429时直接return
                        return;
                    }
                    boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
                    if (sendACK) {
                        return;
                    }
                    if(bizRuleSet!=null) {
                        boolean sendAdmi002 = "1".equals(bizRuleSet.getSendAdmi002()); //是否发送admi002
                        if(sendAdmi002){
                            messageService.signErrors(xml, fpsMsg);
                            HttpClientUtils.sendACKAfter(bizRuleSet);
                            return;
                        }
                    }
//                    FileUtils.write(xml, url);
                    prxy004Service.ack004(fpsMsg, url, bizRuleSet);
                    HttpClientUtils.sendACKAfter(bizRuleSet);
                } else {
                    //报文签名错误
                    messageService.signErrors(xml, fpsMsg);
                    return;
                }

            } else {

                response.setStatus(HttpStatus.ACCEPTED.value());
                //报文格式错误
                messageService.syntaxErrors(xml, fpsMsg);
                return;
            }
        } catch (Exception e) {

            response.setStatus(HttpStatus.ACCEPTED.value());
           // messageService.syntaxErrors(xml, fpsMsg);
            log.error("sign error!", e);
        }
    }

    @ApiOperation(value = "Receive prxy.005.001.01", notes = "")
    @RequestMapping(value = "/enquiry/request", method = RequestMethod.POST)
    @ResponseBody
    public void prxy005Request(@RequestBody String xml, HttpServletResponse response) throws JAXBException {

        response.setStatus(HttpStatus.ACCEPTED.value());
        log.info("receive prxy  005 String xml =  "+xml);
        HdrAndData fpsMsg = ConvertAdrsUtils.unmarshal(xml);
        String url = FileUtils.INWARD_RT_REQ+ MessageUtils.getXmlName();
        FileUtils.write(xml, url);
        try {
            messagePacsService.saveMessagePrxy(fpsMsg, url, SysParamsContst.INWARD);
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_PRXY, FileUtils.ADRS_SCHEMA);
            if (bool) {
                // 验签
                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if(sign){
                    BizRuleSet bizRuleSet = ruleService.ruleOption(SysParamsContst.PRXY_005, fpsMsg.getProxyEnquiryRequest());
                    boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
                    if (sendACK) {
                        return;
                    }
                    if(bizRuleSet!=null) {
                        boolean sendAdmi002 = "1".equals(bizRuleSet.getSendAdmi002()); //是否发送admi002
                        if(sendAdmi002){
                            messageService.signErrors(xml, fpsMsg);
                            HttpClientUtils.sendACKAfter(bizRuleSet);
                            return;
                        }
                    }
                    // 保存报文以及接收到错误报文处理
                    boolean flag = RetryAfter429.retryAfter(response, bizRuleSet);
                    if (!flag) { //httpcode为r429时直接return
                        return;
                    }
                    paynowService.sendPrxy006(fpsMsg,bizRuleSet);
                    HttpClientUtils.sendACKAfter(bizRuleSet);

                }else {

                    response.setStatus(HttpStatus.ACCEPTED.value());
                    //报文签名错误
                    messageService.signErrors(xml,fpsMsg);
                    return;
                }
            } else {

                response.setStatus(HttpStatus.ACCEPTED.value());
                //报文签名错误
                messageService.syntaxErrors(xml, fpsMsg);
                return;
            }
        }catch (Exception e){
            log.error("syntax error!", e);
        }
    }

    @ApiOperation(value = "prxy007")
    @PostMapping(value = "/report/request")
    public void prxy007(@RequestBody String xml, HttpServletResponse response) {
        log.info("incoming receive prxy007 String xml =  " + xml);
        String url = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName();
        FileUtils.write(xml, url);
        HdrAndData fpsMsg = null;
        try {
            fpsMsg = ConvertAdrsUtils.unmarshal(xml);
            // 保存 messagePrxy007
            messagePacsService.saveMessagePrxy(fpsMsg, url, SysParamsContst.INWARD);
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_PRXY, FileUtils.ADRS_SCHEMA);
            if (bool) {


                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if(sign ){
                    BizRuleSet bizRuleSet = ruleService.ruleOption(SysParamsContst.PRXY_007, fpsMsg.getParticipantReportRequest());
                    boolean flag = RetryAfter429.retryAfter(response, bizRuleSet);
                    if (!flag) { //httpcode为r429时直接return
                        return;
                    }
                    boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
                    if (sendACK) {
                        return;
                    }
                    if(bizRuleSet!=null) {
                        boolean sendAdmi002 = "1".equals(bizRuleSet.getSendAdmi002()); //是否发送admi002
                        if(sendAdmi002){
                            response.setStatus(HttpStatus.ACCEPTED.value());
                            messageService.signErrors(xml, fpsMsg);
                            return;
                        }
                    }
                    prxy008Service.ack008(fpsMsg, url, bizRuleSet,response);
                    HttpClientUtils.sendACKAfter(bizRuleSet);
                }else {
                    //报文签名错误

                    response.setStatus(HttpStatus.ACCEPTED.value());
                    messageService.signErrors(xml,fpsMsg);
                    return;
                }
            } else {
                //报文格式错误
                messageService.syntaxErrors(xml, fpsMsg);
                return;
            }
        } catch (Exception e) {
           // messageService.syntaxErrors(xml, fpsMsg);
            log.error("sign error!", e);
        }
    }
    @ApiOperation(value = "admi002")
    @PostMapping(value = "/admin/advice/reject")
    public void receiveMessage(@RequestBody String xml, HttpServletResponse response) {
        log.info("receive String xml =  " + xml);
        String url = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName();
        FileUtils.write(xml, url);
        response.setStatus(HttpStatus.ACCEPTED.value());
        HdrAndData fpsMsg = ConvertAdrsUtils.unmarshal(xml);
        messagePacsService.saveMessagePrxy(fpsMsg, url, SysParamsContst.INWARD);
    }
}