package com.example

import org.springframework.boot.WebApplicationType
import org.springframework.fu.kofu.application
import org.springframework.fu.kofu.webflux.webFlux

val app = application(WebApplicationType.REACTIVE) {
    beans {
        bean<RootHandler>()
        bean<UserHandler>()
        bean<UserService>()
    }
    webFlux {
        port = if (profiles.contains("test")) 8181 else 8080
        codecs {
            string()
            jackson()
        }
        router {
            val rootHandler = ref<RootHandler>()
            val userHandler = ref<UserHandler>()
            GET("/", rootHandler::hello)
            GET("/users", userHandler::findAll)
            GET("/users/{id}", userHandler::findById)
        }
    }
}

fun main() {
    app.run()
}
