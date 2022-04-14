package com.example.demo4ka.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.demo4ka.database.firebase.AppFirebaseRepository
import com.example.demo4ka.database.room.AppRoomDatabase
import com.example.demo4ka.database.room.AppRoomRepository
import com.example.demo4ka.utilits.REPOSITORY
import com.example.demo4ka.utilits.TYPE_FIREBASE
import com.example.demo4ka.utilits.TYPE_ROOM
import com.example.demo4ka.utilits.showToast
import io.reactivex.internal.operators.single.SingleDoOnSuccess

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
private val mContext = application

    fun initDatabase(type:String, onSuccess:()->Unit) {
    when(type){
        TYPE_ROOM -> {
            val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
            REPOSITORY = AppRoomRepository(dao)
            onSuccess()
        }

        TYPE_FIREBASE -> {
            REPOSITORY = AppFirebaseRepository()
            REPOSITORY.connectToDatabase({onSuccess()},{ showToast(it)})
        }

    }
    }
}