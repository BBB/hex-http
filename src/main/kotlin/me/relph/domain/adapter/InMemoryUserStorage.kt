package me.relph.domain.adapter

import dev.forkhandles.result4k.Result4k
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.asResultOr
import me.relph.domain.port.User
import me.relph.domain.port.UserId
import me.relph.domain.port.UserStorage
import me.relph.domain.port.UserStorageFailure
import me.relph.domain.port.UserStorageFailure.NotFound

class InMemoryUserStorage(private val users: Map<UserId, User> = mapOf()) : UserStorage {
    override fun byId(id: UserId): Result4k<User, NotFound> = users[id].asResultOr { NotFound(id) }
    override fun all(): Result4k<List<User>, UserStorageFailure> = Success(users.values.toList())
}
