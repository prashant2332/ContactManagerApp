package com.example.newcontactmanagerapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newcontactmanagerapp.databinding.ContactListItemBinding

class MyAdapter(private var contacts: ArrayList<Contacts>) : RecyclerView.Adapter<MyAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding: ContactListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.contact_list_item,
            parent,
            false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.binding.contact = contacts[position]

        holder.binding.root.setOnLongClickListener {
            showUpdateDialog(contacts[position],holder.binding.root.context)
            true // Indicate the long click was handled
        }


    }

    private fun showUpdateDialog(contact: Contacts, context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Update Contact")
            .setMessage("Do you want to update this contact?")
            .setPositiveButton("Yes") { _, _ ->
                val intent = Intent(context, UpdateContactActivity::class.java)
                intent.putExtra("CONTACT_ID", contact.id) // Pass contact ID for retrieval
                context.startActivity(intent)
            }
            .setNegativeButton("No", null)
            .show()
    }



    override fun getItemCount(): Int = contacts.size

    fun setContacts(contacts: ArrayList<Contacts>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    class ContactViewHolder(val binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root)
}