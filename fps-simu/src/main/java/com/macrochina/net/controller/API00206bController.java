package com.macrochina.net.controller;

import com.macrochina.net.entity.API00206b;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.API00206bService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Api(tags = "API00206b")
@RestController
@RequestMapping("/api00206b")
public class API00206bController {
    
    @Autowired
    private API00206bService api00206bService;

    @ApiOperation(value = "查询所有")
    @GetMapping("/findAll")
    public Res findAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                       API00206b api00206b){
        Page<API00206b> data = api00206bService.findAll(page,limit,api00206b);
        if (data != null) {
            return Res.Success(data);
        }else {
            return Res.Failure(1,"No data!");
        }
    }
    
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Res save(API00206b api00206b){
        api00206bService.save(api00206b);
        return Res.Success();
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Res delete(int id){
        api00206bService.deleteById(id);
        return Res.Success();
    }

    @ApiOperation(value = "查询单个")
    @GetMapping("/detail")
    public Res detail(int id){
        API00206b api00206b = api00206bService.findOneById(id);
        return Res.Success(api00206b);
    }

    @ApiOperation(value = "发送API0006b")
    @GetMapping("/send")
    public Res send(API00206b api00206b){
        api00206bService.sendAPI00206b(api00206b);
        return Res.Success();
    }
}