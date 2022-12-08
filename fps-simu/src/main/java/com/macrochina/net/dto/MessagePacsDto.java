package com.macrochina.net.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MessagePacsDto {
    @ApiModelProperty(value = "Transactions")
    private String biz_msg_idr;
    @ApiModelProperty(value = "Status 0：成功，1：拒绝，2：超时")
    private String state;
    @ApiModelProperty(value = "Date Time")
    private String cre_dt;
    @ApiModelProperty(value = "Debtor Name")
    private String dbtr_nm;
    @ApiModelProperty(value = "Creditor Name")
    private String cdtr_nm;
    @ApiModelProperty(value = "Currency")
    private String ccy;

}
