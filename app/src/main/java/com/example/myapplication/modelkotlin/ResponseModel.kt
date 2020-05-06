package com.example.myapplication.modelkotlin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
):Parcelable