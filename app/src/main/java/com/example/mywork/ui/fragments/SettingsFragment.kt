package com.example.mywork.ui.fragments

import android.view.*
import com.example.mywork.R
import com.example.mywork.utilits.*
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {



    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        settings_bio.text = USER.bio
        settings_full_name.text = USER.fullname
        settings_phone_number.text = USER.phone
        settings_status.text = USER.state
        settings_username.text = USER.username
        settings_post.text = USER.post
        settings_btn_change_username.setOnClickListener{ replaceFragment(ChangeUsernameFragment()) }
        settings_btn_change_bio.setOnClickListener{ replaceFragment(ChangeBioFragment()) }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_menu_exit -> {
                AppStates.updateState(AppStates.OFFLINE)
                AUTH.signOut()
                restartActivity()
            }
            R.id.settings_menu_name -> replaceFragment(ChangeNameFragment())
        }
        return true
    }




}