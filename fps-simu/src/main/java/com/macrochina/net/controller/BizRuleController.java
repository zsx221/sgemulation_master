package com.macrochina.net.controller;
import com.macrochina.net.dto.BizRuleSetDto;
import com.macrochina.net.entity.BizRuleData;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.BizRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(tags = "规则管理")
@RestController
@RequestMapping("/Rule")
public class BizRuleController {
    @Autowired
    private BizRuleService bizRuleService;
    @ApiOperation(value = "查看全部规则/根据参数查找",notes = "")
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Res findAll(@RequestParam Integer page, @RequestParam Integer limit, BizRuleSetDto molde){
        Page<BizRuleSet> data = this.bizRuleService.findAllRuleSetByCdn(page,limit,molde);
        if (data!=null) {
            return Res.Success(data);
        }else {
            return Res.Failure(1,"错误");
        }
    }
    @ApiOperation(value = "增加规则",notes = "")
    @RequestMapping(value = "/addRule",method = RequestMethod.POST)
    public Res addRule(BizRuleSetDto bizRuleSetDto){
        BizRuleSet bizRuleSet = new BizRuleSet();
        BeanUtils.copyProperties(bizRuleSetDto, bizRuleSet, "id","bizRuleDatas");
        bizRuleSet.setRemark("1123");
        BizRuleSet a = bizRuleService.saveSet(bizRuleSet);
        int id = a.getId();
        List<BizRuleData> bizRuleDatas = bizRuleSetDto.getBizRuleDatas();
        bizRuleDatas.forEach(x->x.setRid(id));
        bizRuleService.saveAllDatas(bizRuleDatas);
        return Res.Success();
    }

    @ApiOperation(value = "删除规则",notes = "")
    @RequestMapping(value = "/deleteRule",method = RequestMethod.GET)
    public Res deleteRule(int id){
        int rid = bizRuleService.findOneSetById(id).getId();
        bizRuleService.deleteSetById(id);
        bizRuleService.deleteDataByRid(rid);
        return Res.Success();
    }

    @ApiOperation(value = "查询单个规则",notes = "")
    @RequestMapping(value = "/ruleDetail",method = RequestMethod.POST)
    public Res ruleDetail(int id){
        BizRuleSetDto bizRuleSetDto = new BizRuleSetDto();
        BizRuleSet bizRuleSet = bizRuleService.findOneSetById(id);
        BeanUtils.copyProperties(bizRuleSet, bizRuleSetDto, "id");
        List<BizRuleData> bizRuleDatas = bizRuleService.findAllRuleDataByRid(id);
        bizRuleSetDto.setBizRuleDatas(bizRuleDatas);
        return Res.Success(bizRuleSetDto);
    }

    @ApiOperation(value = "修改规则",notes = "")
    @RequestMapping(value = "/updateRule",method = RequestMethod.POST)
    public Res updateRule(int id,BizRuleSetDto bizRuleSetDto){
        BizRuleSet bizRuleSet = bizRuleService.findOneSetById(id);
        BeanUtils.copyProperties(bizRuleSetDto, bizRuleSet, "id","bizRuleDatas");
        bizRuleService.saveSet(bizRuleSet);
        List<BizRuleData> bizRuleDatas = bizRuleSetDto.getBizRuleDatas();
        bizRuleService.deleteDataByRid(id);
        bizRuleDatas.forEach(x->x.setRid(id));
        bizRuleService.saveAllDatas(bizRuleDatas);
        return Res.Success("修改成功");
    }
    @ApiOperation(value = "修改状态",notes = "")
    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    public Res updateStatus(int id,int status){
        BizRuleSet bizRuleSet = bizRuleService.findOneSetById(id);
        bizRuleSet.setStatus(status);
        bizRuleService.saveSet(bizRuleSet);
        return Res.Success("修改成功");
    }
}
