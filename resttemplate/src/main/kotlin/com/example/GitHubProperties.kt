package com.example

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotNull

@Component
@ConfigurationProperties(prefix = "github")
@Validated
class GitHubProperties {
    @NotNull
    var userScope: UserScope = UserScope()

    class UserScope {
        @NotNull
        lateinit var token: String
    }
}
