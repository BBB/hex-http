package me.relph.application

import me.relph.adapters.http.greet
import me.relph.adapters.http.ping
import me.relph.domain.hub.Hub
import org.http4k.routing.routes

fun HexHttpApp(hub: Hub) = routes(
    ping(hub),
    greet(hub)
)

