package com.example.proyecto_final_nicolas.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    val title: String,
    val description: String,
    @DrawableRes val imageResId: Int,
    val latitude: Double,
    val longitude: Double
) : Parcelable