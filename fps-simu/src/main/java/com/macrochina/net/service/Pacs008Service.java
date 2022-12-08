package com.macrochina.net.service;

import bcsis.HdrAndData;
import com.alibaba.fastjson.JSONObject;
import com.macrochina.net.dao.FpsParamRepository;
import com.macrochina.net.entity.API00204;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.entity.Message;
import com.macrochina.net.entity.SamplePacs008;
import com.macrochina.net.util.*;
import com.macrochina.net.utils.SysParamsContst;
import io.micrometer.core.instrument.util.StringUtils;
import iso.head_001_001.BusinessApplicationHeaderV01;
import iso.head_001_001.G3MessageTypes;
import iso.head_001_001.Party9Choice;
import iso.pacs_008_001.Document;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.*;

@Slf4j
@Service
public class Pacs008Service {


    @Autowired
    DbToXmlService dbToXmlService;

    @Autowired
    FpsParamService fpsParamService;

    @Autowired
    MessagePacsService messagePacsService;

    @Autowired
    SamplePacs008Service samplePacs008Service;

    @Autowired
    API00204Service api00204Service;

    @Autowired
    Camt056Service camt056Service;

    @Autowired
    private MessageService messageService;

    /**
     * @return java.lang.String
     * @Author fangyu
     * @Description 组装发送的报文
     * @Date 2021/8/2 14:58
     * @Param []
     **/

    private String sendMessagePacs008(List<SamplePacs008> samplePacs008s) {
        Map<String, Object> map = new HashMap<>();

        FpsParam param = fpsParamService.findById();
        samplePacs008s.stream().forEach(item -> {
            String msgId = MessageUtils.genMsgId();
            map.put("msgId", msgId);
            map.put("creDtTm", XmlIsoDateUtil.convertToXMLGregorianCalendarMillsec(Calendar.getInstance()));
            map.put("nbOfTxs", "1");
            map.put("ttlIntrBkSttlmAmt", item.getTtlintrbksttlmamt());
            map.put("grpHdrCcy", item.getGrphdrccy());
            map.put("sttlmMtd", "CLRG");
            map.put("clrSysCd", "MEP");
            map.put("instrId", MessageUtils.getCTxnId2(item.getInstdagtmmbid()));
            map.put("endToEndId", item.getEndtoend());
            map.put("txId", MessageUtils.getCTxnId2(item.getInstdagtmmbid()));
            map.put("svcLvl", "SDVA");
            map.put("intrBkSttlmAmt", item.getTtlintrbksttlmamt());
            map.put("intrBkSttlmAmtCcy", item.getGrphdrccy());
            map.put("intrBkSttlmDt", XmlIsoDateUtil.convertToXMLGregorianCalendarDate(Calendar.getInstance()));
            map.put("chrgBr", item.getChrgbr());
            map.put("instgAgtClrSysMmbId", item.getInstgagtmmbid ());
            map.put("instdAgtClrSysMmbId", item.getInstdagtmmbid());
            map.put("dbtrNm", item.getDbtrnm());
            map.put("dbtrAcctId", item.getDbtracct());
            map.put("dbtrAgtMmbId",item.getDbtrclrsysmmbid());
            map.put("cdtrAgtMmbId",  item.getCdtrclrsysmmbid());
            map.put("cdtrNm", item.getCdtrnm());
            map.put("cdtrAcctId", item.getCdtracct());
            map.put("purp", item.getPurp());
            if (!StringUtils.isEmpty(item.getRmtinf())) {
                map.put("rmtInf", item.getRmtinf());
            }
            packageAndSend(item, map);
        });
        return "success!";
    }


