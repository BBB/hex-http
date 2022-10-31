package me.relph.application

import me.relph.domain.hub.HubImpl

fun main() {
    val hub = HubImpl()
    val server = HexHttpServer(hub)

    server.start()
}

