package com.example.newcontactmanagerapp

import android.content.Context
import android.view.View
import android.widget.Toast

class UpdateContactActivityClickHandler(
    private val contact: Contacts,
    private val context: Context,
    private val viewModel: MyViewModel
) {
    fun onUpdateButtonClicked(view: View) {
        if (contact.name.isNullOrEmpty() || contact.email.isNullOrEmpty() || contact.phone.isNullOrEmpty() || contact.address.isNullOrEmpty()) {
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.updateContact(contact)
            Toast.makeText(context, "Contact updated successfully", Toast.LENGTH_SHORT).show()
            (context as? UpdateContactActivity)?.finish()
        }
    }
}
