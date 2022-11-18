package com.example.mywork.utilits

import androidx.recyclerview.widget.DiffUtil
import com.example.mywork.models.UserModel

class DiffUtilCallback(
    private val oldList: List<UserModel>,
    private val newList: List<UserModel>
):DiffUtil.Callback() {
    //передаём размер старого листа
    override fun getOldListSize(): Int = oldList.size
    //передаём размер нового листа
    override fun getNewListSize(): Int = newList.size


    // проверяет по идентификатору 1 (проверка по id)
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].timeStamp == newList[newItemPosition].timeStamp

    // 2 проверка (схематическая проверка)
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}