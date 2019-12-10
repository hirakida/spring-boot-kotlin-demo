package com.example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.domain.Sort.Order.asc
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.r2dbc.core.from
import org.springframework.data.r2dbc.core.into
import org.springframework.data.r2dbc.core.table
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class UserRepository(private val client: DatabaseClient) {

    suspend fun findAll(): Flow<User> =
            client.select()
                    .from<User>()
                    .orderBy(asc("id"))
                    .fetch()
                    .flow()

    suspend fun findOne(id: Int): User? =
            client.select()
                    .from<User>()
                    .matching(where("id").`is`(id))
                    .fetch()
                    .awaitOneOrNull()

    suspend fun insert(user: User) =
            client.insert()
                    .into<User>()
                    .using(user)
                    .await()

    suspend fun insertUntyped(name: String) =
            client.insert()
                    .into("user")
                    .value("name", name)
                    .await()

    suspend fun update(user: User) =
            client.update()
                    .table<User>()
                    .using(user)
                    .then()
                    .awaitFirstOrNull()

    suspend fun delete(id: Int) =
            client.delete()
                    .from<User>()
                    .matching(where("id").`is`(id))
                    .then()
                    .awaitFirstOrNull()
}
