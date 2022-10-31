package me.relph.domain.hub


import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class HubImplTest {

    private val hub = HubImpl()

    @Test
    fun `can ping`() {
        expectThat(hub.ping()).isEqualTo("pong")
    }
    @Test
    fun `can greet`() {
        expectThat(hub.greet("nigel")).isEqualTo("hello nigel")
    }
}