package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name="biz_rule_set")
public class BizRuleSet {

    private static final long serialVersionUID = 1L;
    /*
     *唯一标识
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    /**
     * http状态码 202 404 500等等
     */
    @ApiModelProperty(value = "http状态码 202 404 500等等")
    @Column(name = "http_resp")
    private String httpResp;

    /**
     * 优先级 0:全局优先，1:规则优先
     */
    @ApiModelProperty(value = "优先级")
    @Column(name = "prority")
    private Integer prority;

    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型")
    @Column(name = "resp_status")
    private String resp_status;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    @Column(name = "biz_type")
    private String bizType;

    /**
     * 错误代码
     */
    @ApiModelProperty(value = "错误代码")
    @Column(name = "error_code")
    private String errorCode;

    /**
     * 错误代码
     */
    @ApiModelProperty(value = "错误信息")
    @Column(name = "error_message")
    private String errorMessage;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    /**
     * 错误原因
     */
    @ApiModelProperty(value = "错误原因")
    @Column(name = "rsn_msg")
    private String rsnMsg;
    /**
     * 错误原因
     */
    @ApiModelProperty(value = "错误代码")
    @Column(name = "rsn_code")
    private String rsnCode;

    /**
     *规则名称
     */
    @ApiModelProperty(value = "错误原因")
    @Column(name = "name")
    private String name;

    /**
     *开关
     */
    @ApiModelProperty(value = "开关，0:on 1：off")
    @Column(name = "status")
    private int status;

    /*
     *是否超时  0否 1是
     */
    @ApiModelProperty(value = "是否超时  0否 1是")
    @Column(name = "isTimeOut")
    private Integer isTimeOut;
    /*
     *超时时间
     */
    @ApiModelProperty(value = "超时时间")
    @Column(name = "timeOut")
    private Integer timeOut;

    @ApiModelProperty(value = "callback是否超时  0否 1是")
    @Column(name = "callbackIsTimeOut")
    private Integer callbackIsTimeOut;
    /*
     *超时时间
     */
    @ApiModelProperty(value = "callback超时时间")
    @Column(name = "callbackTimeOut")
    private Integer callbackTimeOut;
    /*
     *优先发送,如果是null则默认优先ack后callback
     */
    @ApiModelProperty(value = "超时时间")
    @Column(name = "sendOrder")
    private String sendOrder;

    @ApiModelProperty(value = "发送callback次数")
    @Column(name = "send_callback_num")
    private Integer sendCallbackNum;

    @ApiModelProperty(value = "发送callback间隔时间")
    @Column(name = "send_interval_time")
    private Integer sendIntervalTime;

    @ApiModelProperty(value = "延迟时间")
    @Column(name = "retry_after")
    private Integer retryAfter;

    @ApiModelProperty(value = "时间格式(Date/Seconds)")
    @Column(name = "format")
    private String format;

    @ApiModelProperty(value = "是否发送camt056,只针对pacs002")
    @Column(name = "send_camt056")
    private String sendCamt056;

    @ApiModelProperty(value = "是否发送admi002,针对所有报文类")
    @Column(name = "send_admi002")
    private String sendAdmi002;
}
