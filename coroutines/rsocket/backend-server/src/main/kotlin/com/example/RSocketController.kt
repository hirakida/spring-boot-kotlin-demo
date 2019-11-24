package com.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import java.time.Instant

data class GreetingRequest(val name: String)
data class GreetingResponse(val message: String)

@Controller
class RSocketController {

    @MessageMapping("greetings")
    fun greet(request: GreetingRequest) = flow {
        while (true) {
            emit(GreetingResponse("${request.name} @ ${Instant.now()}"))
            delay(1000)
        }
    }
}
