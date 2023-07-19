package me.relph.application

import me.relph.domain.hub.Hub
import me.relph.domain.adapter.InMemoryUserStorage

fun main() {
    val hub = Hub(InMemoryUserStorage())
    val server = HexHttpServer(hub)

    server.start()
}

