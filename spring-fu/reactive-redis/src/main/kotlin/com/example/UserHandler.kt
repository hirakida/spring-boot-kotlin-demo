package com.example

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Suppress("UNUSED_PARAMETER")
class UserHandler(private val repository: UserRepository) {
    fun findAll(request: ServerRequest) =
            ok().body(repository.findAll())

    fun findById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id")
        return ok().body(repository.findById(id))
    }
}
