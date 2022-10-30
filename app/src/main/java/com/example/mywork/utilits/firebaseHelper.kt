package com.example.mywork.utilits

import android.provider.ContactsContract
import com.example.mywork.models.CommonModel
import com.example.mywork.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH:FirebaseAuth
lateinit var CURRENT_UID:String
lateinit var REF_DATABASE_ROOT:DatabaseReference
lateinit var USER:User

const val NODE_USERNAMES = "usernames"
const val NODE_USERS = "users"
const val NODE_PHONES = "phones"

const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"
const val CHILD_FULLNAME = "fullname"
const val CHILD_BIO = "bio"
const val CHILD_STATE = "state"

fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = User()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
}

inline fun initUser(crossinline function: () -> Unit) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
        .addListenerForSingleValueEvent(AppValueEventListener{
            // если null выполнется правая часть ?:
            USER = it.getValue(User::class.java) ?: User()
            if (USER.username.isEmpty()){
                USER.username = CURRENT_UID
            }
            function()
        })
}

fun updatePhonesToDatabase() {
    REF_DATABASE_ROOT.child(NODE_PHONES).addListenerForSingleValueEvent(
        AppValueEventListener{
            it.children.forEach {snapshot ->

            }
        }
    )
}

//fun initContacts() {
//    if (checkPermission(READ_CONTACTS)){
//        var arrayContacts = arrayListOf<CommonModel>()
//        val cursor = APP_ACTIVITY.contentResolver.query(
//            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//            null,
//            null,
//            null,
//            null
//        )
//        //Функция let считывает cursor только в том случае если он не null
//        cursor?.let{
//            while (it.moveToNext()){
//                val  fullname = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
//                val phone
//            }
//        }
//
//    }
//}