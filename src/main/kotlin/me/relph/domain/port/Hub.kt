package me.relph.domain.port

import dev.forkhandles.result4k.Result4k

typealias UserId = String
interface Hub {
    fun greet(userId: UserId): Result4k<String, Unit>
    fun ping(): Result4k<String, Unit>
}