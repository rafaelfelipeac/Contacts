package com.rafaelfelipeac.contacts.contacts.data

import com.rafaelfelipeac.contacts.contacts.domain.Contact
import com.rafaelfelipeac.contacts.core.data.ImageStorage
import database.ContactEntity

suspend fun ContactEntity.toContact(
    imageStorage: ImageStorage
): Contact {
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = imagePath?.let { imageStorage.getImage(it) }
    )
}