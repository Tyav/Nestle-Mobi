package com.example.nescafe_pushcart.model.listofvendors


import com.google.gson.annotations.SerializedName

data class VendorList(
    var `data`: Data?,
    var debugMessage: Any?,
    var error: Any?,
    var message: String?,
    var status: String?,
    var subErrors: Any?,
    var timestamp: String?
)