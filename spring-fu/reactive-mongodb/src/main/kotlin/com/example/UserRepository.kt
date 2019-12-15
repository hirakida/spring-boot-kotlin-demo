package com.example

import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findById

class UserRepository(private val operations: ReactiveMongoOperations) {

    fun findAll() = operations.findAll<User>()

    fun findById(id: String) = operations.findById<User>(id)

    fun save(user: User) = operations.save(user)
}
