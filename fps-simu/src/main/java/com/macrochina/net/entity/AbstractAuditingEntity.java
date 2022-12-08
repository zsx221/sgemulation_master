package com.macrochina.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name ="create_by",columnDefinition = "varchar(35)")
    
    private String createBy = "admin";

    @CreatedDate
    
    @Column(name ="created_date",nullable = false)
    private Date createdDate = new Date();

    @LastModifiedBy
    @Column(name ="update_by",columnDefinition = "varchar(35) ")
    
    private String updateBy = "admin";

    @LastModifiedDate
    @Column(name ="last_modified_date")
    
    private Date lastModifiedDate = new Date();
}
