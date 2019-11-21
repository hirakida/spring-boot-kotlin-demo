package com.example

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.ResultSet

@Service
@Transactional
@Component
class UserRepository(private val jdbcTemplate: JdbcTemplate) {

    fun findById(id: Int): User =
            jdbcTemplate.queryForObject("SELECT id, name FROM user WHERE id=$id")
            { rs, _ -> toUser(rs) } ?: throw NoSuchElementException("id: $id")

    fun findAll(): List<User> =
            jdbcTemplate.query("SELECT id, name FROM user")
            { rs, _ -> toUser(rs) }

    private fun toUser(rs: ResultSet) = User(rs.getInt("id"), rs.getString("name"))
}
