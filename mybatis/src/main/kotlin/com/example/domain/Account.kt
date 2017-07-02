package com.example.domain

import java.sql.Timestamp

data class Account(var id: Int,
                   var name: String,
                   var createdAt: Timestamp,
                   var updatedAt: Timestamp)
