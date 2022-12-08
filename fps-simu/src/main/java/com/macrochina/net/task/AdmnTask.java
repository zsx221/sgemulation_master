package com.macrochina.net.task;

import bcsis.HdrAndData;
import com.macrochina.net.dao.FpsParamRepository;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.service.*;
import com.macrochina.net.util.*;
import iso.head_001_001.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class AdmnTask {
    private static Log logger = LogFactory.getLog(AdmnTask.class);
    @Autowired
    AdmnService admnService;
    @Autowired
    private SchemaService schemaService;

    @Autowired
    private XmlSignService xmlSignService;

    @Autowired
    DbToXmlService dbToXmlService;

    @Autowired
    MessageAdmnService messageAdmnService;

    @Autowired
    FpsParamRepository fpsParamRepository;
    //3.添加定时任务
    @Autowired
    private  FpsParamService fpsParamService;
    @Scheduled(cron = "0 0 0/1 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() throws JAXBException {
        logger.info("AdmnTask configureTasks start");
        HdrAndData hdrAndData = new HdrAndData();
        BusinessApplicationHeaderV01 oriHead = new BusinessApplicationHeaderV01();
        BusinessApplicationHeaderV01 retHead = new BusinessApplicationHeaderV01();
        Party9Choice fr =  new Party9Choice();
        BranchAndFinancialInstitutionIdentification5 fiid = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 finInstnId = new FinancialInstitutionIdentification8();
        ClearingSystemMemberIdentification2 clrSysMmbId = new ClearingSystemMemberIdentification2();
        FpsParam param = fpsParamService.findById();
        clrSysMmbId.setMmbId(param.getSendBank());
        finInstnId.setClrSysMmbId(clrSysMmbId);
        fiid.setFinInstnId(finInstnId);
        fr.setFIId(fiid);
        oriHead.setFr(fr);
        this.fillHeader(oriHead, retHead, G3MessageTypes.ADMN_005_001_01);
        hdrAndData.setAppHdr(retHead);// 组装 head
        iso.admn_005_001.Document document = new iso.admn_005_001.Document();
        Map<String,Object> map = new HashMap<>();
        map.put("msgId", MessageUtils.genMsgId());
        map.put("creDtTm", XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        map.put("instrId",MessageUtils.genMsgId());//银行唯一标识，自己定时任务测试用msgId

        map.put("instgAgtMmbId",param.getSendBank());
        map.put("instdAgtMmbId",param.getAcceptBank());
        map.put("fnctnCd","731");
        iso.admn_005_001.Document admn005 = (iso.admn_005_001.Document) dbToXmlService.DbToXml(map,new iso.admn_005_001.Document());
        hdrAndData.setEchoRequest(admn005);
        String xml = ConvertUtils.marshal(hdrAndData, true);
        String signXml =  fpsParamService.xmlStringSign(xml);
      //  logger.info("sign end.");
        String url = FileUtils.OUTWARD_RT_REQ+ MessageUtils.getXmlName();
        // 写入本地文件
        FileUtils.write(signXml,url);
      //  String uri = "http://localhost:8080/sgfps/fast/v1/admin/echo/request";
        String ip = param.getHostAdr();
       // logger.info("send http post."+signXml);

        //messageAdmnService.saveMessage(hdrAndData,url,"0");
        HttpClientUtils.doPost(ip+"/fast/v1/admin/echo/host",signXml);

      /*  String fileName = String.valueOf(System.currentTimeMillis());
        FileUtils.strToXml(signXml, fileName);
        Boolean bool = schemaService.validateXmlByXsd(fileName, SysParamsContst.ADMN_005,FileUtils.FPS_SCHEMA);
        if(bool){
            // 验签
            boolean sign = xmlSignService.verifySignByPublicKey(xml,Constants.OFFORON);
            if(sign){
                // 保存报文以及接收到错误报文处理
                FileUtils.write(xml,url);
                admnService.sendAdmn006(admnService.receive(xml));
            }
        }*/
        logger.info("AdmnTask configureTasks end");
    }


    public void fillHeader(BusinessApplicationHeaderV01 origHead, BusinessApplicationHeaderV01 retHead,G3MessageTypes messageType) {
        retHead.setBizMsgIdr(MessageUtils.genMsgId());
        retHead.setMsgDefIdr(messageType);
        retHead.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        retHead.setBizSvc(origHead.getBizSvc());
        Party9Choice fr = new Party9Choice();
        FpsParam param = fpsParamService.findById();
        fillHead(fr, param.getAcceptBank());
        retHead.setFr(fr);
        Party9Choice to = new Party9Choice();
        fillHead(to, origHead.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
        retHead.setTo(to);
    }

    public void fillHead(Party9Choice from, String mid) {
        BranchAndFinancialInstitutionIdentification5 fiid = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 finsId = new FinancialInstitutionIdentification8();
        ClearingSystemMemberIdentification2 mbid = new ClearingSystemMemberIdentification2();
        mbid.setMmbId(mid);
        finsId.setClrSysMmbId(mbid);
        fiid.setFinInstnId(finsId);
        from.setFIId(fiid);
    }
}




























































