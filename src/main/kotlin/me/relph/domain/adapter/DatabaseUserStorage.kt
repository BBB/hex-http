package me.relph.domain.adapter

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result4k
import dev.forkhandles.result4k.Success
import me.relph.domain.port.User
import me.relph.domain.port.UserId
import me.relph.domain.port.UserStorage
import me.relph.domain.port.UserStorageFailure
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table() {
    val id: Column<String> = varchar("id", 10)
    val name: Column<String> = varchar("name", length = 50)
    override val primaryKey = PrimaryKey(id, name = "PK_User_ID") // name is optional here
}

private fun ResultRow.toUser() = User(UserId.of(this[Users.id]), this[Users.name])
class DatabaseUserStorage(private val database: Database) : UserStorage {

    override fun insert(vararg users: User): Result4k<Unit, UserStorageFailure> {
            return try {
                Users.batchInsert(users.toList()) { user ->
                    this[Users.name] = user.name
                    this[Users.id] = user.id.value
                }
                return Success(Unit)
            }catch (e: Exception) {
                Failure(UserStorageFailure.Unclassified(e))
            }
    }

    override fun byId(id: UserId): Result4k<User, UserStorageFailure> = try {
        Success(Users.select {
            Users.id.eq(id.value)
        }.limit(1).single().toUser())
    } catch (e: Exception) {
        when (e) {
            is NoSuchElementException -> Failure(UserStorageFailure.NotFound(id))
            else -> Failure(UserStorageFailure.Unclassified(e))
        }
    }

    override fun all(): Result4k<List<User>, UserStorageFailure> = try {
        Success(Users.selectAll().map { it.toUser() })
    } catch (e: Exception) {
        Failure(UserStorageFailure.Unclassified(e))
    }

    override fun <T> transaction(block: () -> T): T = transaction(database) {
        block()
    }

}
