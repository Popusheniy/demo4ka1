package com.example.demo4ka.database.firebase

import android.R.id
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import com.example.demo4ka.database.DatabaseRepository
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class AppFirebaseRepository : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val mDatabaseReference = FirebaseDatabase.getInstance().reference
        .child(mAuth.currentUser?.uid.toString())
    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()


    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = mDatabaseReference.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        mapNote[TROUBLECOUNT] = note.troubleCount

        mDatabaseReference.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun editNote(note: AppNote, onSuccess: () -> Unit) {
        val idNote = note.idFirebase
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        mapNote[TROUBLECOUNT] = note.troubleCount

        mDatabaseReference.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }


    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {

    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }

            }
    }


    override fun signOut() {
        mAuth.signOut()
    }
}