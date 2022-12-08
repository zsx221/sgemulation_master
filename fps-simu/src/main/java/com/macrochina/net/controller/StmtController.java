package com.macrochina.net.controller;

import bcsis.HdrAndData;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.service.*;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import iso.head_001_001.G3MessageTypes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(tags = "stmt")
@RestController
@RequestMapping("/fast/v1")
public class StmtController {

    @Autowired
    private Stmt002Service stmt002Service;

    @Autowired
    private MessageService messageService;

    @Autowired
    private XmlSignService xmlSignService;

    @Autowired
    private SchemaService schemaService;

    @Autowired
    private MessagePacsService messagePacsService;

    @Autowired
    RuleService ruleService;

    @Autowired
    Admi002Service admi002Service;

    @ApiOperation(value = "stmt001")
    @PostMapping(value="/report/ad-hoc-statement/request")
    public void request(@RequestBody String xml, HttpServletResponse response){
        log.info("incoming receive stmt001 String xml {}",xml);
        String url = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName();
        response.setStatus(HttpStatus.ACCEPTED.value());
        FileUtils.write(xml, url);
        HdrAndData fpsMsg = null;
        try {
            fpsMsg = ConvertUtils.unmarshal(xml);
            messagePacsService.saveMessage(fpsMsg,url,SysParamsContst.INWARD);
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_STMT_001, FileUtils.FPS_SCHEMA);
            if(bool){
                BizRuleSet bizRuleSet = ruleService.ruleOption(G3MessageTypes.STMT_001_001_01.value(), fpsMsg.getBankStatementRequest());
                boolean flag = RetryAfter429.retryAfter(response,bizRuleSet);
                if(!flag){ //httpcode为r429时直接return
                    return;
                }
                if(bizRuleSet != null){
                    Integer isTimeOut = bizRuleSet.getIsTimeOut();
                    if(isTimeOut != null && bizRuleSet.getIsTimeOut() == 1){
                        try {
                            Thread.sleep((bizRuleSet.getTimeOut() == null ? 0 : bizRuleSet.getTimeOut())* 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String httpResp = bizRuleSet.getHttpResp();
                    String status = bizRuleSet.getResp_status();
                    if(status.equals("HTTP Code")) {
                        if (StringUtils.isBlank(httpResp) || httpResp.equals("202")) {
                            response.setStatus(HttpStatus.ACCEPTED.value());
                        }else {
                            response.setStatus(Integer.parseInt(httpResp));
                            return;
                        }
                    }
                }
                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if (sign) {
                    stmt002Service.ack(fpsMsg, url, bizRuleSet);
                }else {
                    messagePacsService.signError(xml,fpsMsg);
                    return;
            }
        }else {
            messagePacsService.syntaxError(xml, fpsMsg);
            return;
        }
        }catch (Exception e){
           // messagePacsService.syntaxError(xml, fpsMsg);
            log.error("syntax error!", e);
        }
    }
}