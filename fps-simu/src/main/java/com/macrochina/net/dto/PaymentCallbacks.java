package com.macrochina.net.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentCallbacks {
    @JsonProperty("ChTxnId")
    private String  ChTxnId;
    @JsonProperty("EndToEndID")
    private String  EndToEndID;
    @JsonProperty("FPSID")
    private String  FPSID;
    @JsonProperty("Service")
    private String  Service;
    @JsonProperty("Fee")
    private String  Fee;
    @JsonProperty("FeeCurrency")
    private String  FeeCurrency;
    @JsonProperty("Amount")
    private String  Amount;
    @JsonProperty("AmountCurrency")
    private String AmountCurrency ;
    @JsonProperty("PaymentType")
    private String  PaymentType;
    @JsonProperty("DCustName")
    private String  DCustName;
    @JsonProperty("DCustID")
    private String  DCustID;
    @JsonProperty("DAccID")
    private String  DAccID;
    @JsonProperty("DAccIDType")
    private String  DAccIDType;
    @JsonProperty("DAgtCode")
    private String  DAgtCode;
    @JsonProperty("CCustName")
    private String  CCustName;
    @JsonProperty("CCustID")
    private String  CCustID;
    @JsonProperty("CAccID")
    private String CAccID ;
    @JsonProperty("CAccIDType")
    private String  CAccIDType;
    @JsonProperty("CAgtCode")
    private String  CAgtCode;
    @JsonProperty("RemittanceRef")
    private String  RemittanceRef;
    @JsonProperty("StmtDate")
    private String  StmtDate;
    @JsonProperty("ReturnCode")
    private String  ReturnCode;
    @JsonProperty("ReturnMsg")
    private String  ReturnMsg;
    @JsonProperty("ReturnCodeBCS")
    private String  ReturnCodeBCS;
    @JsonProperty("AddtlInf")
    private String  AddtlInf;
    @JsonProperty("TxnStatus")
    private String  TxnStatus;
    @JsonProperty("PayPurp")
    private String  PayPurp;
    @JsonProperty("Destination")
    private String  Destination;

    //713 新增
    @JsonProperty("BcsTransactionId")
    private String bcsTransactionId;
    @JsonProperty("BcsInstructionId")
    private String bcsInstructionId;
}
