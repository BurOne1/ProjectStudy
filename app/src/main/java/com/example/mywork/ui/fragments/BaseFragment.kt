package com.example.mywork.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mywork.MainActivity
import com.example.mywork.R

open class BaseFragment(val layout:Int) : Fragment(layout) {



    override fun onStart() {
        super.onStart()
        (activity as MainActivity).appDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).appDrawer.enableDrawer()
    }

}