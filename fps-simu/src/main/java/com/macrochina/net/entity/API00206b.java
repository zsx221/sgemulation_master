package com.macrochina.net.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "API00206b", description = "API00206b")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity(name = "api00206b")
@Data
public class API00206b implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint")
    private Integer id;

    @JSONField(name = "ProxyId")
    @JsonProperty("ProxyId")
    @Column(name ="ProxyId",columnDefinition = "varchar(140)")
    private String ProxyId;

    @JSONField(name = "ProxyType")
    @JsonProperty("ProxyType")
    @Column(name ="ProxyType",columnDefinition = "varchar(35)")
    private String ProxyType;

    @JSONField(name = "PrxyRqstrTp")
    @JsonProperty("PrxyRqstrTp")
    @Column(name ="PrxyRqstrTp",columnDefinition = "varchar(35)")
    private String PrxyRqstrTp;


    @JSONField(name = "PrxyRqstrId")
    @JsonProperty("PrxyRqstrId")
    @Column(name ="PrxyRqstrId",columnDefinition = "varchar(35)")
    private String PrxyRqstrId;

    @JSONField(name = "DsplNm")
    @JsonProperty("DsplNm")
    @Column(name ="DsplNm",columnDefinition = "varchar(140)")
    private String DsplNm;

    @JSONField(name = "AcctTp")
    @JsonProperty("AcctTp")
    @Column(name ="AcctTp",columnDefinition = "varchar(35)")
    private String AcctTp;
}
