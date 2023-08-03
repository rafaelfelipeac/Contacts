package com.rafaelfelipeac.contacts

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform