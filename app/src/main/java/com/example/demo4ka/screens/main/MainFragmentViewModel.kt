package com.example.demo4ka.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes

    fun editItem(appNote: AppNote) {
        viewModelScope.launch(Dispatchers.Main) {
            val editNote = appNote.copy(troubleCount = appNote.troubleCount + 1)
            REPOSITORY.editNote(editNote) {}
        }
    }

    fun update(appNote: AppNote) {
        viewModelScope.launch(Dispatchers.Main) {
            REPOSITORY.update(appNote) {
            }
        }


    }

    fun signOut() {
        REPOSITORY.signOut()
    }
}