package com.example.demo4ka.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.utilits.REPOSITORY
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel (application: Application):AndroidViewModel(application){

fun insert(note:AppNote,onSuccess:()->Unit) =
    viewModelScope .launch( Dispatchers.Main) {
        REPOSITORY.insert(note){
            onSuccess()
        }
    }

}