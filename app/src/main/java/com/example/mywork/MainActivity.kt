package com.example.mywork


import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.mywork.activities.RegisterActivity
import com.example.mywork.databinding.ActivityMainBinding
import com.example.mywork.ui.fragments.ChatsFragment
import com.example.mywork.ui.objects.AppDrawer
import com.example.mywork.utilits.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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
        if(AUTH.currentUser!=null){
            setSupportActionBar(toolbar)
            appDrawer.create()
            replaceFragment(ChatsFragment(),false)
        } else {
            replaceActivity(RegisterActivity())

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

    //запрос на считывание контактов
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
//            initContacts()
//        }
//    }



}