package com.example.mywork.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.mywork.R
import com.example.mywork.databinding.ActivityRegisterBinding
import com.example.mywork.ui.fragments.EnterPhoneNumberFragment
import com.example.mywork.utilits.initFirebase
import com.example.mywork.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        toolbar = binding.registerToolbar
        setSupportActionBar(toolbar)
        title = "Ваш телефон"
        replaceFragment(EnterPhoneNumberFragment(),true)

    }
}