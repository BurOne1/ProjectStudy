package com.example.mywork.ui.fragments

import android.view.View
import com.example.mywork.R
import com.example.mywork.models.CommonModel
import com.example.mywork.models.UserModel
import com.example.mywork.utilits.*
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_single_chat.*
import kotlinx.android.synthetic.main.toolbar_info.view.*
import kotlin.contracts.contract


class SingleChatFragment(private val model: CommonModel) : BaseFragment(R.layout.fragment_single_chat) {

    private lateinit var listenerInfoToolbar:AppValueEventListener
    private lateinit var receivingUser:UserModel
    private lateinit var toolbarInfo:View
    private lateinit var refUser:DatabaseReference

    override fun onResume() {
        super.onResume()
        toolbarInfo = APP_ACTIVITY.toolbar.toolbar_info
        APP_ACTIVITY.toolbar.toolbar_info.visibility = View.VISIBLE
        listenerInfoToolbar = AppValueEventListener {
            receivingUser = it.getUserModel()
            initInfoToolbar()
        }


        refUser = REF_DATABASE_ROOT.child(NODE_USERS).child(model.id)
        refUser.addValueEventListener(listenerInfoToolbar)
        //кнопка льправки сообщения
        chat_btn_send_massage.setOnClickListener {
            val message = chat_input_message.text.toString()
            if (message.isEmpty()){
                showToast("Enter message")
            } else sendMessage(message,model.id, TYPE_TEXT){
                chat_input_message.setText("")
            }
        }
    }



    private fun initInfoToolbar() {
        toolbarInfo.toolbar_chat_fullname.text = receivingUser.fullname
        toolbarInfo.toolbar_chat_status.text = receivingUser.state
    }

    override fun onPause() {
        super.onPause()
        APP_ACTIVITY.toolbar.toolbar_info.visibility = View.GONE
        //отключаем слушатель во избежании утечек памяти
        refUser.removeEventListener(listenerInfoToolbar)
    }

}