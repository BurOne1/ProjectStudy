package com.example.mywork


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.mywork.databinding.ActivityMainBinding
import com.example.mywork.ui.fragments.MainFragment
import com.example.mywork.ui.fragments.register.EnterPhoneNumberFragment
import com.example.mywork.ui.objects.AppDrawer
import com.example.mywork.utilits.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var appDrawer: AppDrawer
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP_ACTIVITY = this

        initFirebase()
        initUser {
//            CoroutineScope(Dispatchers.IO).launch {
//                initContacts()
//            }
            initFields()
            initFunc()
        }

    }




    private fun initFunc() {
        /* Функция инициализирует работу приложения */
        setSupportActionBar(toolbar)
        if(AUTH.currentUser!=null){
            appDrawer.create()
            replaceFragment(MainFragment(),false)
        } else {
            //false означает что мы не добавляем в стек фрашментов из которых можно вернуться назад
            replaceFragment(EnterPhoneNumberFragment(),false)

        }
    }



    private fun initFields(){
        toolbar = binding.mainToolbar
        appDrawer = AppDrawer()

    }

    override fun onStart() {
        super.onStart()
        AppStates.updateState(AppStates.ONLINE)
    }

    override fun onStop() {
        super.onStop()
        AppStates.updateState(AppStates.OFFLINE)
    }




}