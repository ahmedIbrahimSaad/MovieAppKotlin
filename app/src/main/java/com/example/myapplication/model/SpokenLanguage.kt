package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpokenLanguage (
    var iso6391: String? = null,
    var name: String? = null

    ):Parcelable