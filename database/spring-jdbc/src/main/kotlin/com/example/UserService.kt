package com.example

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class UserService(private val jdbcTemplate: JdbcTemplate) {

    fun findById(id: Int): User =
            jdbcTemplate.queryForObject("SELECT id, name FROM user WHERE id=$id")
            { rs, _ -> User(rs.getInt("id"), rs.getString("name")) }
                    ?: throw NoSuchElementException("id: $id")


    fun findAll(): List<User> =
            jdbcTemplate.query("SELECT * FROM user")
            { rs, _ -> User(rs.getInt("id"), rs.getString("name")) }
}
