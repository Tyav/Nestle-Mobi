package com.example.nescafe_pushcart.model.login


import com.google.gson.annotations.SerializedName

data class Role(
    var authority: String?,
    var createdAt: String?,
    var deletedAt: Any?,
    var description: String?,
    var id: Int?,
    var name: String?,
    var permissions: List<Permission?>?,
    var status: String?,
    var updatedAt: String?
)