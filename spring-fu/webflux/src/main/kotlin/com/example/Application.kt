package com.example

import org.springframework.boot.WebApplicationType
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.logging.LogLevel
import org.springframework.fu.kofu.application
import org.springframework.fu.kofu.webflux.webFlux
import java.time.LocalDateTime

val app = application(WebApplicationType.REACTIVE) {
    logging {
        level = LogLevel.INFO
    }
    beans {
        bean<UserHandler>()
        bean<UserService>()
    }
    listener<ApplicationReadyEvent> {
        val userService = ref<UserService>()
        init(userService)
    }

    webFlux {
        port = if (profiles.contains("test")) 8181 else 8080
        router {
            val userHandler = ref<UserHandler>()
            GET("/") { ok().bodyValue("Hello, Spring Fu!") }
            GET("/users", userHandler::findAll)
            GET("/users/{id}", userHandler::findById)
        }
        codecs {
            string()
            jackson()
        }
    }
}

fun main() {
    app.run()
}

private fun init(userService: UserService) {
    repeat(10) {
        val user = User(it, "name$it", LocalDateTime.now())
        userService.save(user)
    }
}
