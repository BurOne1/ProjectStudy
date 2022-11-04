package com.example.mywork.utilits

import androidx.recyclerview.widget.DiffUtil
import com.example.mywork.models.UserModel

class DiffUtilCalback(
    private val oldList: List<UserModel>,
    private val newList: List<UserModel>
):DiffUtil.Callback {
}