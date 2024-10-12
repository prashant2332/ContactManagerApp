package com.example.newcontactmanagerapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = Repository(application)
    val allContacts: LiveData<List<Contacts>> = repository.getAllContacts()

    fun addNewContact(contact: Contacts) {
        viewModelScope.launch {
            repository.addContact(contact)
        }
    }

    fun deleteContact(contact: Contacts) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
    fun updateContact(contact: Contacts) {
        viewModelScope.launch {
            repository.updateContact(contact)  // Ensure this method exists in the Repository
        }
    }

    fun getContactById(id: Int): LiveData<Contacts> {
        return repository.getContactById(id)
    }

}