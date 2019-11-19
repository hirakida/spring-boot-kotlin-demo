package com.example

import java.time.LocalDateTime

data class User(
        var id: Int,
        var name: String,
        var createdAt: LocalDateTime
)
