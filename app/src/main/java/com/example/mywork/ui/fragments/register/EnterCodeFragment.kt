package com.example.mywork.ui.fragments.register

import androidx.fragment.app.Fragment
import com.example.mywork.MainActivity
import com.example.mywork.R
import com.example.mywork.utilits.*
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.title = phoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string = register_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }

        })
    }

    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = phoneNumber


                REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
                    .addListenerForSingleValueEvent(AppValueEventListener{

                        //Если в нашей ноде нет username то
                        if (!it.hasChild(NODE_USERNAMES)){
                            dateMap[CHILD_USERNAME] = uid
                        }

                        REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                            .addOnFailureListener { showToast(it.message.toString()) }
                            .addOnSuccessListener {
                                showToast("welcome")
                                restartActivity()
                                REF_DATABASE_ROOT.child(NODE_PHONES).child(phoneNumber).setValue(uid)
                                    .addOnFailureListener { showToast(it.message.toString()) }
                            }
                    })




            } else showToast(task.exception?.message.toString())
        }
    }
}