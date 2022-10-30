package com.example.mywork.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mywork.MainActivity
import com.example.mywork.R

open class BaseChangeFragment (layout:Int): Fragment(layout) {

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        (activity as MainActivity).appDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_confirm_change -> change()
        }
        return true
    }

    open fun change() {

    }

}