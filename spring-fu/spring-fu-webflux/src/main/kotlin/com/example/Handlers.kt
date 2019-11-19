package com.example

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Suppress("UNUSED_PARAMETER")
class RootHandler {
    fun hello(request: ServerRequest) =
            ServerResponse
                    .ok()
                    .body("Hello, Spring Fu!")
}

@Suppress("UNUSED_PARAMETER")
class UserHandler(private val userService: UserService) {
    fun findAll(request: ServerRequest) =
            ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userService.findAll())

    fun findById(request: ServerRequest) =
            ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userService.findById(request.pathVariable("id").toInt()))
}
