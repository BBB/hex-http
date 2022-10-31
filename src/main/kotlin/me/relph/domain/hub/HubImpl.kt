package me.relph.domain.hub

import me.relph.domain.port.service.Hub

class HubImpl : Hub {
    override fun greet(name: String) = "hello $name"
    override fun ping() = "pong"
}