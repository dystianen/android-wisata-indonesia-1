package com.example.android_wisata_indonesia_1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wisata(
    val id: Int,
    val name: String,
    val location: String,
    val description: String
) : Parcelable
