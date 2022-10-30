package com.example.mywork.ui.fragments

import com.example.mywork.R
import com.example.mywork.utilits.*
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()

        initFullnameList()

    }

    private fun initFullnameList() {
        val fullmameList = USER.fullname.split(" ")
        if (fullmameList.size>1){
            settings_input_name.setText(fullmameList[0])
            settings_input_surname.setText(fullmameList[1])
        } else settings_input_name.setText(fullmameList[0])
    }


    override fun change() {
        val name = settings_input_name.text.toString()
        val surname = settings_input_surname.text.toString()
        if (name.isEmpty()){
            showToast("Имя не может быть пустым")
        } else {
            val fullname = "$name $surname"
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_FULLNAME)
                .setValue(fullname).addOnCompleteListener{
                    if (it.isSuccessful){
                        showToast("данные обнавлены")
                        USER.fullname = fullname
                        APP_ACTIVITY.appDrawer.updateHeader()
                        fragmentManager?.popBackStack()
                    }
                }
        }
    }

}