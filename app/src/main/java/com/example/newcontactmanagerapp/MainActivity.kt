package com.example.newcontactmanagerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newcontactmanagerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val contactsArrayList = ArrayList<Contacts>()
    private lateinit var viewModel: MyViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.clickhandler = MainActivityClickHandler(this)

        // Initialize the adapter with the ViewModel
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myAdapter = MyAdapter(contactsArrayList)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = myAdapter
        binding.recyclerview.setHasFixedSize(true)

        // Observe the LiveData from ViewModel
        viewModel.allContacts.observe(this, Observer { contacts ->
            contactsArrayList.clear()
            contactsArrayList.addAll(contacts)
            myAdapter.notifyDataSetChanged()
        })

        // ItemTouchHelper for swipe to delete
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val contact = contactsArrayList[position]

                // Show confirmation dialog
                AlertDialog.Builder(binding.root.context)
                    .setTitle("Delete Contact")
                    .setMessage("Do you really want to delete this contact?")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel.deleteContact(contact)
                        Toast.makeText(this@MainActivity,"Contact Deleted Successfully",Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                        myAdapter.notifyItemChanged(position)
                    }
                    .setCancelable(false)
                    .show()
            }
        }).attachToRecyclerView(binding.recyclerview)

        title = "Contact Manager"
    }
}
