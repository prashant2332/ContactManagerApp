package com.example.newcontactmanagerapp

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(application: Application) {
    private val contactDAO: ContactDAO = ContactDatabase.getInstance(application).getContactDAO()

    suspend fun addContact(contact: Contacts) {
        withContext(Dispatchers.IO) {
            contactDAO.insert(contact)
        }
    }

    suspend fun deleteContact(contact: Contacts) {
        withContext(Dispatchers.IO) {
            contactDAO.delete(contact)
        }
    }

    suspend fun updateContact(contact: Contacts) {
        withContext(Dispatchers.IO) {
            contactDAO.update(contact)  // Ensure you have an update query in your DAO
        }
    }

    fun getAllContacts(): LiveData<List<Contacts>> {
        return contactDAO.getAllContacts()
    }

    fun getContactById(id: Int): LiveData<Contacts> {
        return contactDAO.getContactById(id)
    }
}