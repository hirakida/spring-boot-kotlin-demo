package com.example

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface UserMapper {

    @Select("SELECT id, name, created_at, updated_at FROM user")
    fun findAll(): List<User>

    @Select("""
            SELECT id, name, created_at, updated_at FROM user
            WHERE id=#{id}
    """)
    fun findById(id: Long): User?

    @Select("""
            SELECT id, name, created_at, updated_at FROM user
            WHERE name=#{name}
    """)
    fun findByName(name: String): User?

    @Insert("INSERT INTO user(name, created_at, updated_at) VALUES(#{name}, NOW(), NOW())")
    @Options(useGeneratedKeys = true)
    fun insert(user: User): Int

    @Update("UPDATE user SET name=#{name}, updated_at=NOW() WHERE id=#{id}")
    fun update(user: User): Int

    @Delete("DELETE FROM user WHERE id=#{id}")
    fun delete(id: Long): Int

    @Delete("DELETE FROM user")
    fun deleteAll(): Int
}
