package com.macrochina.net.controller;

import com.macrochina.net.entity.API00206a;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.API00206aService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Api(tags = "API00206a")
@RestController
@RequestMapping("/api00206a")
public class API00206aController {

    @Autowired
    private API00206aService api00206aService;

    @ApiOperation(value = "查询所有")
    @GetMapping("/findAll")
    public Res findAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                       API00206a api00206a){
        Page<API00206a> data = api00206aService.findAll(page,limit,api00206a);
        if (data != null) {
            return Res.Success(data);
        }else {
            return Res.Failure(1,"No data!");
        }
    }

    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Res save(API00206a api00206a){
        api00206aService.save(api00206a);
        return Res.Success();
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Res delete(int id){
        api00206aService.deleteById(id);
        return Res.Success();
    }

    @ApiOperation(value = "查询单个")
    @GetMapping("/detail")
    public Res detail(int id){
        API00206a api00206a = api00206aService.findOneById(id);
        return Res.Success(api00206a);
    }

    @ApiOperation(value = "发送API0006a")
    @GetMapping("/send")
    public Res send(API00206a api00206a){
        api00206aService.sendAPI00206a(api00206a);
        return Res.Success();
    }
}