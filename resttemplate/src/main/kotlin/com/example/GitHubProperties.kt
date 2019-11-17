package com.example

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "github")
data class GitHubProperties(val userScope: UserScope) {

    data class UserScope(val token: String)
}
