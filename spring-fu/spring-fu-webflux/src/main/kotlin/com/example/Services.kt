package com.example

import java.time.LocalDateTime

class UserService {
    fun findAll() = (1..10).map { create(it) }

    fun findById(id: Int) = create(id)

    private fun create(id: Int) = User(id, "user$id", LocalDateTime.now())
}
