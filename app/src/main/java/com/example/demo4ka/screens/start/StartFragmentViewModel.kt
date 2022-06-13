package com.example.demo4ka.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.demo4ka.database.firebase.AppFirebaseRepository
import com.example.demo4ka.utilits.REPOSITORY
import com.example.demo4ka.utilits.showToast

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase() {
        REPOSITORY = AppFirebaseRepository()
    }

    fun signIn(onSuccess: () -> Unit) {
        REPOSITORY.connectToDatabase({ onSuccess() }, { showToast(it) })
    }

    fun reconnectFirebase(onSuccess: () -> Unit){
        REPOSITORY.reconnectToDatabase { onSuccess() }
    }
}