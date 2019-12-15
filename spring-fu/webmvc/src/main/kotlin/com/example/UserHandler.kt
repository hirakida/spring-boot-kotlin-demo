package com.example

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.ok

@Suppress("UNUSED_PARAMETER")
class UserHandler {
    fun findAll(request: ServerRequest): ServerResponse {
        val users = (1..5).map { User(it, "name$it") }
        return ok().body(users)
    }

    fun findById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toInt()
        return ok().body(User(id, "name$id"))
    }
}
