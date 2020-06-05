package com.example.nescafe_pushcart.model.login


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class SignInResponse(
    var `data`: Data?,
    var debugMessage: @RawValue Any?,
    var error: @RawValue Any?,
    var message: String?,
    var status: String?,
    var subErrors: @RawValue Any?,
    var timestamp: String?
):Parcelable