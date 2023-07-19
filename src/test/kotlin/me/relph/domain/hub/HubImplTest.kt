package me.relph.domain.hub


import dev.forkhandles.result4k.Success
import me.relph.domain.adapter.InMemoryUserStorage
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class HubImplTest {

    private val hub = Hub(InMemoryUserStorage())

    @Test
    fun `can ping`() {
        expectThat(hub.ping()).isEqualTo(Success("pong"))
    }
    @Test
    fun `can greet an unknown user`() {
        expectThat(hub.greet("xxx")).isEqualTo(Success("hello anon"))
    }
}
