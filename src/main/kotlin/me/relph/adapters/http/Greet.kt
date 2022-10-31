package me.relph.adapters.http

import me.relph.domain.port.service.Hub
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path

fun greet(hub: Hub) = "/greet/{name}" bind Method.GET to { req: Request ->
    val name: String? = req.path("name")
    Response(Status.OK).body(hub.greet(name ?: "anon"))
}

