package com.example.mywork.models

data class User (
    val id: String = "",
    var username: String = "",
    var bio: String = "default",
    var fullname: String = "",
    var state: String = "",
    val phone: String = "",
    val photoUrl: String = "",
    val post: String = "",
)