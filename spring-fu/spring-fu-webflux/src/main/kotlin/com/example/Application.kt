package com.example

import org.springframework.boot.WebApplicationType
import org.springframework.boot.logging.LogLevel
import org.springframework.fu.kofu.application
import org.springframework.fu.kofu.webflux.webFlux

val app = application(WebApplicationType.REACTIVE) {
    logging {
        level = LogLevel.INFO
    }
    beans {
        bean<HelloHandler>()
        bean<UserHandler>()
        bean<UserService>()
    }
    webFlux {
        port = if (profiles.contains("test")) 8181 else 8080
        router {
            val helloHandler = ref<HelloHandler>()
            val userHandler = ref<UserHandler>()
            GET("/", helloHandler::hello)
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
