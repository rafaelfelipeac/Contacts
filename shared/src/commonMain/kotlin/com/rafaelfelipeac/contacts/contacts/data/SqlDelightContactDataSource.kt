package com.rafaelfelipeac.contacts.contacts.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.rafaelfelipeac.contacts.contacts.domain.Contact
import com.rafaelfelipeac.contacts.contacts.domain.ContactDataSource
import com.rafaelfelipeac.contacts.database.ContactDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightContactDataSource(
    db: ContactDatabase
) : ContactDataSource {

    private val queries = db.contactQueries

    override fun getContacts(): Flow<List<Contact>> {
        return queries
            .getContacts()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { list ->
                list.map { contact ->
                    contact.toContact()
                }
            }
    }

    override fun getRecentContacts(amount: Int): Flow<List<Contact>> {
        return queries
            .getRecentContacts(amount.toLong())
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { list ->
                list.map { contact ->
                    contact.toContact()
                }
            }
    }

    override suspend fun insertContact(contact: Contact) {
        queries
            .insertContactEntity(
                id = contact.id,
                firstName = contact.firstName,
                lastName = contact.lastName,
                phoneNumber = contact.phoneNumber,
                email = contact.email,
                createdAt = Clock.System.now().toEpochMilliseconds(),
                imagePath = null
            )
    }

    override suspend fun deleteContact(id: Long) {
        queries.deleteContact(id)
    }
}