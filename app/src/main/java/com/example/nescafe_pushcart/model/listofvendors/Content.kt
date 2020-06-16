package com.example.nescafe_pushcart.model.listofvendors


import com.google.gson.annotations.SerializedName

data class Content(
    var avatar: Any?,
    var contactAddress: String?,
    var createdAt: String?,
    var deletedAt: Any?,
    var email: String?,
    var firstName: String?,
    var gender: String?,
    var id: Int?,
    var identificationNumber: String?,
    var lastName: String?,
    var password: String?,
    var phone: String?,
    var roles: List<Role?>?,
    var status: String?,
    var updatedAt: String?,
    var userName: String?,
    var userType: String?
)