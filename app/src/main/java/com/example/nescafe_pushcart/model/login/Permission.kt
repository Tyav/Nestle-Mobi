package com.example.nescafe_pushcart.model.login


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Permission(
    var createdAt: String?,
    var deletedAt: @RawValue Any?,
    var id: Int?,
    var permission: String?,
    var updatedAt: String?
):Parcelable