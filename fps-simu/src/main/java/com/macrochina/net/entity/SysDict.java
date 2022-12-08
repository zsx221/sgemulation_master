package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Table(name ="sys_dict",indexes = {@Index(columnList = "biz_group")})
public class SysDict implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint ")
    private int id;

    @Column(name ="biz_group",nullable = false,columnDefinition = "varchar(64) ")
    private String bizGroup;

    @Column(name ="biz_code",nullable = false,columnDefinition = "varchar(64) ")
    private String bizCode;

    @Column(name ="biz_value",nullable = false,columnDefinition = "varchar(256)")
    private String bizValue;

    @CreatedBy
    @Column(name ="create_by",columnDefinition = "varchar(35)")
    private String createBy = "admin";

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name ="created_date")
    private Date createdDate = new Date();

    @LastModifiedBy
    @Column(name ="update_by",columnDefinition = "varchar(35)")
    private String updateBy = "admin";

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name ="last_modified_date")
    private Date lastModifiedDate = new Date();
}
