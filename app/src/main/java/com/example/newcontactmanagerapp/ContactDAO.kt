package com.example.newcontactmanagerapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {
    @Insert
    suspend fun insert(contact: Contacts)

    @Delete
    suspend fun delete(contact: Contacts)

    @Update
    suspend fun update(contact: Contacts)

    @Query("SELECT * FROM contacts_table")
    fun getAllContacts(): LiveData<List<Contacts>>

    @Query("SELECT * FROM contacts_table WHERE contact_id = :id LIMIT 1")
    fun getContactById(id: Int): LiveData<Contacts>
}