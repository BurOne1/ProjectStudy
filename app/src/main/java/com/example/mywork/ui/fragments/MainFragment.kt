package com.example.mywork.ui.fragments


import androidx.fragment.app.Fragment
import com.example.mywork.R
import com.example.mywork.utilits.APP_ACTIVITY


class MainFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Burvan App"
        APP_ACTIVITY.appDrawer.enableDrawer()
    }
}