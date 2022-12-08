//package com.macrochina.net.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.annotations.ApiModel;
//import lombok.Data;
//
//import javax.persistence.*;
//
//@ApiModel(value = "API00204Regist", description = "API00204Regist")
//@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
//@Entity(name = "api00204regist")
//@Data
//public class API00206Regist implements java.io.Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @JsonProperty("ClientId")
//    @Column(name ="ClientId",columnDefinition = "varchar(140)")
//    private String ClientId;
//
//    @JsonProperty("CServiceType")
//    @Column(name ="CServiceType",columnDefinition = "varchar(35)")
//    private String CServiceType;
//
//    @JsonProperty("CTxnId")
//    @Column(name ="CTxnId",columnDefinition = "varchar(35)")
//    private String CTxnId;
//
//    @JsonProperty("RptIndc")
//    @Column(name ="RptIndc",columnDefinition = "varchar(35)")
//    private String RptIndc;
//
//    @JsonProperty("CDateTime")
//    @Column(name ="CDateTime",columnDefinition = "varchar(35)")
//    private String CDateTime;
//
//    @Transient
//    private Address address;
//}
