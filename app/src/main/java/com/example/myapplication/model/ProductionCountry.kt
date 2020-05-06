package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCountry (

    var iso31661: String? = null,
    var name: String? = null

    ):Parcelable