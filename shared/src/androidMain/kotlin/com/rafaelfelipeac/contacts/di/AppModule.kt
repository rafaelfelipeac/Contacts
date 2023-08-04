package com.rafaelfelipeac.contacts.di

import android.content.Context
import com.rafaelfelipeac.contacts.contacts.data.SqlDelightContactDataSource
import com.rafaelfelipeac.contacts.contacts.domain.ContactDataSource
import com.rafaelfelipeac.contacts.core.data.DatabaseDriverFactory
import com.rafaelfelipeac.contacts.database.ContactDatabase

actual class AppModule(
    private val context: Context
) {

    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory(context).create()
            )
        )
    }
}