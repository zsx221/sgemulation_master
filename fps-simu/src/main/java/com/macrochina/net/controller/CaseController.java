package com.macrochina.net.controller;

import com.macrochina.net.dto.AddressDto;
import com.macrochina.net.dto.CaseDto;
import com.macrochina.net.dto.SamplePace008Dto;
import com.macrochina.net.entity.*;
import com.macrochina.net.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Api(tags = "案例集管理")
@RestController
@RequestMapping("/Case")
public class CaseController {

    private Log logger = LogFactory.getLog(CaseController.class);
    @Autowired
    private CaseService caseService;
    @Autowired
    private SamplePacs008Service samplePacs008Service;
    @Autowired
    private CaseListService caseListService;
    @Autowired
    private MessageService messageService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private API00204Service api00204Service;

    @Autowired
    private Pacs008Service pacs008Service;


    @ApiOperation(value = "查看全部案例集",notes = "")
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Res findAll(@RequestParam Integer page, @RequestParam Integer limit, CaseDto molde){
        Page<Case> data = this.caseService.findAllCaseByCdn(page,limit,molde);
        if (data!=null) {

            return Res.Success(data);
        }else {
            return Res.Failure(1,"错误");
        }
    }



    @ApiOperation(value = "增加案例集",notes = "")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Res addCase(CaseDto casesdto){
        Case res =  caseService.save(casesdto);
        if(res != null){
            return Res.Success(res);
        }else {
            return Res.Failure(Res.Failure,"添加错误！");
        }
    }


    @ApiOperation(value = "删除案例集",notes = "")
    @RequestMapping(value = "/delete",method= RequestMethod.GET)
    public Res delete(@RequestParam int id){

        try {
            caseService.deleteById(id);
            caseListService.deleteByCaseId(id);
            return Res.Success();
        }catch(Exception e){
            return Res.Failure(Res.Failure,"删除失败");
        }
    }

    @ApiOperation(value = "查询案例集",notes = "")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public Res query(HttpServletRequest request){

        int CaseId = Integer.parseInt(request.getParameter("id"));
        List<CaseList> caseLists = caseListService.findAllByCaseId(CaseId);
        Case res = caseService.findByid(CaseId);
        res.setCaseLists(caseLists);
        return Res.Success(res);
    }

    @ApiOperation(value = "查询案例集案例",notes = "")
    @RequestMapping(value = "/querySimple",method = RequestMethod.GET)
    public Res querySimple(HttpServletRequest request){
        int CaseId = Integer.parseInt(request.getParameter("id"));
        List<SamplePacs008> res = samplePacs008Service.findAllSamplePacs008(CaseId,"pacs.008.001.02");
        return Res.Success(res);
    }

    @ApiOperation(value = "查询案例集地址",notes = "")
    @RequestMapping(value = "/queryAddress",method = RequestMethod.GET)
    public Res queryAddress(HttpServletRequest request){
        int CaseId = Integer.parseInt(request.getParameter("id"));
        List<Address> res = addressService.findAllAddress(CaseId,"prxy.001.001.01");
        return Res.Success(res);
    }

    @ApiOperation(value = "修改案例集",notes = "")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Res update(Case model,HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Case res = caseService.update(model);
        return Res.Success(res);
    }




    @ApiOperation(value = "查找案例",notes = "")
    @RequestMapping(value = "/findAllByTxid",method = RequestMethod.POST)
    public Res findAllByTxid(@RequestParam Integer page, @RequestParam Integer limit, SamplePace008Dto model){
        System.out.println(model);
        Page<SamplePacs008> data = null;
        if (model.getTxid().equals("pacs.008.001.02")) {
            data = this.samplePacs008Service.findAllByTxid(page, limit, model);
            return Res.Success(data);
        }else {
            return Res.Failure(1, "错误");
        }

    }


    @ApiOperation(value = "查找地址",notes = "")
    @RequestMapping(value = "/findAddrs",method = RequestMethod.POST)
    public Res findAddrs(@RequestParam Integer page, @RequestParam Integer limit, AddressDto model){
        System.out.println(model);
        Page<Address> data =null;
        if (model.getMsgDef().equals("prxy.001.001.01")){
            data = this.addressService.findAllByMsgDef(page,limit,model);
        }
        if (data!=null) {
            return Res.Success(data);
        }else {
            return Res.Failure(1,"错误");
        }
    }

    @ApiOperation(value = "发送案例集",notes = "")
    @RequestMapping(value = "/send/{id}/{count}",method = RequestMethod.GET)
    public Res send(@PathVariable int id,@PathVariable int count){
        pacs008Service.send(id,count);
        return Res.Success();
    }


    @ApiOperation(value = "查询案例集案例API00204",notes = "")
    @RequestMapping(value = "/queryAPI00204NotInIds",method = RequestMethod.POST)
    public Res queryAPI00204NotInIds(@RequestParam Integer page, @RequestParam Integer limit, String ids){
        Page<API00204> res = api00204Service.queryAPI00204NotInIds(page,limit,ids);
        return Res.Success(res);
    }

    @ApiOperation(value = "查询案例集案例API00204",notes = "")
    @RequestMapping(value = "/queryAPI00204",method = RequestMethod.POST)
    public Res queryAPI00204(int caseId){
        List<API00204> res = api00204Service.queryAPI00204(caseId);
        return Res.Success(res);
    }
}
