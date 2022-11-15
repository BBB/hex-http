package me.relph.domain.port

import dev.forkhandles.result4k.Result4k
import dev.forkhandles.result4k.asResultOr
import me.relph.domain.port.UserStorageFailure.NotFound

class InMemoryUserStorage(private val users: Map<UserId, User> = mapOf()) : UserStorage {
    override fun byId(id: UserId): Result4k<User, NotFound> = users[id].asResultOr { NotFound(id) }

}