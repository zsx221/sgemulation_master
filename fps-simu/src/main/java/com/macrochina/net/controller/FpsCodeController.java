package com.macrochina.net.controller;

import com.macrochina.net.entity.FpsCode;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.FpsCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Api(tags = "状态码管理")
@RestController
@RequestMapping("/code")
public class FpsCodeController {
    
    @Autowired
    private FpsCodeService fpsCodeService;

    @ApiOperation(value = "查询所有")
    @GetMapping("/findAll")
    public Res findAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "limit", required = false, defaultValue = "1000") Integer limit,
                       FpsCode code){
        Page<FpsCode> data = fpsCodeService.findAll(page,limit,code);
        if (data != null) {
            return Res.Success(data);
        }else {
            return Res.Failure(1,"No data!");
        }
    }
    
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Res save(FpsCode code){
        fpsCodeService.save(code);
        return Res.Success();
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Res delete(int id){
        fpsCodeService.deleteById(id);
        return Res.Success();
    }

    @ApiOperation(value = "查询单个")
    @GetMapping("/detail")
    public Res detail(int id){
        FpsCode code = fpsCodeService.findOneById(id);
        return Res.Success(code);
    }
}