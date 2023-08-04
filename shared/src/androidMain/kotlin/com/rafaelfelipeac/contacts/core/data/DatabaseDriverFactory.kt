package com.rafaelfelipeac.contacts.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.rafaelfelipeac.contacts.database.ContactDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {

    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            ContactDatabase.Schema,
            context,
            "contact.db"
        )
    }
}