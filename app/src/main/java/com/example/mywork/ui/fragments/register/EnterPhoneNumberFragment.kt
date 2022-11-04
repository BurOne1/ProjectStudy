package com.example.mywork.ui.fragments.register

import androidx.fragment.app.Fragment
import com.example.mywork.MainActivity
import com.example.mywork.R
import com.example.mywork.utilits.AUTH
import com.example.mywork.utilits.replaceActivity
import com.example.mywork.utilits.replaceFragment
import com.example.mywork.utilits.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var phoneNumber:String
    private lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        //Проверяет авторизацию
        callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener() { task->
                    if (task.isSuccessful){
                        showToast("welcome")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(task.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(phoneNumber,id))
            }

        }
        register_btn_next.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (register_input_phone_number.text.toString().isEmpty()){
            showToast("Enter phone number")
        } else{
            authUser()
            //replaceFragment(EnterCodeFragment())
        }
    }

    private fun authUser() {
        phoneNumber = register_input_phone_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            callback
        )
    }

}