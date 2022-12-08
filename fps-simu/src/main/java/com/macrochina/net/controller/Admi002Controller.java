package com.macrochina.net.controller;

import bcsis.HdrAndData;
import com.macrochina.net.service.MessagePacsService;
import com.macrochina.net.service.RuleService;
import com.macrochina.net.service.SchemaService;
import com.macrochina.net.service.XmlSignService;
import com.macrochina.net.util.Constants;
import com.macrochina.net.util.ConvertUtils;
import com.macrochina.net.util.FileUtils;
import com.macrochina.net.util.MessageUtils;
import com.macrochina.net.utils.SysParamsContst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(tags = "Admi002")
@RestController
@RequestMapping("/fast/v1")
public class Admi002Controller {

    @Autowired
    private XmlSignService xmlSignService;
    @Autowired
    private SchemaService schemaService;
    @Autowired
    private MessagePacsService messagePacsService;
    @Autowired
    RuleService ruleService;

    @ApiOperation(value = "admi002")
    @PostMapping(value = "/admin/advice/reject")
    public void receiveMessage(@RequestBody String xml, HttpServletResponse response) {
        log.info("receive String xml =  "+xml);
        String url = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName() ;
        FileUtils.write(xml, url);
        response.setStatus(HttpStatus.ACCEPTED.value());
        HdrAndData fpsMsg = ConvertUtils.unmarshal(xml);
        messagePacsService.saveMessage(fpsMsg,url, SysParamsContst.INWARD);
        try {
            Boolean bool = schemaService.validateXmlByXsd2(xml, SysParamsContst.SCHEMA_ADMI_002, FileUtils.FPS_SCHEMA);
            if(bool){
                boolean sign = xmlSignService.verifySignByPublicKey(xml, Constants.OFFORON);
                if (sign) {
                    //todo save 数据库？
                }
            }else {
                return;
            }
        }catch (Exception e){
            log.error("sign error!", e);
        }
    }
}
