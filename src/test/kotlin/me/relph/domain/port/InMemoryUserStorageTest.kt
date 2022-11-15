package me.relph.domain.port

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import me.relph.domain.port.UserStorageFailure.NotFound
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

private val roger = User("Roger")

internal class InMemoryUserStorageTest {
 val storage = InMemoryUserStorage(mapOf("1" to roger))

    @Test
    fun `can get a user by id`() {
        expectThat(storage.byId("1")).isEqualTo(Success(roger))
    }

    @Test
    fun `can not get a user by id`() {
        expectThat(storage.byId("2")).isEqualTo(Failure(NotFound("2")))
    }
}