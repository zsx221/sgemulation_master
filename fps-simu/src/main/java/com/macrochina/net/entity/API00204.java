package com.macrochina.net.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "API00204", description = "API00204")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity(name = "api00204")
@Data
public class API00204 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /*
     *唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("ChTxnId")
    @JSONField(name = "ChTxnId")
    @Column(name ="ChTxnId",columnDefinition = "varchar(35) ")
    private String ChTxnId;

    @JsonProperty("EndToEndID")
    @JSONField(name = "EndToEndID")
    @Column(name ="EndToEndID",columnDefinition = "varchar(35) ")
    private String EndToEndID;

    @JsonProperty("MandateID")
    @JSONField(name = "MandateID")
    @Column(name ="MandateID",columnDefinition = "varchar(35) ")
    private String MandateID;

    @JsonProperty("Service")
    @JSONField(name = "Service")
    @Column(name ="Service")
    private String Service;

    @JsonProperty("Fee")
    @JSONField(name = "Fee")
    @Column(name ="Fee")
    private String Fee;

    @JsonProperty("FeeCurrency")
    @JSONField(name = "FeeCurrency")
    @Column(name ="FeeCurrency")
    private String FeeCurrency;

    @JsonProperty("Amount")
    @JSONField(name = "Amount")
    @Column(name ="Amount")
    private String Amount;

    @JsonProperty("AmountCurrency")
    @JSONField(name = "AmountCurrency")
    @Column(name ="AmountCurrency")
    private String AmountCurrency;

    @JsonProperty("PaymentType")
    @JSONField(name = "PaymentType")
    @Column(name ="PaymentType")
    private String PaymentType;

    @JsonProperty("StmtDate")
    @JSONField(name = "StmtDate")
    @Column(name ="StmtDate")
    private String StmtDate;

    @JsonProperty("DCustName")
    @JSONField(name = "DCustName")
    @Column(name ="DCustName",columnDefinition = "varchar(140) ")
    private String DCustName;

    @JsonProperty("DCustID")
    @JSONField(name = "DCustID")
    @Column(name ="DCustID",columnDefinition = "varchar(34) ")
    private String DCustID;

    @JsonProperty("DAccID")
    @JSONField(name = "DAccID")
    @Column(name ="DAccID",columnDefinition = "varchar(34) ")
    private String DAccID;

    @JsonProperty("DAccIDType")
    @JSONField(name = "DAccIDType")
    @Column(name ="DAccIDType")
    private String DAccIDType;

    @JsonProperty("DAgtCode")
    @JSONField(name = "DAgtCode")
    @Column(name ="DAgtCode",columnDefinition = "varchar(11) ")
    private String DAgtCode;

    @JsonProperty("CCustName")
    @JSONField(name = "CCustName")
    @Column(name ="CCustName",columnDefinition = "varchar(140) ")
    private String CCustName;

    @JsonProperty("CCustID")
    @JSONField(name = "CCustID")
    @Column(name ="CCustID")
    private String CCustID;

    @JsonProperty("CAccID")
    @JSONField(name = "CAccID")
    @Column(name ="CAccID",columnDefinition = "varchar(34) ")
    private String CAccID;

    @JsonProperty("CAccIDType")
    @JSONField(name = "CAccIDType")
    @Column(name ="CAccIDType")
    private String CAccIDType;

    @JsonProperty("CAgtCode")
    @JSONField(name = "CAgtCode")
    @Column(name ="CAgtCode")
    private String CAgtCode;

    @JsonProperty("PayPurp")
    @JSONField(name = "PayPurp")
    @Column(name ="PayPurp")
    private String PayPurp;

    @JsonProperty("RemittanceRef")
    @JSONField(name = "RemittanceRef")
    @Column(name ="RemittanceRef",columnDefinition = "varchar(140) ")
    private String RemittanceRef;

    @JsonProperty("LookupRef")
    @JSONField(name = "LookupRef")
    @Column(name ="LookupRef",columnDefinition = "varchar(35) ")
    private String LookupRef;
    @JsonProperty("RptIndc")
    @JSONField(name = "RptIndc")
    @Column(name ="RptIndc")
    private Boolean RptIndc;
}
