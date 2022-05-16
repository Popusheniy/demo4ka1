package com.example.demo4ka.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.utilits.REPOSITORY
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application):AndroidViewModel(application) {
    fun delete(note:AppNote, onSuccess:()->Unit) = viewModelScope.launch(Dispatchers.IO) {
        REPOSITORY.delete(note){
            onSuccess()
        }

    }
}