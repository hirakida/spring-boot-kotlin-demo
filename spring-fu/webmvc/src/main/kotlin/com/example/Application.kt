package com.example

import org.springframework.boot.WebApplicationType
import org.springframework.boot.logging.LogLevel
import org.springframework.fu.kofu.application
import org.springframework.fu.kofu.webmvc.webMvc

val app = application(WebApplicationType.SERVLET) {
    logging {
        level = LogLevel.INFO
    }
    beans {
        bean<UserHandler>()
    }
    webMvc {
        port = if (profiles.contains("test")) 8181 else 8080
        router {
            val userHandler = ref<UserHandler>()
            GET("/") { ok().body("Hello, Spring Fu!") }
            GET("/users", userHandler::findAll)
            GET("/users/{id}", userHandler::findById)
        }
        converters {
            string()
            jackson()
        }
    }
}

fun main() {
    app.run()
}
