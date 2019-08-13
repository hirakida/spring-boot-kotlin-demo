package com.example.model

data class Email(
        val email: String,
        val verified: Boolean,
        val primary: Boolean,
        val visibility: String?
)
