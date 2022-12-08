package com.macrochina.net.service;

import bcsis.HdrAndData;
import com.macrochina.net.dao.MessageRepository;
import com.macrochina.net.entity.Message;
import com.macrochina.net.utils.SysParamsContst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageAdmnService {
    @Autowired
    MessageRepository messageRepository;

    /**
     * save message
     * @param fpsMsgEnv
     * @param url
     * @param dirct 往来标识
     */
    public void saveMessage(HdrAndData fpsMsgEnv, String url, String dirct){
        String fr = fpsMsgEnv.getAppHdr().getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId();
        String to = fpsMsgEnv.getAppHdr().getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId();
        String msgId = fpsMsgEnv.getAppHdr().getBizMsgIdr();
        Message fpsMsg = new Message();
        if(dirct.equals(SysParamsContst.OUTWARD)){
            fpsMsg.setDirct(0);// 0:往帐
        }else {
            fpsMsg.setDirct(1);// 1:来账
        }
        fpsMsg.setToInstn(to);
        fpsMsg.setFromInstn(fr);
//        fpsMsg.setNumMsg(1); //报文笔数
        fpsMsg.setMsgDef(fpsMsgEnv.getAppHdr().getMsgDefIdr().value());
        fpsMsg.setMsgId(msgId);
        fpsMsg.setMsgFile(url);
        fpsMsg.setTag("Fast");
        messageRepository.save(fpsMsg);
    }
}
