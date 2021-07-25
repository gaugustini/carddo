package com.gaugustini.carddo.ui

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gaugustini.carddo.model.Contact
import com.gaugustini.carddo.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository,
) : ViewModel() {

    var color = Color.parseColor("#FFFFFF")

    fun getAll(): LiveData<List<Contact>> = contactRepository.getContacts().asLiveData()

    fun getContact(id: Int): LiveData<Contact> = contactRepository.getContactById(id).asLiveData()

    fun delete(contact: Contact) {
        viewModelScope.launch { contactRepository.delete(contact) }
    }

    fun isEntryValid(
        name: String,
        job: String,
        phone: String,
        mail: String,
        company: String,
        site: String
    ): Boolean {
        if (name.isBlank() || job.isBlank() || phone.isBlank()
            || mail.isBlank() || company.isBlank() || site.isBlank()
        ) {
            return false
        }
        return true
    }

    fun entryContact(
        id: Int,
        name: String,
        job: String,
        phone: String,
        mail: String,
        company: String,
        site: String,
    ) {
        if (id > 0) {
            update(Contact(name, job, phone, mail, company, site, color, id))
        } else {
            insert(Contact(name, job, phone, mail, company, site, color))
        }
    }

    private fun insert(contact: Contact) {
        viewModelScope.launch { contactRepository.insert(contact) }
    }

    private fun update(contact: Contact) {
        viewModelScope.launch { contactRepository.update(contact) }
    }

}
