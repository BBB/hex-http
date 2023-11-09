package me.relph.adapters.http

import dev.forkhandles.result4k.get
import dev.forkhandles.result4k.map
import dev.forkhandles.result4k.mapFailure
import me.relph.domain.hub.Hub
import me.relph.domain.port.UserId
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.lens.Path
import org.http4k.routing.bind

private val userIdLens = Path.map(UserId::of).of("userId")
fun greet(hub: Hub) = "/greet/{userId}" bind Method.GET to { req: Request ->
    val userId = userIdLens(req)
    hub.greet(userId ).map { Response(Status.OK).body(it) }.mapFailure { Response(Status.INTERNAL_SERVER_ERROR) }
        .get()
}

