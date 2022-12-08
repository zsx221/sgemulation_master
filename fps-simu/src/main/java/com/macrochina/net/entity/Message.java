package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@org.hibernate.annotations.Table(appliesTo = "message")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Table(name ="message",indexes = {@Index(columnList = "msg_id")})
public class Message extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint")
    private Integer id;

    /**
     * 报文标志
     */
    @Column(name ="tag",columnDefinition = "varchar(64)")
    private String tag;

    /**
    * 报文明细笔数
    */
    @Column(name ="num_msg",columnDefinition = "int ")
    private int numMsg = 1;

    /**
    * 报文类型代码
    */
    @Column(name ="msg_def",nullable = false,columnDefinition = "varchar(128)")
    private String msgDef;

    /**
    * 发起机构
    */
    @Column(name ="from_instn",columnDefinition = "varchar(64)")
    private String fromInstn;

    /**
    * 接收机构
    */
    @Column(name ="to_instn",columnDefinition = "varchar(64)")
    private String toInstn;

    /**
    * 往来标识，0：往账，1：来账
    */
    @Column(name ="dirct",columnDefinition = "int")
    private Integer dirct;

    /**
    * 报文文件
    */
    @Column(name ="msg_file",columnDefinition = "varchar(256)")
    private String msgFile;

    /**
    * org_msg_id
    */
    @Column(name ="org_msg_id",columnDefinition = "varchar(40)")
    private String orgMsgId;

    /**
    * msg_id
    */
    @Column(name ="msg_id",nullable = false,columnDefinition = "varchar(40)")
    private String msgId;

    /**
    * 报文状态，0：已接收，1：已拒绝，2：已超时
    */
    @Column(name ="state",columnDefinition = "int")
    private Integer state = 0;

    /**
     * 错误代码
     */
    @Column(name ="error_code",columnDefinition = "varchar(256)")
    private String errorCode;

    /**
    * 错误原因
    */
    @Column(name ="error_rsn",columnDefinition = "varchar(256)")
    private String errorRsn;

    /**
    * 备注
    */
    @Column(name ="remark",columnDefinition = "text")
    private String remark;

    private String time;

}