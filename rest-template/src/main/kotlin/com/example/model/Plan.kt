package com.example.model

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Plan(val name: String,
                val space: Long,
                val privateRepos: Long,
                val collaborators: Long
)
