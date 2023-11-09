package me.relph.domain.hub

import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.flatMapFailure
import dev.forkhandles.result4k.map
import me.relph.domain.port.UserId
import me.relph.domain.port.UserStorage

class Hub(private val users: UserStorage) {
    fun greet(userId: UserId) = users.byId(userId).map { "hello ${it.name}" }.flatMapFailure { Success("hello anon") }
    fun ping() = Success("pong")
    fun list() = users.all()
}

