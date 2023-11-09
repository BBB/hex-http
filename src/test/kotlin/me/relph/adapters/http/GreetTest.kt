package me.relph.adapters.http

import me.relph.application.HexHttpApp
import me.relph.domain.adapter.InMemoryUserStorage
import me.relph.domain.hub.Hub
import me.relph.domain.port.User
import org.http4k.core.Method
import org.http4k.core.Request
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.assertEquals

internal class GreetTest {
    @Test
    fun `greets the user when they do not exist`() {
        val hub = Hub(InMemoryUserStorage())
        val app = HexHttpApp(hub)
        val response = app(Request(Method.GET, "/greet/roger"))
        expectThat(response.status.code).isEqualTo(200)
        expectThat(response.bodyString()).isEqualTo("hello anon x")
    }

    @Test
    fun `greets the user when they exist`() {
        val hub = Hub(InMemoryUserStorage(mapOf("roger" to User("Roger"))))
        val app = HexHttpApp(hub)
        val response = app(Request(Method.GET, "/greet/roger"))
        assertEquals(response.status.code, 200)
        assertEquals(response.bodyString(), "hello Roger")
    }
}
