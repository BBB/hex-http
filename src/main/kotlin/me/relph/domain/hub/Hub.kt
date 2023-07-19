package me.relph.domain.hub

import dev.forkhandles.result4k.*
import me.relph.domain.port.UserStorage

typealias UserId = String

class Hub(private val users: UserStorage)  {
    fun greet(userId: UserId) = users.byId(userId).map{"hello ${it.name}"}.flatMapFailure { Success("hello anon") }
    fun ping() = Success("pong")
}

