package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Table(name ="biz_rule_data",indexes = {@Index(columnList = "rid"),@Index(columnList = "class_name")})
public class BizRuleData {
    private static final long serialVersionUID = 1L;

    /*
     *唯一标识
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint")
    private Integer id;

    /**
     * 操作符 = > < in
     */
    @ApiModelProperty(value = "操作符 = > < in")
    @Column(name = "operator")
    private String operator;

    /**
     * 规则id
     */
    @ApiModelProperty(value = "规则id")
    @Column(name = "rid")
    private int rid;

    /**
     * 字段属性
     */
    @ApiModelProperty(value = "字段属性")
    @Column(name = "field")
    private String field;

    /**
     * class全称
     */
    @ApiModelProperty(value = "Class全称")
    @Column(name = "class_name")
    private String className;

    /**
     * class值
     */
    @ApiModelProperty(value = "Class值")
    @Column(name = "class_value")
    private String classValue;

    /**
     * 备注
     *
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    public BizRuleData() {}
}
