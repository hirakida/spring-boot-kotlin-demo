package com.example.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Account(
        @Id
        @GeneratedValue
        var id: Int = 0,

        @Column(nullable = false)
        var name: String = "",

        @CreatedDate
        @Column(nullable = false, updatable = false)
        var createdAt: LocalDateTime = LocalDateTime.MIN,

        @LastModifiedDate
        @Column(nullable = false)
        var updatedAt: LocalDateTime = LocalDateTime.MIN
)
