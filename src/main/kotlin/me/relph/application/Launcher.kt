package me.relph.application

import me.relph.domain.hub.HubImpl
import me.relph.domain.port.InMemoryUserStorage

fun main() {
    val hub = HubImpl(InMemoryUserStorage())
    val server = HexHttpServer(hub)

    server.start()
}

