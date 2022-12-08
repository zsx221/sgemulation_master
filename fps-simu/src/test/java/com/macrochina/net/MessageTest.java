package com.macrochina.net;

import com.macrochina.net.controller.BizRuleController;
import com.macrochina.net.controller.MessageController;
import com.macrochina.net.dto.BizRuleSetDto;
import com.macrochina.net.entity.BizRuleData;
import com.macrochina.net.entity.SamplePacs008;
import com.macrochina.net.service.BizRuleService;
import com.macrochina.net.service.MessagePacsService;
import com.macrochina.net.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTest {
    @Autowired
    MessageService messageService;
    @Autowired
    MessageController messageController;
    @Autowired
    MessagePacsService pacsService;
    @Autowired
    BizRuleController bizRuleController;
    @Autowired
    BizRuleService bizRuleService;
    @Test
    public void send008(){
        List<SamplePacs008> lists = new ArrayList<SamplePacs008>();
        SamplePacs008 pacs008 = new SamplePacs008();
        pacs008.setGrphdrccy("2");
        pacs008.setEndtoend("123123");
        pacs008.setTxid("1");
        pacs008.setChrgbr("12312");
        pacs008.setCdtrclrsysmmbid("1");
        pacs008.setDbtrclrsysmmbid("1");
        pacs008.setDbtrnm("213");
        pacs008.setDbtracct("132");
        pacs008.setCdtrnm("234");
        pacs008.setCdtracct("12311");
        pacs008.setPurp("123123");
        pacs008.setRmtinf("121321");
        lists.add(pacs008);
//        messageService.sendMessagePacs008(lists);
    }

    @Test
    public void xml(){
//        bizRuleController.deleteRule(7);
        BizRuleData bd = new BizRuleData();
        BizRuleData bd1 = new BizRuleData();
        bd.setClassName("aaaa");
        bd.setClassValue("bbb");
        bd1.setField("ccc");
        bd.setClassName("aaa");
        bd1.setClassValue("bb");
        bd1.setField("cc");
        List<BizRuleData> bds= new ArrayList<>();
        bds.add(bd);
        bds.add(bd1);
        BizRuleSetDto b = new BizRuleSetDto();
        b.setBizType("pacs.008.01.02");
        b.setErrorCode("1");
        b.setHttpResp("200");
        b.setName("PHONE RULE");
        b.setPrority(1);
        b.setResp_status("successful");
        b.setRsnCode("2");
        b.setStatus(1);
        b.setBizRuleDatas(bds);

        bizRuleController.addRule(b);
    }

    
}
