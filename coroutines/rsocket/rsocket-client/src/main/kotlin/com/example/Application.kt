package com.example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.connectTcpAndAwait
import org.springframework.messaging.rsocket.retrieveFlow
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.sse

data class GreetingRequest(val name: String)
data class GreetingResponse(val message: String)

@SpringBootApplication
class Application {
    private var requester: RSocketRequester? = null

    @Bean
    fun route(builder: RSocketRequester.Builder) =
        coRouter {
            GET("/greetings/{name}") {
                val request = GreetingRequest(it.pathVariable("name"))
                val greetings: Flow<String> = requester(builder).route("greetings")
                    .data(request)
                    .retrieveFlow<GreetingResponse>()
                    .map { response -> response.message }
                ok().sse().bodyAndAwait(greetings)
            }
        }

    suspend fun requester(builder: RSocketRequester.Builder): RSocketRequester =
        requester ?: builder.connectTcpAndAwait("localhost", 7000).also { requester = it }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
