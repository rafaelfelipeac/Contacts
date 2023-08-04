package com.rafaelfelipeac.contacts.contacts.data

import com.rafaelfelipeac.contacts.contacts.domain.Contact
import database.ContactEntity

fun ContactEntity.toContact() : Contact {
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = null
    )
}