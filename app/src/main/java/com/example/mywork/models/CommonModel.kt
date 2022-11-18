package com.example.mywork.models

data class CommonModel(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "no name",
    var state: String = "",
    val phone: String = "",
    val photoUrl: String = "",
    val post: String = "",

    var text: String = "",
    var type: String = "",
    var from: String = "",
    var timeStamp: Any = ""
)