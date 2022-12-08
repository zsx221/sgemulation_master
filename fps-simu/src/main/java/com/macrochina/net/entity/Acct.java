package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name="acct")
public class Acct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint ")
    private Integer id;

    /**
     * 组织
     */
    @Column(name ="org",columnDefinition = "varchar(64) default ''")
    private String org;

    /**
    * 组织类型
    */
    @Column(name ="org_tp",columnDefinition = "varchar(64)")
    private String orgTp;

    /**
     * 组织名称
     */
    @Column(name ="org_nm",columnDefinition = "varchar(64) ")
    private String orgNm;

    /**
    * participant（bic）
    */
    @Column(name ="bic",columnDefinition = "varchar(64)")
    private String bic;

    /**
    * 账户名
    */
    @Column(name ="acct_nm",columnDefinition = "varchar(64)")
    private String acctNm;

    /**
    * 显示名称
    */
    @Column(name ="dspl_nm",columnDefinition = "varchar(64) ")
    private String dsplNm;

    /**
    * 账户检查
    */
    @Column(name ="bank_acct",columnDefinition = "varchar(64)")
    private String bankAcct;

    /**
    * 专有的
    */
    @Column(name ="pty",columnDefinition = "varchar(64) ")
    private String pty;

    /**
    * 发行人
    */
    @Column(name ="issr",columnDefinition = "varchar(64) ")
    private String issr;

    /**
     * gvn_nm
     */
    @Column(name ="gvn_nm",columnDefinition = "varchar(64)")
    private String gvnNm;

    /**
     * md_nm
     */
    @Column(name ="md_nm",columnDefinition = "varchar(64) ")
    private String mdNm;

    /**
     * lt_nm
     */
    @Column(name ="lt_nm",columnDefinition = "varchar(64)")
    private String ltNm;

    /**
     * nrig
     */
    @Column(name ="nrig",columnDefinition = "varchar(64)")
    private String nrig;

    /**
     * 手机号
     */
    @Column(name ="msisdn",columnDefinition = "varchar(64)")
    private String msisdn;

    /**
     * 公司代理
     */
    @Column(name ="crop_proxy",columnDefinition = "varchar(64)")
    private String cropProxy;

    /**
     * 唯一实体编号
     */
    @Column(name ="uen",columnDefinition = "varchar(64) ")
    private String uen;

    /**
     * 虚拟支付地址
     */
    @Column(name ="vpa",columnDefinition = "varchar(64)")
    private String vpa;

    /**
    * 状态（0、正常 1、冻结）
    */
    @Column(name ="state",columnDefinition = "varchar(2) ")
    private String state;
    /**
    * 状态（0、正常 1、冻结）
    */
    @Column(name ="phx_card",columnDefinition = "varchar(64)")
    private String phxCard;

    /**
    * 备注
    */
    @Column(name ="remark",columnDefinition = "varchar(64)")
    private String remark;

    @Column(name ="accountid_type",columnDefinition = "varchar(12)")
    private String accountIdType;

}