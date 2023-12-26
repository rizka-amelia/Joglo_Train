package com.example.joglotrain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var id: Int = 0,
    var username: String = "",
    var phoneNumber: String = "",
    var email: String = "",
    var password: String = "",
) : Parcelable