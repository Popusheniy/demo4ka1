package com.example.demo4ka.utilits

import com.example.demo4ka.MainActivity
import com.example.demo4ka.database.DatabaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

lateinit var APP_ACTIVITY:MainActivity


lateinit var REPOSITORY:DatabaseRepository

lateinit var AUTH:FirebaseAuth
lateinit var CURRENT_ID:String
lateinit var REF_DATABASE:DatabaseReference
lateinit var EMAIL:String
lateinit var PASSWORD:String
const val ADMIN_KEY = "GyA70572eRZEIvH8WyfelvCFLDh2"
const val ID_FIREBASE = "idFirebase"
const val NAME = "name"
const val TEXT = "text"
const val TEXT2 = "text2"
const val TROUBLECOUNT = "troubleCount"
const val PROGRESS = "progressStatus"