package com.example.nescafe_pushcart.model.login


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Role(
    var authority: String?,
    var createdAt: String?,
    var deletedAt: @RawValue Any?,
    var description: String?,
    var id: Int?,
    var name: String?,
    var permissions: List<Permission?>?,
    var status: String?,
    var updatedAt: String?
):Parcelable