package me.relph.domain.port

interface AppStorage {
    fun <T>transaction(block: () -> T): T
}
