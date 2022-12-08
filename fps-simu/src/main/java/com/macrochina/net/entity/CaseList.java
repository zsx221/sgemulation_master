package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Case.
 * 中间表
 */
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name="case_list")
public class CaseList  implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     *唯一标识
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    /**
     * Case ID
     */
    @NotNull
    @Column(name="case_id")
    private int caseId;

    /**
     *sample ID
     */
    @NotNull
    @Column(name="sample_id")
    private int sampleId;

    /**
     *sample类型
     *
     * 008、002
     */
    @NotNull
    @Column(name="sample_type")
    private String sampleType;

    /**
     *状态码
     * '处理状态：0：待处理，1：已发送'
     */
    @NotNull
    @Column(name="state")
    private Integer state;

    /**
     *备注
     */
    @Column(name="remark")
    private String remark;



    @JsonIgnore
    @Transient
    private SamplePacs008 samplePacs008;

}
