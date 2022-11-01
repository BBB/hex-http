package me.relph.domain.hub

import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.flatMapFailure
import dev.forkhandles.result4k.map
import me.relph.domain.port.Hub
import me.relph.domain.port.UserId
import me.relph.domain.port.UserStorage

class HubImpl(private val users: UserStorage) : Hub {
    override fun greet(userId: UserId) = users.byId(userId).map{"hello ${it.name}"}.flatMapFailure { Success("hello anon") }
    override fun ping() = Success("pong")
}