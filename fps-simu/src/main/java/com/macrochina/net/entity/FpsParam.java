package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@org.hibernate.annotations.Table(appliesTo = "fps_param")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name="fps_param")
public class FpsParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint")
    private Integer id;

    @Column(name ="accept_bank",nullable = false,columnDefinition = "varchar(64)")
    private String acceptBank;

    @Column(name ="send_bank",nullable = false,columnDefinition = "varchar(64)")
    private String sendBank;

    @Column(name ="host_adr",nullable = false,columnDefinition = "varchar(256)")
    private String hostAdr;

    @Column(name ="paynow_host",columnDefinition = "varchar(256)")
    private String paynowHost;

    @Column(name ="is_sign",nullable = false,columnDefinition = "varchar(4)")
    private String isSign;

    @Column(name ="accountValidation",nullable = false,columnDefinition = "varchar(4)")
    private String accountValidation;

    @Column(name ="autoReturnPostingCallback",nullable = false,columnDefinition = "varchar(4) ")
    private String autoReturnPostingCallback;
    @Column(name ="signature_verification" ,columnDefinition = "varchar(4) ")
    private String signatureVerification;
}
