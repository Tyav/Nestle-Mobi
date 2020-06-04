package com.example.nescafe_pushcart.model.login


import com.google.gson.annotations.SerializedName

data class SignInResponse(
    var `data`: Data?,
    var debugMessage: Any?,
    var error: Any?,
    var message: String?,
    var status: String?,
    var subErrors: Any?,
    var timestamp: String?
)