package com.example.newcontactmanagerapp

import android.content.Context
import android.content.Intent
import android.view.View

class MainActivityClickHandler(private val context: Context){
    fun onFABClicked(view: View){
        val intent=Intent(view.context,AddNewContactActivity::class.java)
        context.startActivity(intent)
        (context as MainActivity).finish()
    }

}