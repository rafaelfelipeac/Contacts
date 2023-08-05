package com.rafaelfelipeac.contacts.contacts.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.rafaelfelipeac.contacts.contacts.domain.Contact
import com.rafaelfelipeac.contacts.contacts.domain.ContactDataSource
import com.rafaelfelipeac.contacts.core.data.ImageStorage
import com.rafaelfelipeac.contacts.database.ContactDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock

class SqlDelightContactDataSource(
    db: ContactDatabase,
    private val imageStorage: ImageStorage
) : ContactDataSource {

    private val queries = db.contactQueries

    override fun getContacts(): Flow<List<Contact>> {
        return queries
            .getContacts()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { list ->
                supervisorScope {
                    list
                        .map {
                            async {
                                it.toContact(imageStorage)
                            }
                        }
                        .map { it.await() }
                }
            }
    }

    override fun getRecentContacts(amount: Int): Flow<List<Contact>> {
        return queries
            .getRecentContacts(amount.toLong())
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { list ->
                supervisorScope {
                    list
                        .map {
                            async {
                                it.toContact(imageStorage)
                            }
                        }
                        .map { it.await() }
                }
            }
    }

    override suspend fun insertContact(contact: Contact) {
        val imagePath = contact.photoBytes?.let {
            imageStorage.saveImage(it)
        }

        queries
            .insertContactEntity(
                id = contact.id,
                firstName = contact.firstName,
                lastName = contact.lastName,
                phoneNumber = contact.phoneNumber,
                email = contact.email,
                createdAt = Clock.System.now().toEpochMilliseconds(),
                imagePath = imagePath
            )
    }

    override suspend fun deleteContact(id: Long) {
        val entity = queries.getContactById(id).executeAsOne()
        entity.imagePath?.let {
            imageStorage.deleteImage(it)
        }

        queries.deleteContact(id)
    }
}