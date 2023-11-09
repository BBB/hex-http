package me.relph.domain.port

import dev.forkhandles.result4k.Result4k
import dev.forkhandles.values.NonBlankStringValueFactory
import dev.forkhandles.values.StringValue

class UserId private constructor(override val value: String) : StringValue(value) {
    companion object : NonBlankStringValueFactory<UserId>(::UserId)
}


data class User(val id: UserId, val name: String)
sealed class UserStorageFailure(val message: String) {
    data class NotFound(val id: UserId, val cause: Throwable? = null) : UserStorageFailure("Unable to find it: $id")
    /**
     * The aim here is to remove all instances of this from our monitoring
     * Implementation specific errors should all be mapped to Domain specific types
     */
    data class Unclassified(val cause: Throwable? = null) : UserStorageFailure("Unclassified")
}


interface UserStorage: AppStorage {
    fun insert(vararg users: User): Result4k<Unit, UserStorageFailure>
    fun byId(id: UserId): Result4k<User, UserStorageFailure>
    fun all(): Result4k<List<User>, UserStorageFailure>
}
