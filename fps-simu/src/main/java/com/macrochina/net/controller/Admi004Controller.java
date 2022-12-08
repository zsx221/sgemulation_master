package com.macrochina.net.controller;

import com.macrochina.net.dto.Admi004Dto;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.Admi004Service;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "admi004")
@RestController
@RequestMapping("/Admi004")
public class Admi004Controller {

    @Autowired
    private Admi004Service admi004Service;
    @PostMapping(value = "/sendAdmi004")
    public Res sendAdmi004(Admi004Dto admi004Dto){
        admi004Service.receiveNotification(admi004Dto);
        return Res.Success();
    }

}
