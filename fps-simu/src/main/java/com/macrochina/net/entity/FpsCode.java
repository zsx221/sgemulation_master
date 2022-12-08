package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@org.hibernate.annotations.Table(appliesTo = "fps_code")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name="fps_code")
public class FpsCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint ")
    private Integer id;

    @Column(name ="rsn_code",columnDefinition = "varchar(64)")
    private String rsnCode;

    @Column(name ="des",columnDefinition = "varchar(254)")
    private String des;

    @Column(name ="cpi",columnDefinition = "varchar(64)")
    private String cpi;

    @Column(name ="rsn_nm",columnDefinition = "varchar(64)")
    private String rsnNm;

    @Column(name ="code")
    private String code;
}