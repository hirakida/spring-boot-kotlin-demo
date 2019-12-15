package com.example

import kotlinx.coroutines.flow.Flow
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations
import org.springframework.data.mongodb.core.awaitOne
import org.springframework.data.mongodb.core.flow
import org.springframework.data.mongodb.core.insert
import org.springframework.data.mongodb.core.oneAndAwait
import org.springframework.data.mongodb.core.query
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.query.where

class UserRepository(private val operations: ReactiveFluentMongoOperations) {

    fun findAll(): Flow<User> = operations.query<User>().flow()

    suspend fun findById(id: String): User =
            operations.query<User>()
                    .matching(query(where(User::id).isEqualTo(id)))
                    .awaitOne()

    suspend fun insert(user: User): User = operations.insert<User>().oneAndAwait(user)
}
