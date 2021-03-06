package com.example.demo4ka.screens.edit_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.utilits.REF_DATABASE
import com.example.demo4ka.utilits.REPOSITORY
import com.example.demo4ka.utilits.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditNoteViewModel(application: Application) : AndroidViewModel(application) {

    fun editNote(appNote: AppNote, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.edit(appNote) {
                onSuccess()
            }
        }
    }

}

