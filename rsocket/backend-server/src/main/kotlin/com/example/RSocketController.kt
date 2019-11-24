package com.example

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.Instant
import java.util.stream.Stream

data class GreetingRequest(val name: String)
data class GreetingResponse(val message: String)

@Controller
class RSocketController {

    @MessageMapping("greetings")
    fun greet(request: GreetingRequest): Flux<GreetingResponse> {
        return Flux.fromStream(Stream.generate { GreetingResponse("${request.name} @ ${Instant.now()}") })
                .delayElements(Duration.ofSeconds(1))
    }
}
