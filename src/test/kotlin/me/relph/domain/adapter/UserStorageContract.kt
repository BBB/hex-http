package me.relph.domain.adapter

import dev.mrbergin.kotest.result4k.shouldBeFailure
import dev.mrbergin.kotest.result4k.shouldBeSuccess
import me.relph.domain.port.User
import me.relph.domain.port.UserId
import me.relph.domain.port.UserStorage
import me.relph.domain.port.UserStorageFailure
import org.jetbrains.exposed.sql.SchemaUtils
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

val roger = User(UserId.of("1"), "Roger")

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
interface UserStorageContract {
    val storage: UserStorage

    fun createSchema() {}


    @BeforeAll
    fun beforeAll() {
        storage.transaction {
            createSchema()
            storage.insert(roger)
        }
    }

    @Test
    fun `can get a user by id`() {
        storage.transaction {
            storage.byId(UserId.of("1")) shouldBeSuccess roger
        }
    }

    @Test
    fun `can not get a user by id`() {
        storage.transaction {
            storage.byId(UserId.of("2")) shouldBeFailure UserStorageFailure.NotFound(UserId.of("2"))
        }
    }

    @Test
    fun `can get all users`() {
        storage.transaction {
            storage.all() shouldBeSuccess listOf(roger)
        }
    }
}
