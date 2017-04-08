package com.example

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Account {
    @Id
    @GeneratedValue
    val id: Int = 0

    @Column(nullable = false)
    var name: String = ""

    @CreatedDate
    @Column(nullable = false, updatable = false)
    val createdAt: Date = Date()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: Date = Date()
}
