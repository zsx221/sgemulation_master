package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@org.hibernate.annotations.Table(appliesTo = "message_pacs")
@Entity(name="message_pacs")
@Data
public class MessagePacs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint")
    private Long id;

    /**
    * 往来标识，0：往账，1：来账
    */
    @Column(name ="dirct",nullable = false,columnDefinition = "int")
    private Integer dirct;

    /**
    * 报文类型代码
    */
    @Column(name ="msg_def",nullable = false,columnDefinition = "varchar(35)")
    private String msgDef;

    /**
    * 发起机构
    */
    @Column(name ="from_instn")
    private String fromInstn;

    /**
    * 接收机构
    */
    @Column(name ="to_instn")
    private String toInstn;

    /**
    * 业务报文id
    */
    @Column(name ="biz_msg_idr",nullable = false,columnDefinition = "varchar(35)")
    private String bizMsgIdr;


    /**
     * 业务服务码
     */
//    @Column(name ="biz_svc",nullable = false,columnDefinition = "varchar(16) COMMENT '业务服务码'")
//    private String bizSvc;

    /**
     * 报文创建时间
     */
    @Column(name ="cre_dt", columnDefinition = "varchar(24) ")
    private String creDt;

    @Column(name ="cre_dt_tm", columnDefinition = "varchar(24)")
    private String creDtTm;

    /**
     * msgid
     */
    @Column(name ="msg_id",nullable = false,columnDefinition = "varchar(35)")
    private String msgId;

    /**
     * 笔数
     */
    @Column(name ="nb_of_txs",nullable = false,columnDefinition = "varchar(16)")
    private String nbOfTxs = "1";

    /**
     * 服务等级
     */
    @Column(name ="svc_lvl",nullable = false,columnDefinition = "varchar(35)")
    private String svcLvl;

    /**
     * 清算方法
     */
    @Column(name ="sttlm_mtd",columnDefinition = "varchar(8)")
    private String sttlmMtd;

    /**
     * 清算系统
     */
    @Column(name ="clr_sys",columnDefinition = "varchar(16)")
    private String clrSys;

    /**
     * 付款标识，发起行生成，唯一
     */
    @Column(name ="instr_id",columnDefinition = "varchar(35)")
    private String instrId;

    /**
     * 端对端id，当pacs004时为原端对端id
     */
    @Column(name ="end_end_id",columnDefinition = "varchar(35)")
    private String endEndId;

    /**
     * 交易id， 当pacs004时为原交易id
     */
    @Column(name ="tx_id",columnDefinition = "varchar(35)")
    private String txId;

    /**
     * 清算系统参考号，pacs004时为原清算参考号
     */
    @Column(name ="clr_sys_ref",columnDefinition = "varchar(35)")
    private String clrSysRef;

    /**
     * 业务类型
     */
    @Column(name ="purp_cd",columnDefinition = "varchar(12)")
    private String purpCd;

    /**
     * 币种， pacs004时为原交易币种
     */
    @Column(name ="ccy",nullable = false,columnDefinition = "varchar(3)")
    private String ccy;

    /**
     * 交易金额，pacs004时为原交易币种
     */
    @Column(name ="sttlm_amt",columnDefinition = "decimal(15,2)")
    private BigDecimal sttlmAmt;

    /**
     * 收费方式
     */
    @Column(name ="chrg_br",columnDefinition = "varchar(4)")
    private String chrgBr;

    /**
     * 付款人名称
     */
    @Column(name ="dbtr_nm",columnDefinition = "varchar(140)")
    private String dbtrNm;

    /**
     * 付款人账号
     */
    @Column(name ="dbtr_acct_id",columnDefinition = "varchar(35)")
    private String dbtrAcctId;

    /**
     * 付款人账户类型
     */
    @Column(name ="dbtr_acct_schme",columnDefinition = "varchar(4)")
    private String dbtrAcctSchme;

    /**
     * 付款方清算机构代码
     */
    @Column(name ="instg_agt_id",columnDefinition = "varchar(35)")
    private String instgAgtId;

    /**
     * 付款机构id
     */
    @Column(name ="dbtr_org_id",columnDefinition = "varchar(35)")
    private String dbtrOrgId;

    /**
     * 付款机构类型
     */
    @Column(name ="dbtr_org_schme",columnDefinition = "varchar(4)")
    private String dbtrOrgSchme;

    /**
     * 最终付款方
     */
    @Column(name ="ultmt_dbtr",columnDefinition = "varchar(140)")
    private String ultmtDbtr;

    /**
     * 收款方清算机构代码
     */
    @Column(name ="instd_agt_id",columnDefinition = "varchar(35)")
    private String instdAgtId;

    /**
     * 收款人名称
     */
    @Column(name ="cdtr_nm",columnDefinition = "varchar(140)")
    private String cdtrNm;

    /**
     * 收款人账号
     */
    @Column(name ="cdtr_acct_id",columnDefinition = "varchar(35)")
    private String cdtrAcctId;

    /**
     * 收款人账户类型
     */
    @Column(name ="cdtr_acct_schme",columnDefinition = "varchar(4)")
    private String cdtrAcctSchme;

    /**
     * 收款机构id
     */
    @Column(name ="cdtr_prvt_id",columnDefinition = "varchar(35)")
    private String cdtrPrvtId;

    /**
     * 收款机构类型
     */
    @Column(name ="cdtr_prvt_schme",columnDefinition = "varchar(4)")
    private String cdtrPrvtSchme;

    /**
     * 最终收款方
     */
    @Column(name ="ultmt_cdtr",columnDefinition = "varchar(140)")
    private String ultmtCdtr;

    /**
     * 结算时间
     */
    @Column(name ="intr_bk_bttlm_dt",columnDefinition = "varchar(24)")
    private String intrBkSttlmDt;


    /**
     * 附言
     */
    @Column(name ="ustrd",columnDefinition = "varchar(254)")
    private String ustrd;

    /**
     * 处理状态：0：成功，1：拒绝，2：超时
     */
    @Column(name ="state",columnDefinition = "int")
    private Integer state;

    /**
     * 超时状态：0：未超时，1：已超时
     */
    @Column(name ="timeout",columnDefinition = "int")
    private Integer timeout;

    /**
     * 状态码：rjct：拒绝，acsc：清算
     */
    @Column(name ="sts_cd",columnDefinition = "varchar(4)")
    private String stsCd;

    /**
     * 错误码
     */
    @Column(name ="sts_rsn_cd",columnDefinition = "varchar(8)")
    private String stsRsnCd;

    /**
     * 备注
     */
    @Column(name ="remark",columnDefinition = "varchar(35)")
    private String remark;

    @Column(name ="dbtr_agt_id",columnDefinition = "varchar(35)")
    private String dbtrAgtId;

    @Column(name ="cdtr_agt_id",columnDefinition = "varchar(35)")
    private String cdtrAgtId;
}