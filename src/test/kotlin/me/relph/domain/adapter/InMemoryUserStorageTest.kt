package me.relph.domain.adapter

internal class InMemoryUserStorageTest : UserStorageContract {
    override val storage = InMemoryUserStorage()
}
