package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.router

@SpringBootApplication
class Application {
    @Bean
    fun routes(handler: UserHandler) = router {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/users", handler::findAll)
            GET("/users/{id}", handler::findById)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
