package me.relph.domain.hub


import dev.mrbergin.kotest.result4k.shouldBeSuccess
import me.relph.domain.adapter.InMemoryUserStorage
import me.relph.domain.port.UserId
import org.junit.jupiter.api.Test

internal class HubTest {

    private val hub = Hub(InMemoryUserStorage())

    @Test
    fun `can ping`() {
        hub.ping() shouldBeSuccess "pong"
    }

    @Test
    fun `can greet an unknown user`() {
        hub.greet(UserId.of("xxx")) shouldBeSuccess "hello anon"
    }
}
