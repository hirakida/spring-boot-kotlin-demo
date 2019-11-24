package com.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

data class User(val id: Int, val message: String)
data class Message(val message: String)

@SpringBootApplication
class Application {

    @Bean
    fun routes() = coRouter {
        GET("/users", ::findAll)
        GET("/users/{id}", ::findOne)
    }

    suspend fun findAll(request: ServerRequest): ServerResponse {
        delay(1000)
        return ok().bodyAndAwait(
                flowOf(User(1, "name1"),
                        User(2, "name2"),
                        User(3, "name3")))
    }

    suspend fun findOne(request: ServerRequest): ServerResponse {
        delay(1000)
        val id = request.pathVariable("id").toInt()
        return ok().bodyValueAndAwait(User(id, "name$id"))
    }
}

@RestController
class MessageController {

    @GetMapping("/messages")
    suspend fun findAll(): Flow<Message> {
        delay(1000)
        return flowOf(Message("message1"), Message("message2"), Message("message3"))
    }

    @GetMapping("/messages/{id}")
    suspend fun findOne(@PathVariable id: Int): Message {
        delay(1000)
        return Message("message${id}")
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
