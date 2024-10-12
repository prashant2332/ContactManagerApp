package com.example.newcontactmanagerapp

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast

class AddNewContactClickHandler(
    private val contact: Contacts,
    private val context: Context,
    private val myViewModel: MyViewModel
) {
    fun onsubmitbtnclicked(view: View) {
        if (contact.name.isNullOrEmpty() || contact.email.isNullOrEmpty() ||contact.phone.isNullOrEmpty() || contact.address.isNullOrEmpty()) {
            Toast.makeText(context, "Fields cannot be Empty", Toast.LENGTH_SHORT).show()
        } else {
            val newContact = Contacts(name = contact.name, email = contact.email, phone = contact.phone, address = contact.address)
            myViewModel.addNewContact(newContact)
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}