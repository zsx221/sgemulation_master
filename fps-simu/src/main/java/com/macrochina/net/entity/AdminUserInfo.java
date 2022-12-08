package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(value="用户登录表单对象",description="用户登录表单对象")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name="adminUserInfo")
public class AdminUserInfo implements java.io.Serializable  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "用户名",required = true,example = "root")
    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_nick_name")
    private String userNickName;

    @Column(name = "user_login_name")
    private String userLoginName;

    @ApiModelProperty(value = "密码",required = true,example = "123456")
    @Column(name = "user_password")
    private String userPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "AdminUserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userLoginName='" + userLoginName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
