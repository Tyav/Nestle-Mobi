package com.example.nescafe_pushcart.model.login


import com.google.gson.annotations.SerializedName

data class Data(
    var email: String?,
    var firstName: String?,
    var lastName: String?,
    var password: Any?,
    var phone: String?,
    var roles: List<Role?>?,
    var token: String?
)