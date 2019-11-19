package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.retrieveFlux
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router


data class GreetingRequest(val name: String)
data class GreetingResponse(val message: String)

@SpringBootApplication
class Application {

    @Bean
    fun rSocketClient(rSocketRequester: RSocketRequester.Builder) =
            rSocketRequester.connectTcp("localhost", 7777).block()

    @Bean
    fun route(rSocketClient: RSocketRequester) =
            router {
                GET("/greetings/{name}") {
                    val request = GreetingRequest(it.pathVariable("name"))
                    val greetings = rSocketClient.route("greetings")
                            .data(request)
                            .retrieveFlux<GreetingResponse>()
                    ServerResponse.ok()
                            .contentType(MediaType.TEXT_EVENT_STREAM)
                            .body(greetings)
                }
            }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
