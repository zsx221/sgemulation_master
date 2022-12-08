package com.macrochina.net.controller;

import com.macrochina.net.entity.Res;
import com.macrochina.net.entity.SysDict;
import com.macrochina.net.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "字典表")
@RestController
@RequestMapping("/sysdict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @ApiOperation(value = "查询所有")
    @GetMapping("/findAll")
    public Res findAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                       SysDict dict){
        Page<SysDict> data = sysDictService.findAll(page,limit,dict);
        if (data != null) {
            return Res.Success(data);
        }else {
            return Res.Failure(1,"No data!");
        }
    }

    @ApiOperation(value = "根据类型查找")
    @RequestMapping(value = "/findAllByBizGroup",method = RequestMethod.GET)
    public Res findAllByBizGroup(@RequestParam String bizGroup){
        List<SysDict> sysDictList = sysDictService.findAllByBizGroup(bizGroup);
        return Res.Success(sysDictList);
    }

    @ApiOperation(value = "查询单个")
    @GetMapping("/detail")
    public Res detail(int id){
        SysDict code = sysDictService.findOneById(id);
        return Res.Success(code);
    }

    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Res save(SysDict code){
        sysDictService.save(code);
        return Res.Success();
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Res delete(int id){
        sysDictService.deleteById(id);
        return Res.Success();
    }
}
