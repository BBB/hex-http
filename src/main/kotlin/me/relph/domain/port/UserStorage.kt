package me.relph.domain.port

import dev.forkhandles.result4k.Result4k

data class User(val name: String)
class NotFound

interface UserStorage {
    fun byId(id: UserId): Result4k<User, NotFound>
}