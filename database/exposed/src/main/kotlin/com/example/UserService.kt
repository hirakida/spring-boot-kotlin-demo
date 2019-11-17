package com.example

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService {

    fun selectAll(): List<User> =
            Users.selectAll().map { User(it[Users.id], it[Users.name]) }

    fun insert(user: User) =
            Users.insert {
                it[name] = user.name
            }
}
