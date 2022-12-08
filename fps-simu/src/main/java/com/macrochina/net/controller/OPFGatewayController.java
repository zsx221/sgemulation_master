package com.macrochina.net.controller;

import com.alibaba.fastjson.JSONObject;
import com.macrochina.net.dto.*;
import com.macrochina.net.entity.Acct;
import com.macrochina.net.entity.BizRuleSet;
import com.macrochina.net.entity.FpsParam;
import com.macrochina.net.entity.Message;
import com.macrochina.net.service.*;
import com.macrochina.net.util.MessageUtils;
import com.macrochina.net.util.RetryAfter429;
import com.macrochina.net.utils.SysParamsContst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags = "opf-gateway")
@RestController
@RequestMapping("/opf-gateway")
public class OPFGatewayController {
    @Autowired
    RuleService ruleService;

    @Autowired
    private MessageService messageService;

    @Autowired
    OPFGatewayService opfGatewayService;

    @Autowired
    FpsParamService fpsParamService;

    @Autowired
    AcctService acctService;

    @ApiOperation(value = "Payment Initiation Callback", notes = "")
    @RequestMapping(value = "/FAST/payment-callbacks", method = RequestMethod.POST)
    @ResponseBody
    public Map paymentCallbacks(@RequestBody PaymentCallbacks paymentCallbacks, HttpServletResponse response) {
       //200
        Message message = new Message();
        message.setTag("OpfGateway");
        message.setMsgId("");
        message.setMsgDef("payment-callbacks");
        message.setDirct(Integer.parseInt(SysParamsContst.INWARD));
        message.setRemark(JSONObject.toJSONString(paymentCallbacks));
        messageService.save(message);
        return ack(response,"payment-callbacks",paymentCallbacks);
    }
    @ApiOperation(value = "Posting", notes = "")
    @RequestMapping(value = "/posting-requests", method = RequestMethod.POST)
    @ResponseBody
    //200
    public Map posting(@RequestBody PostingRequestsDto postingRequestsDto, HttpServletResponse response) {
        Message msg = new Message();
        msg.setTag("OpfGateway");
        msg.setMsgId(MessageUtils.genMsgId());//产生一个 22位 字母+数字，然后把这个放到数据库里面去
        msg.setMsgDef("posting-requests");//设置msg_def为posting-requests
        msg.setDirct(Integer.parseInt(SysParamsContst.INWARD));//标识这个是测试发来的
        msg.setRemark(JSONObject.toJSONString(postingRequestsDto));//把发过来的json数据储存了
        messageService.save(msg);//提交到数据库
        PostingRequests postingRequests = new PostingRequests(postingRequestsDto);//发来的json数据也存到规定的实体类里面
        FpsParam fpsParam = fpsParamService.findById();//通过id查找所有的数据,不为空的话就返回第一个数据，为空就返回空
        JSONObject jsonObject = new JSONObject();
        BizRuleSet bizRuleSet = ruleService.ruleOption("posting",postingRequests);//把json数据的类型和数据传进去处理,然后返回一个命中的规则
        boolean flag = RetryAfter429.retryAfter(response,bizRuleSet);
        if(!flag){ //httpcode为429时直接return
            return null;
        }
        Map res = new HashMap();
        if(bizRuleSet==null ||(bizRuleSet != null && "200".equals(bizRuleSet.getHttpResp()))){

            response.setStatus(200);
            if (bizRuleSet != null ){
                res.put("errorCode",  bizRuleSet.getErrorCode());
                res.put("errorDescription", bizRuleSet.getErrorMessage());
            }
        }else if(bizRuleSet != null && !"200".equals(bizRuleSet.getHttpResp())){
            res.put("errorCode", bizRuleSet.getErrorCode());
            res.put("errorDescription", bizRuleSet.getErrorMessage());
            response.setStatus(Integer.valueOf(bizRuleSet.getHttpResp()));
            return res;
        }


        //bizRuleSet==null ||(bizRuleSet != null && "200".equals(bizRuleSet.getHttpResp()))才会执行
        jsonObject.put("postingRequestId",postingRequestsDto.getPostingRequestId());
        jsonObject.put("opfTransactionId",postingRequestsDto.getOpfTransactionId());
        jsonObject.put("status", StringUtils.isEmpty(res.get("errorCode"))?"SUCCESS":"FAILED");
        jsonObject.put("rejectReasonCode",res.get("errorCode"));
        jsonObject.put("rejectReasonDesc",res.get("errorDescription"));
        jsonObject.put("transactionType",postingRequests.getTransactionType());
        jsonObject.put("reverseTransaction",postingRequests.getReverseTransaction());

        //713 新增
        if(org.apache.commons.lang3.StringUtils.isNotBlank(postingRequests.getBcsTransactionId()) || org.apache.commons.lang3.StringUtils.isNotBlank(postingRequests.getBcsInstructionId())){
            jsonObject.put("bcsTransactionId",postingRequests.getBcsTransactionId());
            jsonObject.put("bcsInstructionId",postingRequests.getBcsInstructionId());
        }


        Map<String,String> map = new HashMap<String,String>();
        map.put("Content-Type", "application/json;charset=utf-8");
        String url = fpsParam.getHostAdr() + "/channel/G3/FAST/postingcallbacks";
       // String url = "https://localhost:3306"+ "/channel/G3/FAST/postingcallbacks";
        if (bizRuleSet != null) {
            opfGatewayService.delaySendCallback(bizRuleSet, jsonObject, map, url,postingRequestsDto);
            Integer isTimeOut = bizRuleSet.getIsTimeOut();
            String httpResp = bizRuleSet.getHttpResp();
            String status = bizRuleSet.getResp_status();
            //判断是否ack超时
            if (isTimeOut != null && bizRuleSet.getIsTimeOut() == 1) {
                try {
                    Thread.sleep((bizRuleSet.getTimeOut() == null ? 0 : bizRuleSet.getTimeOut()) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (status.equals("HTTP Code")) {
                if (org.apache.commons.lang3.StringUtils.isBlank(httpResp) || httpResp.equals("200")) {
                    response.setStatus(HttpStatus.OK.value());
                } else {
                    response.setStatus(Integer.parseInt(httpResp));
                    return res;
                }
            }
        }else{
            response.setStatus(Integer.valueOf(200));
            if("1".equals(fpsParam.getAutoReturnPostingCallback())) {
                opfGatewayService.delaySendCallback(bizRuleSet, jsonObject, map, url, postingRequestsDto);
            }

        }
        return res;
    }


    /**
     * @return void
     * @Author fangyu
     * @Description
     * "endToEndId": "FPS-13214432324532412321",
     * "opfTransactionId": "12345678901234567890xxxxx",
     * "errorCode": "ERR_OPF_001",
     * "debugMessage": "Rejected as counter party bank is sanction bank"
     * @Date 2021/8/28 11:11
     * @Param [response]
     **/
    @ApiOperation(value = "Inward Payment Instructions", notes = "")
    @RequestMapping(value = "/FAST/inward-payment-instructions", method = RequestMethod.POST)
    @ResponseBody
    public Map inwardPaymentInstructions(@RequestBody InwardPaymentInstructionsDto inwardPaymentInstructionsDto, HttpServletResponse response) {
        Message msg = new Message();
        FpsParam fpsParam = fpsParamService.findById();
        msg.setTag("OpfGateway");
        msg.setMsgId(inwardPaymentInstructionsDto.getOpfTransactionId());
        msg.setMsgDef("inward-payment-instructions");
        msg.setDirct(Integer.parseInt(SysParamsContst.INWARD));
        msg.setRemark(JSONObject.toJSONString(inwardPaymentInstructionsDto));
        messageService.save(msg);
        InwardPaymentInstructions inwardPaymentInstructions = new InwardPaymentInstructions(inwardPaymentInstructionsDto);
        BizRuleSet bizRuleSet = ruleService.ruleOption("inward-payment-instructions",inwardPaymentInstructions);
        boolean flag = RetryAfter429.retryAfter(response,bizRuleSet);
        if(!flag){ //httpcode为429时直接return
            return null;
        }
        Map res = new HashMap();
        Acct acct = acctService.findAcctByBankAcct(inwardPaymentInstructionsDto.getPayeeAccountId());

        //是否校验账号
        if("1".equals(fpsParam.getAccountValidation())){
            if(acct==null){
                res.put("errorCode", "ERR_PAY_151");
                res.put("debugMessage", "Invalid Receiving Account No");
            }else if("7".equals(acct.getState())){
                res.put("errorCode", "ERR_PAY_156");
                res.put("debugMessage", "Beneficiary Account Closed");
            }else if(inwardPaymentInstructionsDto.getPayeeName().equals(acct.getAcctNm())){
                res.put("errorCode", "ERR_PAY_158");
                res.put("debugMessage", "Beneficiary Account Name Does Not Match Beneficiary Account Number");
            }else if("9".equals(acct.getState())){
                res.put("errorCode", "ERR_PAY_162");
                res.put("debugMessage", "Blocked Account");
            }
        }

        if(bizRuleSet == null ||(bizRuleSet != null && "200".equals(bizRuleSet.getHttpResp()))){
            res.put("paymentInstructionId",UUID.randomUUID().toString());
            res.put("clientTransactionId", UUID.randomUUID().toString());
            res.put("endToEndId",inwardPaymentInstructionsDto.getEndToEndId());
            res.put("opfTransactionId",inwardPaymentInstructionsDto.getOpfTransactionId());
            //713 新增
            if(org.apache.commons.lang3.StringUtils.isNotBlank(inwardPaymentInstructionsDto.getBcsTransactionId()) ||
                    org.apache.commons.lang3.StringUtils.isNotBlank(inwardPaymentInstructionsDto.getPaymentPurpose()) ||
                    org.apache.commons.lang3.StringUtils.isNotBlank(inwardPaymentInstructionsDto.getBcsInstructionId())){
                res.put("bcsTransactionId",inwardPaymentInstructionsDto.getBcsTransactionId());
                res.put("bcsInstructionId",inwardPaymentInstructionsDto.getBcsInstructionId());
                res.put("paymentPurpose",inwardPaymentInstructionsDto.getPaymentPurpose());
            }
            response.setStatus(200);
            if(bizRuleSet != null && bizRuleSet.getErrorCode() != null){
                res.put("errorCode", bizRuleSet.getErrorCode());
                res.put("debugMessage", bizRuleSet.getErrorMessage());
            }
        }else{
            res.put("errorCode", bizRuleSet.getErrorCode());
            res.put("errorDescription", bizRuleSet.getErrorMessage());
            response.setStatus(Integer.valueOf(bizRuleSet.getHttpResp()));
        }
        if("AIIN".equals(inwardPaymentInstructionsDto.getPayeeAccountIdType())){
            res.put("payeeInternalAccountId","");
            res.put("payerInternalAccountId",inwardPaymentInstructionsDto.getPayerAccountId());
            res.put("paymentInstructionId",UUID.randomUUID().toString());
            res.put("clientTransactionId", UUID.randomUUID().toString());
            res.put("endToEndId",inwardPaymentInstructionsDto.getEndToEndId());
            res.put("opfTransactionId",inwardPaymentInstructionsDto.getOpfTransactionId());
            //713 新增
            if(org.apache.commons.lang3.StringUtils.isNotBlank(inwardPaymentInstructionsDto.getBcsTransactionId()) ||
                    org.apache.commons.lang3.StringUtils.isNotBlank(inwardPaymentInstructionsDto.getPaymentPurpose()) ||
                    org.apache.commons.lang3.StringUtils.isNotBlank(inwardPaymentInstructionsDto.getBcsInstructionId())){
                res.put("bcsTransactionId",inwardPaymentInstructionsDto.getBcsTransactionId());
                res.put("bcsInstructionId",inwardPaymentInstructionsDto.getBcsInstructionId());
                res.put("paymentPurpose",inwardPaymentInstructionsDto.getPaymentPurpose());
            }
            if(acct==null){
                res.put("errorCode", "ERR_PAY_151");
                res.put("debugMessage", "Invalid Receiving Account No .Coverage Rule");
            }else{
                res.put("payeeInternalAccountId",acct.getPhxCard());
            }
        }
        Message message = new Message();
        message.setTag("OpfGateway");
        message.setMsgId(inwardPaymentInstructionsDto.getOpfTransactionId());
        message.setDirct(Integer.parseInt(SysParamsContst.OUTWARD));
        message.setMsgDef("inward-payment-instructions-response");
        message.setRemark(JSONObject.toJSONString(res));
        messageService.save(message);
        return res;
    }


    @ApiOperation(value = "Get Tx limits", notes = "")
    @RequestMapping(value = "/transaction-limits/limit", method = RequestMethod.POST)
    @ResponseBody
    public Map transactionLimits(@RequestParam String customerId ,
                                 @RequestParam double amount  ,
                                 @RequestParam String limitCategory ,
                                 @RequestParam String tracingId  ,
                                 HttpServletRequest request, HttpServletResponse response) {
//        Message msg = new Message();
//        msg.setTag("OpfGateway");
//        msg.setMsgId(MessageUtils.genMsgId());
//        msg.setMsgDef("transaction-limits-limit");
//        msg.setDirct(Integer.parseInt(SysParamsContst.OUTWARD));
//        Map map = new HashMap();
//        msg.setRemark(JSONObject.toJSONString(map));
//        messageService.save(msg);
        TransactionLimits transactionLimits = new TransactionLimits();
        transactionLimits.setAmount(amount);
        transactionLimits.setCustomerId(customerId);
        transactionLimits.setLimitCategory(limitCategory);
        transactionLimits.setTracingId(tracingId);
        return ack(response,"transaction-limits-limit",transactionLimits);
    }


    @ApiOperation(value = "PayNow Maintenance Callback", notes = "")
    @RequestMapping(value = "/PayNowMaintenance-callbacks", method = RequestMethod.POST)
    @ResponseBody
    public Map payNowMaintenanceCallbacks(@RequestBody PayNowMaintenanceCallbacks payNowMaintenanceCallbacks, HttpServletResponse response) {
        Message msg = new Message();
        msg.setTag("OpfGateway");
        msg.setMsgId("PayNowMaintenance-callbacks");
        msg.setMsgDef("PayNowMaintenance-callbacks");
        msg.setDirct(Integer.parseInt(SysParamsContst.INWARD));
        msg.setRemark(JSONObject.toJSONString(payNowMaintenanceCallbacks));
        messageService.save(msg);
        return ack(response,"PayNowMaintenance-callbacks",payNowMaintenanceCallbacks);
    }

    @ApiOperation(value = "PayNowLookUp-callbacks", notes = "")
    @RequestMapping(value = "/PayNowLookUp-callbacks", method = RequestMethod.POST)
    @ResponseBody
    public Map payNowLookUpCallbacks( @RequestBody PayNowLookUpCallbacks payNowMaintenanceCallbacks, HttpServletResponse response) {
        Message msg = new Message();
        msg.setTag("OpfGateway");
        msg.setMsgId(payNowMaintenanceCallbacks.getCTxnId());
        msg.setMsgDef("PayNowLookUp-callbacks");
        msg.setDirct(Integer.parseInt(SysParamsContst.INWARD));
        msg.setRemark(JSONObject.toJSONString(payNowMaintenanceCallbacks));
        messageService.save(msg);
        return ack(response,"PayNowLookUp-callbacks",payNowMaintenanceCallbacks);
    }

    @ApiOperation(value = "PayNowEnquiry-callbacks", notes = "")
    @RequestMapping(value = "/PayNowEnquiry-callbacks", method = RequestMethod.POST)
    @ResponseBody
    public Map payNowEnquiryCallbacks(@RequestBody PayNowEnquiryCallbacks payNowEnquiryCallbacks,HttpServletResponse response){
        Message msg = new Message();
        msg.setTag("OpfGateway");
        msg.setMsgId("PayNowEnquiry-callbacks");
        msg.setMsgDef("PayNowEnquiry-callbacks");
        msg.setDirct(Integer.parseInt(SysParamsContst.INWARD));
        msg.setRemark(JSONObject.toJSONString(payNowEnquiryCallbacks));
        messageService.save(msg);
        return ack(response,"PayNowEnquiry-callbacks",payNowEnquiryCallbacks);
    }
    @ApiOperation(value = "PayNowReport-callbacks", notes = "")
    @RequestMapping(value = "/PayNowReport-callbacks", method = RequestMethod.POST)
    @ResponseBody
    public Map payNowReportCallbacks(@RequestBody PayNowReportCallbacks payNowReportCallbacks,HttpServletResponse response){
        Message msg = new Message();
        msg.setTag("OpfGateway");
        msg.setMsgId("PayNowReport-callbacks");
        msg.setMsgDef("PayNowReport-callbacks");
        msg.setDirct(Integer.parseInt(SysParamsContst.INWARD));
        msg.setRemark(JSONObject.toJSONString(payNowReportCallbacks));
        messageService.save(msg);
        return ack(response,"PayNowReport-callbacks",payNowReportCallbacks);
    }

    @ApiOperation(value = "participant-detail", notes = "")
    @RequestMapping(value = "/participant-detail", method = RequestMethod.POST)
    @ResponseBody
    public Map participantDetail(@RequestBody ParticipantDetail participantDetail,HttpServletResponse response){
        Message msg = new Message();
        msg.setTag("OpfGateway");
        msg.setMsgId("");
        msg.setMsgDef("participant-detail");
        msg.setDirct(Integer.parseInt(SysParamsContst.INWARD));
        msg.setRemark(JSONObject.toJSONString(participantDetail));
        messageService.save(msg);
        return ack(response,"participant-detail",participantDetail);
    }

    @ApiOperation(value = "/opf-gateway/banks/{bic}/is-blocked")
    @ResponseBody
    @GetMapping("/banks/{bic}/is-blocked")
    public Map fastReport(@PathVariable String bic, HttpServletResponse response) {
        Message msg = new Message();
        msg.setTag("OpfGateway");
        msg.setMsgId(bic);
        msg.setMsgDef("/banks/"+bic+"/is-blocked");
        msg.setDirct(Integer.parseInt(SysParamsContst.INWARD));
        msg.setRemark(JSONObject.toJSONString(bic));
        messageService.save(msg);
        return ack(response,"is-blocked"," {'blocked': true}");
    }


    //直接回复ack
    private Map ack(HttpServletResponse response,String bizMsgDefIdr, Object object) {
        Map res = new HashMap();
        BizRuleSet bizRuleSet = ruleService.ruleOption(bizMsgDefIdr, object);
        if(bizRuleSet==null ||(bizRuleSet != null && "200".equals(bizRuleSet.getHttpResp()))){
            response.setStatus(200);
            if (bizRuleSet != null ){
                res.put("errorCode",  bizRuleSet.getErrorCode());
                res.put("errorDescription", bizRuleSet.getErrorMessage());
            }
        }else{
            res.put("errorCode", bizRuleSet.getErrorCode());
            res.put("errorDescription", bizRuleSet.getErrorMessage());
            response.setStatus(Integer.valueOf(bizRuleSet.getHttpResp()));
        }
        Message message = new Message();
        message.setTag("OpfGateway");
        message.setMsgId(MessageUtils.genMsgId());
        message.setMsgDef(bizMsgDefIdr+"-response");
        message.setDirct(Integer.parseInt(SysParamsContst.OUTWARD));
        message.setRemark(JSONObject.toJSONString(res));
        messageService.save(message);
        return res;
    }



}
