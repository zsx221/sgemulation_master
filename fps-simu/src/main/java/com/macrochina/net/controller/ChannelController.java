package com.macrochina.net.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "往帐Pacs008")
@RestController
@RequestMapping("/channel/G3/FAST")
public class ChannelController {
//    @ApiOperation(value = "pacs008", notes = "")
//    @PostMapping(value = "/PaymentInitiation")
//    @ResponseBody
//    public Res paymentInitiation(HttpServletResponse response){
//        Res res = new Res();
//        res.setReturnCode("00"); //正常
//        res.setReturnCode("10"); //消息验证失败。
//        res.setReturnCode("99"); //系统错误
//
//        response.setStatus(HttpStatus.OK.value());
//        return res;
//    }
}

