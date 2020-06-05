package com.example.nescafe_pushcart.model.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginBody(
    var email:String? = null,
    var password:String? = null
): Parcelable {
}