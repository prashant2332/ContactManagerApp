package com.example.newcontactmanagerapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contacts(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "contact_id") var id: Int = 0,
    @ColumnInfo(name = "contact_name") var name: String? = null,
    @ColumnInfo(name = "contact_email") var email: String? = null,
    @ColumnInfo(name = "contact_phone") var phone: String? = null,  //new field
    @ColumnInfo(name = "contact_address") var address: String? = null  // New field
)