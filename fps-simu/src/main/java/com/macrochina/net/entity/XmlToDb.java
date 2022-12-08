package com.macrochina.net.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name="xmlToDb")
public class XmlToDb implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "srcClassName")
    private String srcClassName;
    @Column(name = "srcField")
    private String srcField;
    @Column(name = "targetClassName")
    private String targetClassName;
    @Column(name = "targetClassField")
    private String targetClassField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrcClassName() {
        return srcClassName;
    }

    public void setSrcClassName(String srcClassName) {
        this.srcClassName = srcClassName;
    }

    public String getSrcField() {
        return srcField;
    }

    public void setSrcField(String srcField) {
        this.srcField = srcField;
    }

    public String getTargetClassName() {
        return targetClassName;
    }

    public void setTargetClassName(String targetClassName) {
        this.targetClassName = targetClassName;
    }

    public String getTargetClassField() {
        return targetClassField;
    }

    public void setTargetClassField(String targetClassField) {
        this.targetClassField = targetClassField;
    }
}
