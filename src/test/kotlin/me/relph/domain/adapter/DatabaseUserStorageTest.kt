package me.relph.domain.adapter

import me.relph.domain.port.UserStorage
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

class DatabaseUserStorageTest : UserStorageContract {

    override val storage: UserStorage = DatabaseUserStorage(
        Database.connect(
            "jdbc:h2:file:/tmp/boom-test;AUTO_SERVER=TRUE",
            driver = "org.h2.Driver",
            user = "root",
            password = ""
        )
    )
    override fun createSchema() {
        SchemaUtils.create(Users)
    }

}
