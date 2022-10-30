package com.example.mywork.ui.fragments

import android.view.View
import com.example.mywork.R
import com.example.mywork.models.CommonModel
import com.example.mywork.utilits.APP_ACTIVITY
import kotlinx.android.synthetic.main.activity_main.view.*


class SingleChatFragment(model: CommonModel) : BaseFragment(R.layout.fragment_single_chat) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.toolbar.toolbar_info.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        APP_ACTIVITY.toolbar.toolbar_info.visibility = View.GONE
    }

}