    private void packageAndSend(SamplePacs008 item, Map map) {

        FpsParam param = fpsParamService.findById();
        //接收报文接收对象
        Document documentPacs008 = new Document();
        documentPacs008 = (Document) dbToXmlService.DbToXml(map, documentPacs008);
        HdrAndData fpsevnp = new HdrAndData();
        BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();
        String msgId = map.get("msgId").toString();
        appHdr.setBizMsgIdr(msgId);
        appHdr.setMsgDefIdr(G3MessageTypes.PACS_008_001_02);
        appHdr.setCreDt(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance()));
        Party9Choice fr = new Party9Choice();
        MessageHeadUtils.fillHeadFrom(fr, param.getSendBank());
        appHdr.setFr(fr);
        Party9Choice to = new Party9Choice();
        MessageHeadUtils.fillHeadTo(to, param.getAcceptBank());
        appHdr.setTo(to);
        fpsevnp.setAppHdr(appHdr);
        fpsevnp.setCreditTransfer(documentPacs008);
        try {
            String xml = ConvertUtils.marshal(fpsevnp, true);

            String username = "";
            String password = "";
            String partition = "", method = "", alias = "";

            FpsParam fpsParam = fpsParamService.findById();
            String xmlSign = fpsParamService.xmlStringSign(xml);
            //xmlSignService.xmlStringSign(xml,username,password,partition,method,alias);
            String fileUrl = FileUtils.OUTWARD_RT_ACK + MessageUtils.getXmlName();
            FileUtils.write(xmlSign, fileUrl);
//            String fileName = String.valueOf(System.currentTimeMillis());
//            FileUtils.strToXml(xmlSign, fileName);
//            Boolean bool = schemaService.validateXmlByXsd(fileName, SysParamsContst.PACS_002,FileUtils.FPS_SCHEMA);
//            if(bool){
            // 保存报文以及接收到错误报文处理

//        String url = "http://localhost:8080/sgfps/fast/v1/payment/credit-transfer/send";
            String ip = fpsParam.getHostAdr();
            String url = ip + "/fast/v1/payment/credit-transfer/receive";
            //String url = "http://localhost:8080/fast/v1/payment/credit-transfer/receive";
            log.info("---------outgoing pacs008 "+xmlSign);
            HttpClientUtils.doPost(url, xmlSign);
            //判断是否需要超时
            if("1".equals(item.getIsTimeOut())){
                camt056Service.sendCamt056(documentPacs008 ,item.getInstgagtmmbid(),item.getInstdagtmmbid());
            }
            messagePacsService.saveMessage(fpsevnp, fileUrl, SysParamsContst.OUTWARD);
            messagePacsService.saveMessagePacs008(fpsevnp, SysParamsContst.OUTWARD);

//            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async("asyncTaskExecutor")
    public void send(int id, int count) {
        long time1 = System.currentTimeMillis();
        List<SamplePacs008> samplePacs008s = samplePacs008Service.findAllSamplePacs008(id, "pacs.008.001.02");
        //  List<Address> addresses = addressService.findAllAddress(id,"prxy.001.001.01");
        List<API00204> api00204s = api00204Service.queryAPI00204(id);
        for (int i = 0; i < count; i++) {
            if (samplePacs008s != null && samplePacs008s.size() > 0) {
                sendMessagePacs008(samplePacs008s);
            }
            if (api00204s != null && api00204s.size() > 0) {
                sendAPI00204(api00204s);
            }
            // addressService.sendMessagePrxy001(addresses);

        }

        long time2 = System.currentTimeMillis();
        log.info("案例发送时间为 =[{}] ,处理笔数请求 =[{}]", time2 - time1, samplePacs008s.size() * count + api00204s.size() * count);
    }

    private void sendAPI00204(List<API00204> api00204s) {
        FpsParam param = fpsParamService.findById();
        for (API00204 api00204 : api00204s) {
            String url = param.getHostAdr() + "/channel/G3/FAST/PaymentInitiation";
            Map<String, String> map = new HashMap<String, String>();
            map.put("Content-type", "application/json");
            map.put("ClientId", "MBANK");
            map.put("CServiceType  ", "PaymentInstruction");
            map.put("CTxnId", MessageUtils.getCTxnId2(param.getSendBank()));
            map.put("RptIndc", api00204.getRptIndc().toString());
            map.put("CDateTime", DateUtils.formatDate(new Date(), "yyyymmddhhmmss"));
            api00204.setRptIndc(null);
            String jsonObject = JSONObject.toJSONString(api00204);
            Message message = new Message();
            message.setTag("OpfGateway");
            message.setMsgId(MessageUtils.genMsgId());
            message.setMsgDef("PaymentInitiation");
            message.setDirct(Integer.parseInt(SysParamsContst.OUTWARD));
            message.setRemark(jsonObject);
            messageService.save(message);
            HttpClientUtils.doPostHeader(url, jsonObject, map);
        }
    }
}
