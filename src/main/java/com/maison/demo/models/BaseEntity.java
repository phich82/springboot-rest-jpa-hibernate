package com.maison.demo.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // auditing
public class BaseEntity {

    @CreatedDate // auditing: automatically insert
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy // auditing: automatically insert
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate // auditing: automatically insert
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy // auditing: automatically insert
    @Column(insertable = false)
    private String updatedBy;
}
