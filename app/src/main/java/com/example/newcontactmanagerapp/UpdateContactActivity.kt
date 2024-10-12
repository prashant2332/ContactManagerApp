package com.example.newcontactmanagerapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newcontactmanagerapp.databinding.ActivityUpdateContactBinding

class UpdateContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateContactBinding
    private lateinit var viewModel: MyViewModel
    private var contactId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_contact)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        // Retrieve contact ID from the intent
        contactId = intent.getIntExtra("CONTACT_ID", -1)

        // Observe the contact data
        if (contactId != -1) {
            viewModel.getContactById(contactId!!).observe(this) { contact ->
                binding.contact = contact // Bind the contact data
                binding.clickHandler=UpdateContactActivityClickHandler(contact!!,this,viewModel)

            }
        } else {
            Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show()
            finish() // Close the activity if contact not found
        }
    }
}
