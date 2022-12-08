package com.macrochina.net.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "API00206c", description = "API00206c")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity(name = "api00206c")
@Data
public class API00206c implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint")
    private Integer id;

    @JSONField(name = "EnquiryChoice")
    @JsonProperty("EnquiryChoice")
    @Column(name ="EnquiryChoice",columnDefinition = "varchar(35)")
    private String EnquiryChoice;

    @JSONField(name = "RegnId")
    @JsonProperty("RegnId")
    @Column(name ="RegnId",columnDefinition = "varchar(35)")
    private String RegnId;

    @JSONField(name = "ProxyId")
    @JsonProperty("ProxyId")
    @Column(name ="ProxyId",columnDefinition = "varchar(140)")
    private String ProxyId;

    @JSONField(name = "ProxyType")
    @JsonProperty("ProxyType")
    @Column(name ="ProxyType",columnDefinition = "varchar(35)")
    private String ProxyType;

    @JSONField(name = "RegnSts")
    @JsonProperty("RegnSts")
    @Column(name ="RegnSts",columnDefinition = "varchar(35)")
    private String RegnSts;

    @JSONField(name = "NbOfItm")
    @JsonProperty("NbOfItm")
    @Column(name ="NbOfItm")
    private String NbOfItm;

    @JSONField(name = "AgentBIC")
    @JsonProperty("AgentBIC")
    @Column(name ="AgentBIC",columnDefinition = "varchar(35)")
    private String AgentBIC;

    @JSONField(name = "AcctId")
    @JsonProperty("AcctId")
    @Column(name ="AcctId",columnDefinition = "varchar(35)")
    private String AcctId;

    @JSONField(name = "AcctTp")
    @JsonProperty("AcctTp")
    @Column(name ="AcctTp",columnDefinition = "varchar(35)")
    private String AcctTp;
}
