package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name="adminUserHistory")
public class UserHistory implements java.io.Serializable  {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_info_id")
    private Integer userInfoId;

    @Column(name = "user_opn_name")
    private String userOpnName;

    @Column(name = "user_opn_desc")
    private String userOpnDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserOpnName() {
        return userOpnName;
    }

    public void setUserOpnName(String userOpnName) {
        this.userOpnName = userOpnName;
    }

    public String getUserOpnDesc() {
        return userOpnDesc;
    }

    public void setUserOpnDesc(String userOpnDesc) {
        this.userOpnDesc = userOpnDesc;
    }

    @Override
    public String toString() {
        return "AdminUserHistory{" +
                "id=" + id +
                ", userInfoId='" + userInfoId + '\'' +
                ", userOpnName='" + userOpnName + '\'' +
                ", userOpnDesc='" + userOpnDesc + '\'' +
                '}';
    }
}
