package me.relph.application

import me.relph.domain.port.Hub
import org.http4k.core.HttpHandler
import org.http4k.server.Http4kServer
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun HexHttpServer(hub: Hub): Http4kServer {
    val app: HttpHandler = HexHttpApp(hub)
    return app.asServer(Jetty(9000))
}