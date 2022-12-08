package com.macrochina.net.controller;

import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.FpsParamService;
import com.macrochina.net.service.SysParamsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "报文参数管理")
@RestController
@RequestMapping("/param")
public class FpsParamController {

    @Autowired
    private FpsParamService fpsParamService;

    @ApiOperation(value = "查找")
    @GetMapping("/findById")
    public Res findById(){
        List<FpsParam> list = fpsParamService.findAll();
        if (!CollectionUtils.isEmpty(list)) {
            return Res.Success(list.get(0));
        }else {
            return Res.Success();
        }
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/update")
    public Res update(FpsParam fpsParam){
        fpsParamService.save(fpsParam);
        return Res.Success("success");
    }
}
