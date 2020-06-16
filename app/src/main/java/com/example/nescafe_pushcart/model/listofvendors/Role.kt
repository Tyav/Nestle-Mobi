package com.example.nescafe_pushcart.model.listofvendors


import com.google.gson.annotations.SerializedName

data class Role(
    var authority: String?,
    var createdAt: String?,
    var deletedAt: Any?,
    var description: String?,
    var id: Int?,
    var name: String?,
    var permissions: List<Any?>?,
    var status: String?,
    var updatedAt: String?
)