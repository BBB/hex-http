package me.relph.adapters.http

import dev.forkhandles.result4k.get
import dev.forkhandles.result4k.map
import dev.forkhandles.result4k.mapFailure
import me.relph.domain.hub.Hub
import me.relph.domain.port.User
import org.http4k.core.Body
import org.http4k.format.Jackson.auto
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind

val usersLens = Body.auto<List<User>>().toLens()

fun listUsers(hub: Hub): RoutingHttpHandler {
    return "/users/" bind Method.GET to { req: Request ->
        hub.list().map { Response(Status.OK).with(usersLens of it) }.mapFailure { Response(Status.INTERNAL_SERVER_ERROR) }.get()
    }
}

