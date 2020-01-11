package com.example

import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.ok

@Component
class UserHandler(private val repository: UserRepository) {

    fun findAll(request: ServerRequest): ServerResponse = ok().body(repository.findAll())

    fun findById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toInt()
        return ok().body(repository.findById(id))
    }
}
