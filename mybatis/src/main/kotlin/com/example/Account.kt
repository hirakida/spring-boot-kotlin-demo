package com.example

import java.time.LocalDateTime

data class Account(var id: Int = 0,
                   var name: String = "",
                   var createdAt: LocalDateTime = LocalDateTime.MIN,
                   var updatedAt: LocalDateTime = LocalDateTime.MIN)
