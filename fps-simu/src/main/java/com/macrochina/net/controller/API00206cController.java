package com.macrochina.net.controller;

import com.macrochina.net.entity.API00206a;
import com.macrochina.net.entity.API00206c;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.API00206cService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Api(tags = "API00206c")
@RestController
@RequestMapping("/api00206c")
public class API00206cController {
    
    @Autowired
    private API00206cService api00206cService;

    @ApiOperation(value = "查询所有")
    @GetMapping("/findAll")
    public Res findAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                       API00206c api00206c){
        Page<API00206c> data = api00206cService.findAll(page,limit,api00206c);
        if (data != null) {
            return Res.Success(data);
        }else {
            return Res.Failure(1,"No data!");
        }
    }
    
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Res save(API00206c api00206c){
        api00206cService.save(api00206c);
        return Res.Success();
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Res delete(int id){
        api00206cService.deleteById(id);
        return Res.Success();
    }

    @ApiOperation(value = "查询单个")
    @GetMapping("/detail")
    public Res detail(int id){
        API00206c api00206c = api00206cService.findOneById(id);
        return Res.Success(api00206c);
    }

    @ApiOperation(value = "发送API0006c")
    @GetMapping("/send")
    public Res send(API00206c api00206c){
        api00206cService.sendAPI00206c(api00206c);
        return Res.Success();
    }
}