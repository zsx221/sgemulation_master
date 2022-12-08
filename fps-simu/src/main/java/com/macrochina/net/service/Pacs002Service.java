package com.macrochina.net.service;

import bcsis.HdrAndData;
import com.macrochina.net.util.ConvertUtils;
import com.macrochina.net.utils.SysParamsContst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Pacs002Service {
    @Autowired
    DbToXmlService dbToXmlService;

    @Autowired
    XmlSignService xmlSignService;

    @Autowired
    MessagePacsService messagePacsService;


    /**
     * @Author fangyu
     * @Description 保存报文文件到本地,并持久化到数据库
     * @Date 2021/8/6 17:56
     * @Param [xml]
     * @return void
     **/
    public void receive(String xml,String fileUrl){
        HdrAndData hdrAndData =ConvertUtils.unmarshal(xml);
        messagePacsService.saveMessage(hdrAndData, fileUrl,SysParamsContst.INWARD);
    }
}