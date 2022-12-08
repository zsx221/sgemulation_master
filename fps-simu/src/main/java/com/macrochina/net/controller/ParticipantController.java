package com.macrochina.net.controller;

import com.macrochina.net.entity.Participant;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.ParticipantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Api(tags = "参与行查询")
@RestController
@RequestMapping("/part")
public class ParticipantController {
    
    @Autowired
    private ParticipantService participantService;

    @ApiOperation(value = "查询所有")
    @GetMapping("/findAll")
    public Res findAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                       @RequestParam(value = "limit", required = false, defaultValue = "100") Integer limit,
                       Participant participant){
        Page<Participant> data = participantService.findAll(page,limit,participant);
        if (data != null) {
            return Res.Success(data);
        }else {
            return Res.Failure(1,"No data!");
        }
    }

    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Res save(Participant participant){
        participantService.save(participant);
        return Res.Success();
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Res delete(int id){
        participantService.deleteById(id);
        return Res.Success();
    }

    @ApiOperation(value = "查询单个")
    @GetMapping("/detail")
    public Res detail(int id){
        Participant participant = participantService.findOneById(id);
        return Res.Success(participant);
    }
}