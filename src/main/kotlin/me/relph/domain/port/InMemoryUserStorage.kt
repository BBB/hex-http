package me.relph.domain.port

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result4k

class InMemoryUserStorage : UserStorage {
    override fun byId(id: UserId): Result4k<User, NotFound> =
        Failure(NotFound())

}