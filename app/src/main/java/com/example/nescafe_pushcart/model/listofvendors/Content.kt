package com.example.nescafe_pushcart.model.listofvendors


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Content(
    var avatar:@RawValue Any?,
    var contactAddress: String?,
    var createdAt: String?,
    var deletedAt:@RawValue Any?,
    var email: String?,
    var firstName: String?,
    var gender: String?,
    var id: Int?,
    var identificationNumber: String?,
    var lastName: String?,
    var password: String?,
    var phone: String?,
    var roles:@RawValue List<Role?>?,
    var status: String?,
    var updatedAt: String?,
    var userName: String?,
    var userType: String?
):Parcelable