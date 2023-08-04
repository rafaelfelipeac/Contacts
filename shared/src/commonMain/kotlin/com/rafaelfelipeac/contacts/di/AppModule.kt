package com.rafaelfelipeac.contacts.di

import com.rafaelfelipeac.contacts.contacts.domain.ContactDataSource

expect class AppModule {

    val contactDataSource: ContactDataSource
}