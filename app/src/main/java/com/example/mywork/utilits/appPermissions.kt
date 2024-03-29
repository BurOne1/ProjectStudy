package com.example.mywork.utilits

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val READ_CONTACTS = android.Manifest.permission.READ_CONTACTS
const val PERMISSION_REQUEST = 200

fun checkPermission(permission: String): Boolean {
    return if (ContextCompat.checkSelfPermission(APP_ACTIVITY,permission)!=PackageManager.PERMISSION_GRANTED){

        ActivityCompat.requestPermissions(APP_ACTIVITY, arrayOf(permission), PERMISSION_REQUEST)

        false
    } else true
}