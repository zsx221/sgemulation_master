package com.macrochina.net.controller;

import com.macrochina.net.dto.BizRuleSetDto;
import com.macrochina.net.entity.API00204;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.API00204Service;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "API00204")
@RestController
@CrossOrigin
@RequestMapping("/API00204")
public class API00204Controller {

    @Autowired
    API00204Service api00204Service;

    @RequestMapping(value="/saveAPI00204",method = RequestMethod.POST)
    @ResponseBody
    public Res saveAPI00204(API00204 api00204){
        api00204Service.save(api00204);
        return Res.Success();
    }

    @RequestMapping(value="/updateAPI00204",method = RequestMethod.POST)
    @ResponseBody
    public Res updateAPI00204(API00204 api00204){
        api00204Service.update(api00204);
        return Res.Success();
    }

    @RequestMapping(value="/deleteAPI00204",method = RequestMethod.POST)
    @ResponseBody
    public Res deleteAPI00204(Integer id){
        api00204Service.deleteById(id);
        return Res.Success();
    }

    @RequestMapping(value="/findAPI00204",method = RequestMethod.POST)
    @ResponseBody
    public Res findAPI00204(Integer id){
        API00204 api00204 =api00204Service.findAPI00204ById(id);
        return Res.Success(api00204);
    }

    @RequestMapping(value="/findAllAPI00204",method = RequestMethod.POST)
    @ResponseBody
    public Res findAllAPI00204(@RequestParam Integer page, @RequestParam Integer limit,API00204 api00204){
        Page<API00204> api00204s =api00204Service.findAll( page,  limit, api00204);
        return Res.Success(api00204s);
    }
}
