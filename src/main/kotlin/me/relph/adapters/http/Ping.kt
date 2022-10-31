package me.relph.adapters.http

import me.relph.domain.port.service.Hub
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind

fun ping(hub: Hub) = "/ping" bind Method.GET to { _: Request -> Response(Status.OK).body(hub.ping()) }