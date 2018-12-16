package com.example.model

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class User(
        val login: String,
        val id: Long,
        val avatarUrl: String,
        val gravatarId: String,
        val url: String,
        val htmlUrl: String,
        val followersUrl: String,
        val followingUrl: String,
        val gistsUrl: String,
        val starredUrl: String,
        val subscriptionsUrl: String,
        val organizationsUrl: String,
        val reposUrl: String,
        val eventsUrl: String,
        val receivedEventsUrl: String,
        val type: String,
        val siteAdmin: Boolean,
        val name: String,
        val company: String?,
        val blog: String,
        val location: String,
        val email: String?,
        val bio: String?,
        val hireable: Boolean?,
        val publicRepos: Long,
        val publicGists: Long,
        val followers: Long,
        val following: Long,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        val totalPrivateRepos: Long,
        val ownedPrivateRepos: Long,
        val privateGists: Long,
        val diskUsage: Long,
        val collaborators: Long,
        val twoFactorAuthentication: Boolean,
        val plan: Plan?
)

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Plan(val name: String,
                val space: Long,
                val privateRepos: Long,
                val collaborators: Long
)
