package com.example.mywork.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mywork.MainActivity
import com.example.mywork.R
import com.example.mywork.utilits.APP_ACTIVITY

open class BaseFragment(val layout:Int) : Fragment(layout) {



    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.appDrawer.disableDrawer()
    }



}