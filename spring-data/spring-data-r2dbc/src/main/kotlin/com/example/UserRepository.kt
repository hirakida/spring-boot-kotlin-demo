package com.example

import org.springframework.data.domain.Sort.Order.asc
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.from
import org.springframework.data.r2dbc.core.into
import org.springframework.data.r2dbc.core.table
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.data.r2dbc.query.Update
import org.springframework.stereotype.Component

@Component
class UserRepository(private val client: DatabaseClient) {

    fun findAll() =
            client.select()
                    .from<User>()
                    .orderBy(asc("id"))
                    .fetch()
                    .all()

    fun findOne(id: Int) =
            client.select()
                    .from<User>()
                    .matching(where("id").`is`(id))
                    .fetch()
                    .one()

    fun insert(user: User) =
            client.insert()
                    .into<User>()
                    .using(user)
                    .fetch()
                    .rowsUpdated()

    fun insertUntyped(name: String) =
            client.insert()
                    .into("user")
                    .value("name", name)
                    .fetch()
                    .rowsUpdated()

    fun update(user: User) =
            client.update()
                    .table<User>()
                    .using(user)
                    .fetch()
                    .rowsUpdated()

    fun updateUntyped(user: User) =
            client.update()
                    .table("user")
                    .using(Update.update("name", user.name))
                    .matching(where("id").`is`(user.id))
                    .fetch()
                    .rowsUpdated()

    fun delete(id: Int) =
            client.delete()
                    .from<User>()
                    .matching(where("id").`is`(id))
                    .fetch()
                    .rowsUpdated()

    fun deleteAll() =
            client.delete()
                    .from<User>()
                    .fetch()
                    .rowsUpdated()
}
