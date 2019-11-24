package com.example

import kotlinx.coroutines.delay
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@SpringBootApplication
class Application {

    @Bean
    fun routes(handler: HelloHandler) = coRouter {
        GET("/hello", handler::hello)
    }
}

@Component
class HelloHandler {
    suspend fun hello(request: ServerRequest): ServerResponse {
        delay(1000)
        return ok().bodyValueAndAwait("Hello!")
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
