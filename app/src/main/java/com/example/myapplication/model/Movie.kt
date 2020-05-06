package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var budget: Int? = null,
    var genres: List<Genre>? = null,
    var homepage: String? = null,
    var id: Int? = null,
    var imdbId: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var productionCompanies: List<ProductionCompany>? = null,
    var productionCountries: List<ProductionCountry>? = null,
    var releaseDate: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var spokenLanguages: List<SpokenLanguage>? = null,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null) : Parcelable