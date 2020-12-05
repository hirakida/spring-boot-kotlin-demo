package com.example

import org.springframework.http.MediaType
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

data class GreetingRequest(val name: String)
data class GreetingResponse(val message: String)

@RestController
class RSocketController(rSocketRequester: RSocketRequester.Builder) {
    private val requester: RSocketRequester = rSocketRequester.tcp("localhost", 7777)

    @GetMapping("/greetings/{name}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun greetings(@PathVariable name: String): Flux<GreetingResponse> {
        return requester.route("greetings")
            .data(GreetingRequest(name))
            .retrieveFlux(GreetingResponse::class.java)
            .delayElements(Duration.ofSeconds(1))
    }
}
