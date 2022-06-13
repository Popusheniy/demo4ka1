package com.example.demo4ka.database.firebase

import androidx.lifecycle.LiveData
import com.example.demo4ka.database.DatabaseRepository
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class AppFirebaseRepository : DatabaseRepository {



    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = REF_DATABASE.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        mapNote[TEXT2] = note.text2
        mapNote[TROUBLECOUNT] = note.troubleCount
        mapNote[PROGRESS] = note.progressStatus



        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun update(note: AppNote, onSuccess: () -> Unit) {
        val idNote = note.idFirebase
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        mapNote[TEXT2] = note.text2
        mapNote[TROUBLECOUNT] = note.troubleCount
        mapNote[PROGRESS] = note.progressStatus

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun edit(note: AppNote, onSuccess: () -> Unit) {
        val idNote = note.idFirebase
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        mapNote[TEXT2] = note.text2
        mapNote[TROUBLECOUNT] = note.troubleCount
        mapNote[PROGRESS] = note.progressStatus

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }


    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        REF_DATABASE.child(note.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun editNote(note: AppNote, onSuccess: () -> Unit) {
        val idNote = note.idFirebase
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        mapNote[TEXT2] = note.text2
        mapNote[TROUBLECOUNT] = note.troubleCount
        mapNote[PROGRESS] = note.progressStatus

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }

            }
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(CURRENT_ID)
    }

    override fun reconnectToDatabase(onSuccess: () -> Unit) {
        try {
            CURRENT_ID = AUTH.currentUser?.uid.toString()
            REF_DATABASE = FirebaseDatabase.getInstance().reference
                .child(CURRENT_ID)
            onSuccess()
        } catch (e:Exception ){

        }
    }


    override fun signOut() {
        AUTH.signOut()
    }
}