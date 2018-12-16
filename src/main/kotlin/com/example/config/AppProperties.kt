package com.example.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app")
data class AppProperties(
        var userScope: UserScope = UserScope()
)

data class UserScope(
        var token: String = ""
)
