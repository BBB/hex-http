package me.relph.domain.hub

import dev.forkhandles.result4k.Success
import me.relph.domain.port.service.Hub

class HubImpl : Hub {
    override fun greet(name: String) = Success("hello $name")
    override fun ping() = Success("pong")
}