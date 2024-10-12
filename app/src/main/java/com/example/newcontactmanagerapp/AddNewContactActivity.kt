package com.example.newcontactmanagerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newcontactmanagerapp.databinding.ActivityAddNewContactBinding

class AddNewContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewContactBinding
    private val contacts = Contacts()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_contact)

        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        binding.contact = contacts
        binding.clickHandler = AddNewContactClickHandler(contacts, this, viewModel)
    }
}