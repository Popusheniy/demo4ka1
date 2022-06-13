package com.example.demo4ka.database

import androidx.lifecycle.LiveData
import com.example.demo4ka.model.AppNote


interface DatabaseRepository {
    val allNotes:LiveData<List<AppNote>>
    suspend fun insert(note:AppNote, onSuccess:()->Unit)
    suspend fun delete(note:AppNote, onSuccess:()->Unit)
    suspend fun editNote(note:AppNote, onSuccess:()->Unit)
    suspend fun update(note: AppNote, onSuccess: () -> Unit)
    suspend fun edit(note: AppNote, onSuccess: () -> Unit)
    fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit){}

    fun signOut(){}

}