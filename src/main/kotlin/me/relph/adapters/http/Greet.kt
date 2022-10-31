package me.relph.adapters.http

import dev.forkhandles.result4k.get
import dev.forkhandles.result4k.map
import dev.forkhandles.result4k.mapFailure
import me.relph.domain.port.service.Hub
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path

fun greet(hub: Hub) = "/greet/{name}" bind Method.GET to { req: Request ->
    val name: String? = req.path("name")
    hub.greet(name ?: "anon").map { Response(Status.OK).body(it) }.mapFailure { Response(Status.INTERNAL_SERVER_ERROR) }.get()
}

