package com.macrochina.net.controller;

import com.macrochina.net.entity.Acct;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.AcctService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(tags = "账户管理")
@RestController
@RequestMapping("/acct")
public class AcctController {
    
    @Autowired
    private AcctService acctService;

    @ApiOperation(value = "查询所有")
    @GetMapping("/findAll")
    public Res findAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                       Acct acct){
        Page<Acct> data = acctService.findAll(page,limit,acct);
        if (data != null) {
            return Res.Success(data);
        }else {
            return Res.Failure(1,"No data!");
        }
    }
    
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Res save(Acct acct){
        if(acct.getId()==null || StringUtils.isEmpty(acct.getPhxCard())) {
            acct.setPhxCard(UUID.randomUUID().toString());
        }
        acctService.save(acct);
        return Res.Success();
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Res delete(int id){
        acctService.deleteById(id);
        return Res.Success();
    }

    @ApiOperation(value = "查询单个")
    @GetMapping("/detail")
    public Res detail(int id){
        Acct acct = acctService.findOneById(id);
        return Res.Success(acct);
    }
    @ApiOperation(value = "根据bankacct查询银行账户")
    @GetMapping("/findAcctByBankAcct")
    public Res findAcctByBankAcct(String bankAcct){
        Acct acct = acctService.findAcctByBankAcct(bankAcct);
        return Res.Success(acct);
    }
}