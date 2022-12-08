package com.macrochina.net.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "API00206a", description = "API00206a")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity(name = "api00206a")
@Data
public class API00206a implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint ")
    private Integer id;

    @JsonProperty("RegnTp")
    @JSONField(name = "RegnTp")
    @Column(name ="RegnTp",columnDefinition = "varchar(35)")
    private String RegnTp;

    @JsonProperty("RegnSubTp")
    @JSONField(name = "RegnSubTp")
    @Column(name ="RegnSubTp",columnDefinition = "varchar(35)")
    private String RegnSubTp;

    @JsonProperty("ProxyId")
    @JSONField(name = "ProxyId")
    @Column(name ="ProxyId",columnDefinition = "varchar(140)")
    private String ProxyId;

    @JsonProperty("ProxyType")
    @JSONField(name = "ProxyType")
    @Column(name ="ProxyType",columnDefinition = "varchar(35)")
    private String ProxyType;

    @JsonProperty("RegnId")
    @JSONField(name = "RegnId")
    @Column(name ="RegnId",columnDefinition = "varchar(35)")
    private String RegnId;

    @JsonProperty("DsplNm")
    @JSONField(name = "DsplNm")
    @Column(name ="DsplNm",columnDefinition = "varchar(140)")
    private String DsplNm;

    @JsonProperty("AcctID")
    @JSONField(name = "AcctID")
    @Column(name ="AcctID",columnDefinition = "varchar(34)")
    private String AcctID;

    @JsonProperty("AcctType")
    @JSONField(name = "AcctType")
    @Column(name ="AcctType",columnDefinition = "varchar(35)")
    private String AcctType;

    @JsonProperty("AcctName")
    @JSONField(name = "AcctName")
    @Column(name ="AcctName",columnDefinition = "varchar(140)")
    private String AcctName;

    @JsonProperty("AcctHldrType")
    @JSONField(name = "AcctHldrType")
    @Column(name ="AcctHldrType",columnDefinition = "varchar(35)")
    private String AcctHldrType;

    @JsonProperty("PreAuthrsd")
    @JSONField(name = "PreAuthrsd")
    @Column(name ="PreAuthrsd", length = 2)
    private Boolean PreAuthrsd;

    @JsonProperty("OrgName")
    @JSONField(name = "OrgName")
    @Column(name ="OrgName",columnDefinition = "varchar(35)")
    private String OrgName;

    @JsonProperty("RegnDt")
    @JSONField(name = "RegnDt")
    @Column(name ="RegnDt",columnDefinition = "varchar(35)")
    private String RegnDt;

    @JsonProperty("TpOfOrg")
    @JSONField(name = "TpOfOrg")
    @Column(name ="TpOfOrg",columnDefinition = "varchar(35)")
    private String TpOfOrg;

    @JsonProperty("GvnNm")
    @JSONField(name = "GvnNm")
    @Column(name ="GvnNm",columnDefinition = "varchar(140)")
    private String GvnNm;

    @JsonProperty("MddlNm")
    @JSONField(name = "MddlNm")
    @Column(name ="MddlNm",columnDefinition = "varchar(140)")
    private String MddlNm;

    @JsonProperty("LastNm")
    @JSONField(name = "LastNm")
    @Column(name ="LastNm",columnDefinition = "varchar(140)")
    private String LastNm;

}
