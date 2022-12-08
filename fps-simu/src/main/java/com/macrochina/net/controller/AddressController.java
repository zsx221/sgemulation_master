package com.macrochina.net.controller;

import com.macrochina.net.dto.AddrsDto;
import com.macrochina.net.entity.Address;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Api(tags = "地址管理")
@RestController
@RequestMapping("/adrs")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "查询所有")
    @GetMapping("/findAll")
    public Res findAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                       Address address) {
        Page<Address> data = addressService.findAll(page, limit, address);
        if (data != null) {
            return Res.Success(data);
        } else {
            return Res.Failure(1, "error");
        }
    }

    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Res save(Address address) {
        if(StringUtils.isBlank(address.getBic())){
            return Res.Failure(1, "bic is null");
        }
        addressService.save(address);
        return Res.Success();
    }

    @ApiOperation(value = "参数检查")
    @PostMapping("/check")
    public Res check(String bic,String param, String checkType) {
        String b = addressService.check2(bic,param, checkType);
        if (StringUtils.isNotBlank(b)){
            return Res.Failure(1,b);
        }else {
            return Res.Success();
        }
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Res delete(int id) {
        addressService.deleteById(id);
        return Res.Success();
    }

    @ApiOperation(value = "查询单个")
    @GetMapping("/detail")
    public Res detail(int id) {
        Address address = addressService.findOneById(id);
        return Res.Success(address);
    }

    @ApiOperation(value = "查询静态数据列表")
    @GetMapping("/list")
    public Page<AddrsDto> list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                               @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        return addressService.findAdrsAll(page, limit);
    }
}