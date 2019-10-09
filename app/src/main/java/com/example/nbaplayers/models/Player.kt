package com.example.nbaplayers.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    val firstName: String,
    val lastName: String,
    val born: String,
    val hometown: String,
    val url: String,
    var id: Long = -1
) : Parcelable {}
