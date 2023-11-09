package me.relph.adapters.http

import io.kotest.matchers.be
import me.relph.application.HexHttpApp
import me.relph.domain.adapter.InMemoryUserStorage
import me.relph.domain.hub.Hub
import me.relph.domain.port.User
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.kotest.shouldHaveBody
import org.junit.jupiter.api.Test

internal class ListUsersTest {
    @Test
    fun `can list all users (empty)`() {
        val hub = Hub(InMemoryUserStorage())
        val app = HexHttpApp(hub)
        val response = app(Request(Method.GET, "/users"))
        response.shouldHaveBody(usersLens, be(listOf()))
    }

    @Test
    fun `can list all users (some)`() {
        val hub = Hub(InMemoryUserStorage(mapOf("1" to User("roger"))))
        val app = HexHttpApp(hub)
        val response = app(Request(Method.GET, "/users"))
        response.shouldHaveBody(usersLens, be(listOf(User("roger"))))
    }
}
