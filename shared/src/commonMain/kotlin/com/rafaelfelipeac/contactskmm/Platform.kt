package com.rafaelfelipeac.contactskmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform