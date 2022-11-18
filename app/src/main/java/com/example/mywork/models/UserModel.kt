package com.example.mywork.models

data class UserModel (
    val id: String = "",
    var username: String = "",
    var bio: String = "tell us about yourself",
    var fullname: String = "no name",
    var state: String = "",
    val phone: String = "",
    val photoUrl: String = "",
    var post: String = "",

    var text: String = "",
    var type: String = "",
    var from: String = "",
    var timeStamp: Any = ""

) {
    override fun equals(other: Any?): Boolean {
        return (other as UserModel).id == id
    }
}