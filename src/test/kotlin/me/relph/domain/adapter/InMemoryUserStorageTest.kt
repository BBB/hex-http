package me.relph.domain.adapter

import dev.mrbergin.kotest.result4k.shouldBeFailure
import dev.mrbergin.kotest.result4k.shouldBeSuccess
import me.relph.domain.port.User
import me.relph.domain.port.UserId
import me.relph.domain.port.UserStorage
import me.relph.domain.port.UserStorageFailure.NotFound
import org.junit.jupiter.api.Test

private val roger = User("Roger")

interface UserStorageContract {
    val storage: UserStorage
    @Test
    fun `can get a user by id`() {
        storage.byId(UserId.of("1")) shouldBeSuccess roger
    }

    @Test
    fun `can not get a user by id`() {
        storage.byId(UserId.of("2")) shouldBeFailure NotFound(UserId.of("2"))
    }
    @Test
    fun `can get all users`() {
        storage.all() shouldBeSuccess  listOf(roger)
    }
}

internal class InMemoryUserStorageTest : UserStorageContract {
    override val storage = InMemoryUserStorage(mapOf(UserId.of("1") to roger))
}
