package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCompany(
    var id: Int? = null,
    var logoPath: String? = null,
    var name: String? = null,
    var originCountry: String? = null

):Parcelable