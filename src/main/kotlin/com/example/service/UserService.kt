package com.example.service

import com.example.entity.User
import com.example.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAll()

    fun getOne(id: Int): User = userRepository.getOne(id)

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
