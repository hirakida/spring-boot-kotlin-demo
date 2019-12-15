package com.example

import org.springframework.boot.WebApplicationType
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.logging.LogLevel
import org.springframework.fu.kofu.application
import org.springframework.fu.kofu.redis.reactiveRedis
import org.springframework.fu.kofu.webflux.webFlux

val app = application(WebApplicationType.REACTIVE) {
    logging {
        level = LogLevel.INFO
    }
    beans {
        bean<UserHandler>()
        bean<UserRepository>()
    }
    listener<ApplicationReadyEvent> {
        val userRepository = ref<UserRepository>()
        init(userRepository)
    }
    reactiveRedis {
    }

    webFlux {
        port = if (profiles.contains("test")) 8181 else 8080
        router {
            val userHandler = ref<UserHandler>()
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

private fun init(userRepository: UserRepository) {
    repeat(10) {
        val user = User("key$it", "name$it")
        userRepository.save(user).subscribe()
    }
}
