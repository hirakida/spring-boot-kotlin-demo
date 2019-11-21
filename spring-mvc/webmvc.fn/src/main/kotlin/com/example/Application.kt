package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
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

@Component
class UserHandler(private val userRepository: UserRepository) {

    fun findAll(request: ServerRequest): ServerResponse =
            ServerResponse.ok().body(userRepository.findAll())

    fun findById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().body(userRepository.findById(id))
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
