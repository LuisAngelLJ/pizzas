package com.la.pizzeria.persistence.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass//esta clase puede ser heredada o extendedida a partir de otros entitys
public class AuditableEntity {
    @Column(name = "created_date")
    @CreatedDate//para que funcione la auditoria
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate//para que funcione la auditoria
    private LocalDateTime modifiedDate;
}
