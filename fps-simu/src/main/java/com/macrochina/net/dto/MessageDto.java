package com.macrochina.net.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.Date;

@Data
public class MessageDto {
      @ApiModelProperty(value = "Transactions")
      private String msg_def;
      @ApiModelProperty(value = "Dirction 0：往账，1：来账")
      private String dirct;
      @ApiModelProperty(value = "Status 0：已接收，1：已拒绝，2：已超时")
      private String state;
      @ApiModelProperty(value = "Date Time")

      private String created_date;
      @ApiModelProperty(value = "Bank")
      private String to_instn;
}
