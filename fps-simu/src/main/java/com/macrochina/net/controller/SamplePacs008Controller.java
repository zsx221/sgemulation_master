package com.macrochina.net.controller;

import com.macrochina.net.entity.Res;
import com.macrochina.net.dto.SamplePace008Dto;
import com.macrochina.net.entity.SamplePacs008;
import com.macrochina.net.service.SamplePacs008Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(tags = "案例管理")
@RestController
@RequestMapping("/SamplePace0008")
public class SamplePacs008Controller {
    private Log logger = LogFactory.getLog(SamplePacs008Controller.class);


    @Autowired
    private SamplePacs008Service samplePacs008Service;

    @ApiOperation(value = "增加案例",notes = "")
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public Res addSamplePace008Service(SamplePacs008 model){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SamplePacs008 res = model;
        res.setCreatetime(sdf.format(new Date()));
        res = samplePacs008Service.save(model);
        if(res != null){
            return Res.Success(res);
        }else {
            return Res.Failure(Res.Failure,"添加错误！");
        }
    }

    @ApiOperation(value = "删除案例",notes = "")
    @RequestMapping(value="/delete",method= RequestMethod.GET)
    public Res delete(@RequestParam int id){

    try {
        samplePacs008Service.deleteById(id);
        return Res.Success();
    }catch(Exception e){
            return Res.Failure(Res.Failure,"删除失败");
        }
    }

    @ApiOperation(value = "修改案例",notes = "")
    @RequestMapping(value="/update",method= RequestMethod.POST)
    public Res updateSamplePace008Service(SamplePacs008 model, HttpServletRequest request){
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            SamplePacs008 res = samplePacs008Service.findById(id);
            BeanUtils.copyProperties(model, res, "id");
            samplePacs008Service.save(res);
            return Res.Success(res);
        }catch (Exception e){
            return Res.Failure(Res.Failure,"修改错误！");
        }
    }

    @ApiOperation(value = "根据id查找案例",notes = "")
    @RequestMapping(value="/findyById",method= RequestMethod.GET)
    public Res findyById(@RequestParam int id){
        SamplePacs008 samplePacs008 = samplePacs008Service.findById(id);
        if (samplePacs008!=null) {
            return Res.Success(samplePacs008);
        }else{
            return Res.Failure(Res.Failure,"错误！");
        }
    }

    @ApiOperation(value = "根据参数查找案例",notes = "")
    @RequestMapping(value="/findAll",method= RequestMethod.GET)
    public Res findAll(@RequestParam Integer page, @RequestParam Integer limit, SamplePace008Dto molde){
        Page<SamplePacs008> data = this.samplePacs008Service.findAllSamplePacs008ByCdn(page,limit,molde);
        if (data!=null) {
            return Res.Success(data);
        }else {
            return Res.Failure(1,"错误");
        }
    }
}
