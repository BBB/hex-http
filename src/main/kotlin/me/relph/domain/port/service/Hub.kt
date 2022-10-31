package me.relph.domain.port.service

import dev.forkhandles.result4k.Result4k

interface Hub {
    fun greet(name: String): Result4k<String, Unit>
    fun ping(): Result4k<String, Unit>
}