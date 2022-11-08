package com.steve_md.testapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// DTO
@Parcelize
data class UserModel (
    var id: Int?  = null,
    var name:String? = null,
    var email:String? = null,
    var password:String? = null
) : Parcelable