package com.example

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Suppress("UNUSED_PARAMETER")
class UserHandler(private val repository: UserRepository) {
    suspend fun findAll(request: ServerRequest): ServerResponse =
            ok().bodyAndAwait(repository.findAll())

    suspend fun findById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        return ok().bodyValueAndAwait(repository.findById(id))
    }
}
