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






    fun editNote(appNote: AppNote) {
    //    viewModelScope.launch(Dispatchers.Main) {
         //   val edit = appNote.copy(troubleCount = appNote.troubleCount, name = appNote.name, text = appNote.text, text2 = appNote.text2)
       //     REPOSITORY.editNote(edit) {}
        viewModelScope.launch(Dispatchers.Main) {
            REPOSITORY.edit(appNote) {
                appNote.copy(troubleCount = appNote.troubleCount, name = appNote.name, text = appNote.text, text2 = appNote.text2)
            }
        }
    }

}

