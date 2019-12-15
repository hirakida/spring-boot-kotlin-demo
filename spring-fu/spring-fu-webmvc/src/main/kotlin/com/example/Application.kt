package com.example

import org.springframework.boot.WebApplicationType
import org.springframework.boot.logging.LogLevel
import org.springframework.fu.kofu.application
import org.springframework.fu.kofu.webmvc.webMvc
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.ok

val app = application(WebApplicationType.SERVLET) {
    logging {
        level = LogLevel.INFO
    }
    beans {
        bean<HelloHandler>()
        bean<UserHandler>()
    }
    webMvc {
        port = if (profiles.contains("test")) 8181 else 8080
        router {
            val helloHandler = ref<HelloHandler>()
            val userHandler = ref<UserHandler>()
            GET("/", helloHandler::hello)
            GET("/users", userHandler::findAll)
        }
        converters {
            string()
            jackson()
        }
    }
}

data class User(val id: Int, val name: String)

@Suppress("UNUSED_PARAMETER")
class HelloHandler {
    fun hello(request: ServerRequest) = ok().body("Hello, Spring Fu!")
}

@Suppress("UNUSED_PARAMETER")
class UserHandler {
    fun findAll(request: ServerRequest): ServerResponse {
        val users = (1..5).map { User(it, "name$it") }
        return ok().body(users)
    }
}

fun main() {
    app.run()
}
