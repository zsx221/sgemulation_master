package com.macrochina.net.dto;

import com.macrochina.net.entity.BizRuleData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class BizRuleSetDto {
    @ApiModelProperty(value = "Rules Name")
    private String name;
    @ApiModelProperty(value = "Transcations")
    private String bizType;
    @ApiModelProperty(value = "Priority level")
    private int prority;
    @ApiModelProperty(value = "Response Status")
    private String resp_status;
    @ApiModelProperty(value = "Status")
    private int status;
    @ApiModelProperty(value = "HTTP Code")
    private String httpResp;
    @ApiModelProperty(value = "Reject code")
    private String rsnCode;
    @ApiModelProperty(value = "Reject message")
    private String rsnMsg;
    @ApiModelProperty(value = "error Code")
    private String errorCode;
    @ApiModelProperty(value = "error message")
    private String errorMessage;


    @ApiModelProperty(value = "sendCallbackNum")
    private Integer sendCallbackNum;
    @ApiModelProperty(value = "sendIntervalTime")
    private Integer sendIntervalTime;


    @ApiModelProperty(value = "延迟时间")
    private Integer retryAfter;

    @ApiModelProperty(value = "时间格式(Date/Seconds)")
    private String format;

    private Integer isTimeOut;
    private Integer timeOut;
    private Integer callbackIsTimeOut;
    private Integer callbackTimeOut;
    private String sendOrder;
    private String isCallback;
    private String sendCamt056;
    private String sendAdmi002;
    private List<BizRuleData> bizRuleDatas;
}
