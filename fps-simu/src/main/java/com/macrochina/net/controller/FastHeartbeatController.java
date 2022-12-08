package com.macrochina.net.controller;

import com.macrochina.net.dto.Apikey;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.service.RuleService;
import com.macrochina.net.util.HttpClientUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "fast心跳报文接收处理")
@RestController
@CrossOrigin
@RequestMapping("/fast/v1")
public class FastHeartbeatController {
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


}
