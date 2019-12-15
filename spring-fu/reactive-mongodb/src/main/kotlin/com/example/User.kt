package com.example

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class User(@Id val id: String,
                val name: String,
                val createdAt: LocalDateTime)
