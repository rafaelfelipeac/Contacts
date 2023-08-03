package com.rafaelfelipeac.contacts.contacts.presentation

import com.rafaelfelipeac.contacts.contacts.domain.Contact

data class ContactListState(
    val contacts: List<Contact> = emptyList(),
    val recentlyAddedContacts: List<Contact> = emptyList(),
    val selectedContact: Contact? = null,
    val isAddedContactSheetOpen: Boolean = false,
    val isSelectedContactSheetOpen: Boolean = false,
    val firstNameError: String?  = null,
    val lastNameError: String? = null,
    val emailError: String?  = null,
    val phoneNumberError: String? = null
)
