package com.example

import org.springframework.data.redis.core.ReactiveRedisTemplate

const val KEY = "users"

class UserRepository(reactiveRedisTemplate: ReactiveRedisTemplate<String, User>) {
    private val operations = reactiveRedisTemplate.opsForHash<String, User>()

    fun findAll() = operations.values(KEY)

    fun findById(id: String) = operations.get(KEY, id)

    fun save(user: User) = operations.put(KEY, user.id, user)
}
