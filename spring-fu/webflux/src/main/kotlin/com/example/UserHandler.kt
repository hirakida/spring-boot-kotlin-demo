package com.example

import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Suppress("UNUSED_PARAMETER")
class UserHandler(private val userService: UserService) {
    fun findAll(request: ServerRequest): Mono<ServerResponse> =
            ok().body(fromValue(userService.findAll()))

    fun findById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ok().body(fromValue(userService.findById(id)))
    }
}
