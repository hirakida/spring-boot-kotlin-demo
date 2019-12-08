package com.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

data class User(val id: Int, val message: String)

@SpringBootApplication
class Application {

    @Bean
    fun routes() = coRouter {
        "/users".nest {
            GET("/", ::findAll)
            GET("/{id}", ::findOne)
        }
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

fun main(args: Array<String>) {
    runApplication<Application>(*args) {
        val context = beans {
            bean {
                ApplicationListener<ApplicationReadyEvent> {
                    runBlocking {
                        delay(1000)
                        println("ApplicationReadyEvent")
                    }
                }
            }
        }
        addInitializers(context)
    }
}
