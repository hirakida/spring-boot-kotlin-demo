package com.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.coRouter

@SpringBootApplication
class Application {
    @Bean
    fun routes(userHandler: UserHandler) = coRouter {
        "/users".nest {
            GET("/", userHandler::findAll)
            GET("/{id}", userHandler::findOne)
        }
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
