package com.bifos.piudbbackend.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected val id : Long? = null

    @CreatedDate
    protected val createdDate: LocalDateTime? = null

    @LastModifiedDate
    protected val lastModifiedDate: LocalDateTime? = null
}