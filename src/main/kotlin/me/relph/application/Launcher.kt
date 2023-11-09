package me.relph.application

import me.relph.domain.adapter.InMemoryUserStorage
import me.relph.domain.hub.Hub

fun main() {
    val hub = Hub(InMemoryUserStorage())
    val server = HexHttpServer(hub)

    server.start()
}

