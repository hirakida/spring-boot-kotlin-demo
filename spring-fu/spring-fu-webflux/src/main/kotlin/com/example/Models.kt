package com.example

import java.time.LocalDateTime

data class User(val id: Int,
                val name: String,
                val createdAt: LocalDateTime)
