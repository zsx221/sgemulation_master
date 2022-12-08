package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name="participant")
@org.hibernate.annotations.Table(appliesTo = "participant")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Participant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint")
    private Integer id;

    /**
    * 机构名称
    */
    @Column(name ="part",columnDefinition = "varchar(64)")
    private String part;

    /**
    * bic
    */
    @Column(name ="bic",columnDefinition = "varchar(64)")
    private String bic;

    /**
    * 机构名称
    */
    @Column(name ="bank_name",columnDefinition = "varchar(64)")
    private String bankName;

    /**
     * 默认
     */
    @Column(name ="deflt",columnDefinition = "varchar(64)")
    private String deflt;

    /**
     * 签名
     */
    @Column(name ="sign_on",columnDefinition = "varchar(64)")
    private String signOn;

    /**
     * 有用（效）性
     */
    @Column(name ="availability",columnDefinition = "varchar(64)")
    private String availability;

    /**
    * 状态（0、正常 1、冻结）
    */
    @Column(name ="state",columnDefinition = "varchar(2)")
    private String state;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name ="created_date",nullable = false)
    private Date createdDate = new Date();
}