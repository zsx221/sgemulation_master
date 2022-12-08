package com.macrochina.net.controller;

import com.macrochina.net.dto.MessageDto;
import com.macrochina.net.dto.MessagePacsDto;
import com.macrochina.net.entity.Message;
import com.macrochina.net.entity.MessagePacs;
import com.macrochina.net.entity.Res;
import com.macrochina.net.service.MessagePacsService;
import com.macrochina.net.service.MessageService;
import com.macrochina.net.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Optional;

@Api(tags = "message")
@RestController
@RequestMapping("/msg")
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessagePacsService messagePacsService;

    @ApiOperation(value = "interchange根据参数查找",notes = "")
    @RequestMapping(value = "/queryMessage",method = RequestMethod.GET)
    public Res QueryMessage(@RequestParam Integer page, @RequestParam Integer limit, MessageDto model){
        Page<Message> data = this.messageService.findAllMessageByCdn(page,limit,model);
        if (data!=null) {


            return Res.Success(data);
        }else {
            return Res.Failure(1,"错误");
        }
    }
    @ApiOperation(value = "interchange返回Xml",notes = "")
    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public Res detail(@RequestParam int id){
        Optional<Message> message = messageService.findById(id);
        String url = message.get().getMsgFile();

        /*File file = new File(url);
        FileUtils.loadFile(url);
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
            return Res.Failure(1,"错误");
        }*/
        return Res.Success(FileUtils.loadFile(url));
    }

    @ApiOperation(value = "transcations根据参数查找",notes = "")
    @RequestMapping(value="/queryMessagePacs",method = RequestMethod.GET)
    public Res queryMessagePacs(@RequestParam Integer page, @RequestParam Integer limit, MessagePacsDto model){
        Page<MessagePacs> data = this.messagePacsService.findAllMessagePacsByCdn(page,limit,model);
        if (data!=null) {

            return Res.Success(data);
        }else {
            return Res.Failure(1,"错误");
        }
    }

    @ApiOperation(value = "批量删除messagePacs",notes = "")
    @RequestMapping(value="/deleteMessagePacs",method = RequestMethod.POST)
    public Res deleteMessagePacs(String ids){
        this.messagePacsService.deleteMessagePacs(ids);
        return Res.Success();

    }
    @ApiOperation(value = "批量删除message",notes = "")
    @RequestMapping(value="/deleteMessage",method = RequestMethod.POST)
    public Res deleteMessage(String ids){
        this.messageService.deleteMessage(ids);
        return Res.Success();

    }
}
