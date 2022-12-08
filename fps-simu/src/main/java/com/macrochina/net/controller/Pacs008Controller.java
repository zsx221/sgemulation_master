package com.macrochina.net.controller;

import bcsis.HdrAndData;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "往帐Pacs008")
@RestController
@RequestMapping("/fast/v1/payment")
public class Pacs008Controller {

    private Log log = LogFactory.getLog(Pacs008Controller.class);

    @Autowired
    private MsgAckPacs002Service messageAckService;

    @Autowired
    private XmlSignService xmlSignService;

    @Autowired
    private SchemaService schemaService;

    @Autowired
    private RuleService ruleService;

    @Autowired
    private MessagePacsService messagePacsService;

    @ApiOperation(value = "pacs008", notes = "")
    @PostMapping(value = "/credit-transfer/send")
    public void receiveMessage(@RequestBody String xml, HttpServletResponse response) {
        log.info("incoming pacs008 String xml =  "+xml);
        String url = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName();
        FileUtils.write(xml, url);
        HdrAndData fpsMsg = null;
        try {
            fpsMsg = ConvertUtils.unmarshal(xml);//把xml格式解析成HdrAndData类
            messagePacsService.saveMessage(fpsMsg,url, SysParamsContst.INWARD);//把xml信息保存到数据库
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_PACS_008, FileUtils.FPS_SCHEMA);//验证一下发过来的xml格式是否正确
            if(bool){
                BizRuleSet bizRuleSet = ruleService.ruleOption(SysParamsContst.PACS_008,fpsMsg.getCreditTransfer());
                boolean flag = RetryAfter429.retryAfter(response,bizRuleSet);
                if(!flag){ //httpcode为r429时直接return
                    return;
                }

                if(bizRuleSet!=null) {
                    boolean sendAdmi002 = "1".equals(bizRuleSet.getSendAdmi002()); //是否发送admi002
                    if(sendAdmi002){
                        response.setStatus(HttpStatus.ACCEPTED.value());
                        messagePacsService.signError(xml, fpsMsg);
                        return;
                    }
                }
                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if(sign){
                    boolean sendACK = HttpClientUtils.sendACKBefore(response, bizRuleSet);
                    if (sendACK) {
                        return;
                    }
                    messageAckService.ack(fpsMsg, url, bizRuleSet);
                    HttpClientUtils.sendACKAfter(bizRuleSet);
                }else {

                    response.setStatus(HttpStatus.ACCEPTED.value());
                    //报文签名错误
                    messagePacsService.signError(xml,fpsMsg);
                    return;
                }

            }else {

                response.setStatus(HttpStatus.ACCEPTED.value());
                //报文格式错误
                messagePacsService.syntaxError(xml,fpsMsg);
                return;
            }
        }catch (Exception e){
           // messagePacsService.syntaxError(xml,fpsMsg);
            log.error("syntax error!", e);
        }
    }
}
