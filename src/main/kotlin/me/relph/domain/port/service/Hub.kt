package me.relph.domain.port.service

interface Hub {
    fun greet(name: String): String
    fun ping(): String
}