package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name="address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint")
    private Integer id;

    @Column(name ="msg_def",nullable = false,columnDefinition = "varchar(32)")
    private String msgDef;

    //自动生成
    @Column(name ="msg_id",columnDefinition = "varchar(40) ")
    private String msgId;

    //自动生成
    @Column(name ="cre_dt_tm",columnDefinition = "varchar(32)")
    private String creDtTm;

    @ApiModelProperty(value = "regnSubTp")  //DEAC 禁用
    @Column(name ="regn_sub_tp",columnDefinition = "varchar(32)")
    private String regnSubTp;

    @ApiModelProperty(value = "regnSts")  //regnSts
    @Column(name ="regn_sts",columnDefinition = "varchar(32)")
    private String regnSts;

    @ApiModelProperty(value = "Registration Type")
    @Column(name ="regn_tp",columnDefinition = "varchar(32)")
    private String regnTp;

    @ApiModelProperty(value = "Proxy Type")
    @Column(name ="tp",columnDefinition = "varchar(32) ")
    private String tp;

    @ApiModelProperty(value = "Proxy Vaule")
    @Column(name ="val",columnDefinition = "varchar(32) ")
    private String val;

    @ApiModelProperty(value = "Display Name")
    @Column(name ="dspl_nm",columnDefinition = "varchar(32)")
    private String dsplNm;

    @ApiModelProperty(value = "Participant(BIC)")
    @Column(name ="agt",columnDefinition = "varchar(32)")
    private String agt;

    @ApiModelProperty(value = "bic")
    @Column(name ="bic",columnDefinition = "varchar(32)")
    private String bic;

    @ApiModelProperty(value = "Checking Account")
    @Column(name ="acct_id",columnDefinition = "varchar(32) ")
    private String acctId;

    @ApiModelProperty(value = "acctPrtry")
    @Column(name ="acct_prtry",columnDefinition = "varchar(32)")
    private String acctPrtry;

    //全名
    @ApiModelProperty(value = "acctNm")
    @Column(name ="acct_nm",columnDefinition = "varchar(32)")
    private String acctNm;

    //Account Holder(Org /  CROP)
    @ApiModelProperty(value = "Account Holder")
    @Column(name ="acct_hldr_nm",columnDefinition = "varchar(32)")
    private String acctHldrNm;

    //公司
    @Column(name ="tp_of_org_id",columnDefinition = "varchar(32)")
    private String tpOfOrgId;

    @Column(name ="org_nm",columnDefinition = "varchar(32)")
    private String orgNm;

    @Column(name ="org_regn_dt",columnDefinition = "varchar(32)")
    private String orgRegnDt;

    @Column(name ="tp_of_org_issr",columnDefinition = "varchar(32)")
    private String tpOfOrgIssr;


    //个人
    @Column(name ="gvn_nm",columnDefinition = "varchar(32)")
    private String gvnNm;

    @Column(name ="mddl_nm",columnDefinition = "varchar(32)")
    private String mddlNm;

    @Column(name ="nm",columnDefinition = "varchar(32)")
    private String nm;

    @ApiModelProperty(value = "Customer Id")
    @Column(name ="regn_id",columnDefinition = "varchar(32)")
    private String regnId;

    @ApiModelProperty(value = "Pre Authorised")
    @Column(name ="pre_authrsd",columnDefinition = "varchar(32)")
    private boolean preAuthrsd;

    /**
     * 创建人
     */
    @CreatedBy
    @Column(name ="creater",columnDefinition = "varchar(64)")
    private String creater  = "admin";

    /**
     * 创建时间
     */
    @CreatedDate
    @JsonIgnore
    @Column(name ="created_date",nullable = false)
    private Date createdDate = new Date();

    @Column(name ="status",columnDefinition = "varchar(256)")
    private String status = "ACTC";

    /**
     * 最后修改人
     */
    @LastModifiedBy
    @Column(name ="modifier",columnDefinition = "varchar(64)")
    private String modifier = "admin";

    /**
     * 最后修改时间
     */
    @LastModifiedDate
    @JsonIgnore
    @Column(name ="last_modified_date")
    private Date lastModifiedDate = new Date();
}