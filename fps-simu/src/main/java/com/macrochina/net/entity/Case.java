package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 案例集表
 */
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name="cases")
public class Case implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     *唯一标识
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;



    /*
     *案例集名称
     * Case Name
     */
    @Column(name="name")
    private String name;

    /*
     *案例集发送次数
     * Sending Counts
     */
    @NotNull
    @Column(name="count")
    private Integer count;

    /*
     *时间（忽略）
     */
    @NotNull
    @Column(name="times")
    private String times;

    /**
     *状态码
     * '处理状态：0：待处理，1：已发送'
     */
    @NotNull
    @Column(name="state")
    private Integer state;
    /**
     *备注
     * Description
     */
    @Column(name="remark")
    private String remark;


    @Transient
    @JsonIgnore
    private List<CaseList> caseLists = new ArrayList<>();

}
