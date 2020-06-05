package com.example.nescafe_pushcart.model.login


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Data(
    var email: String?,
    var firstName: String?,
    var lastName: String?,
    var password: @RawValue Any?,
    var phone: String?,
    var roles: @RawValue List<Role?>?,
    var token: String?
): Parcelable