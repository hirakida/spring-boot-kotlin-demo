package com.example

class UserService(var users: MutableList<User>) {

    fun findAll() = users

    fun findById(id: Int) = users.first { it.id == id }

    fun save(user: User) = users.add(user)
}
