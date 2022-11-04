package com.example.mywork.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mywork.R
import com.example.mywork.databinding.FragmentChatsBinding
import com.example.mywork.utilits.APP_ACTIVITY

class ChatsFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Burvan App"
        APP_ACTIVITY.appDrawer.enableDrawer()
    }

}