package com.gaugustini.carddo.repository

import com.gaugustini.carddo.database.ContactDao
import com.gaugustini.carddo.model.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDao: ContactDao) {

    suspend fun insert(contact: Contact) = contactDao.insert(contact)

    suspend fun update(contact: Contact) = contactDao.update(contact)

    suspend fun delete(contact: Contact) = contactDao.delete(contact)

    fun getContactById(id: Int): Flow<Contact> = contactDao.getContactById(id)

    fun getContacts(): Flow<List<Contact>> = contactDao.getContacts()

}
