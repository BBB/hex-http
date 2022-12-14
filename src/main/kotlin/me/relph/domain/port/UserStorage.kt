package me.relph.domain.port

import dev.forkhandles.result4k.Result4k

data class User(val name: String)
sealed class UserStorageFailure(val message: String) {
    data class NotFound(val id: UserId, val cause: Throwable? = null) : UserStorageFailure("Unable to find it: $id")
}


interface UserStorage {
    fun byId(id: UserId): Result4k<User, UserStorageFailure>
}