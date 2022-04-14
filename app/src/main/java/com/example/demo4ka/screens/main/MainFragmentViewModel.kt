package com.example.demo4ka.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.demo4ka.utilits.REPOSITORY

class MainFragmentViewModel(application: Application):AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes
}