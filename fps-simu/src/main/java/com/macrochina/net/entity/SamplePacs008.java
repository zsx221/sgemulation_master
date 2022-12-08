package com.macrochina.net.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @description sample_pacs008
 * @date 2021-07-31
 */
@Data
//Table(name="sample_pacs008")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name = "sample_pacs008")
public class SamplePacs008 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    /**
     *
     * instgagtmmbid
     *
     *Originating Bank BIC
     */
    @ApiModelProperty(value = "Originating Bank BIC")
    @Column(name = "instgagtmmbid")
    private String instgagtmmbid;

    /**
     *
     * instdagtmmbid
     *
     *Receiving Bank BIC
     */
    @ApiModelProperty(value = "Receiving Bank BIC")
    @Column(name = "instdagtmmbid")
    private String instdagtmmbid;

    /**
     * grphdrccy
     * Currency
     */
    @ApiModelProperty(value = "Currency")
    @Column(name = "grphdrccy")
    private String grphdrccy;

    /**
     * ttlintrbksttlmamt
     *
     * Total Amount
     */
    @ApiModelProperty(value = "Total Amount")
    @Column(name = "ttlintrbksttlmamt")
    private BigDecimal ttlintrbksttlmamt;

    /**
     * endtoend
     * End to End Identification
     */
    @ApiModelProperty(value = "End to End")
    @Column(name = "endtoend")
    private String endtoend;

    /**
     * txid
     * Transaction Identification
     */
    @ApiModelProperty(value = "Transaction")
    @Column(name = "txid")
    private String txid;

    /**
     * svclvl
     * Service Level
     */
    @ApiModelProperty(value = "Service Level")
    @Column(name = "svclvl")
    private String svclvl;

    /**
     * chrgbr
     * Charge Bearer
     */
    @ApiModelProperty(value = "Charge Bearer")
    @Column(name = "chrgbr")
    private String chrgbr;

    /**
     * dbtrnm
     * Debtor Account Name
     */
    @ApiModelProperty(value = "Debtor Account Name")
    @Column(name = "dbtrnm")
    private String dbtrnm;

    /**
     * dbtracct
     *Debtor Account Number
     */
    @ApiModelProperty(value = "Debtor Account Number")
    @Column(name = "dbtracct")
    private String dbtracct;

    /**
     * dbtrclrsysmmbid
     * Debtor Bank BIC
     */
    @ApiModelProperty(value = "Debtor Bank BIC")
    @Column(name = "dbtrclrsysmmbid")
    private String dbtrclrsysmmbid;

    /**
     * cdtrnm
     * Creditor Account Name
     */
    @ApiModelProperty(value = "Creditor Account Name")
    @Column(name = "cdtrnm")
    private String cdtrnm;

    /**
     * cdtracct
     * Creditor Account Number
     */
    @ApiModelProperty(value = "Creditor Account Number")
    @Column(name = "cdtracct")
    private String cdtracct;

    /**
     * cdtrclrsysmmbid
     * Creditor Bank BIC
     */
    @ApiModelProperty(value = "Creditor Bank BIC")
    @Column(name = "cdtrclrsysmmbid")
    private String cdtrclrsysmmbid;


    /**
     * ultmtcdtrnm
     * Ultimate Creditor

     */
    @ApiModelProperty(value = "Ultimate Creditor")
    @Column(name = "ultmtcdtrnm")
    private String ultmtcdtrnm;

    /**
     * ultmtdbtrnm
     * Ultimate Debtor
     */
    @ApiModelProperty(value = "Ultimate Debtor")
    @Column(name = "ultmtdbtrnm")
    private String ultmtdbtrnm;

    /*
    * RmtInf
    *Remittance Information
    * */
    @ApiModelProperty(value = "Remittance Information")
    @Column(name = "rmtinf")
    private  String rmtinf;
    /*
     * RmtInf
     *Purpose
     * */
    @ApiModelProperty(value = "Purpose")
    @Column(name = "purp")
    private  String purp;

    /*
     * createtime
     *Creation Date Time
     * */
    @ApiModelProperty(value = "Creation Date Time")
    @Column(name = "createtime")
    private  String createtime;

    @ApiModelProperty(value = "是否超时")
    @Column(name = "is_timeout" , columnDefinition="varchar(2)")
    private String isTimeOut = "0";

    public SamplePacs008() {
    }
}

