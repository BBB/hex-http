package me.relph.adapters.http

import me.relph.application.HexHttpApp
import me.relph.domain.adapter.InMemoryUserStorage
import me.relph.domain.hub.Hub
import me.relph.domain.port.User
import me.relph.domain.port.UserId
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.kotest.shouldHaveBody
import org.http4k.kotest.shouldHaveStatus
import org.junit.jupiter.api.Test

internal class GreetTest {
    @Test
    fun `greets the user when they do not exist`() {
        val hub = Hub(InMemoryUserStorage())
        val app = HexHttpApp(hub)
        val response = app(Request(Method.GET, "/greet/roger"))
        response shouldHaveStatus Status.OK
        response shouldHaveBody "hello anon"
    }

    @Test
    fun `greets the user when they exist`() {
        val hub = Hub(InMemoryUserStorage(mutableMapOf(UserId.of("1") to User(UserId.of("1"), "Roger"))))
        val app = HexHttpApp(hub)
        val response = app(Request(Method.GET, "/greet/1"))
        response shouldHaveStatus Status.OK
        response shouldHaveBody "hello Roger"
    }
}
