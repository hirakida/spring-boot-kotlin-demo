package com.example

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Int): User = userRepository.findById(id).orElseThrow { NoSuchElementException() }

    fun create(user: User): User {
        return userRepository.save(user)
    }

    fun update(user: User): User {
        val current = userRepository.getOne(user.id)
        current.name = user.name
        current.age = user.age
        return userRepository.save(current)
    }

    fun delete(id: Int) {
        val user = userRepository.getOne(id)
        userRepository.deleteById(user.id)
    }
}
