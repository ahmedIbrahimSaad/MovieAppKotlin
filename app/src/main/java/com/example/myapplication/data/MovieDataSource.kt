package com.example.myapplication.data


import com.example.myapplication.model.Movie
import com.example.myapplication.modelkotlin.ResponseModel
import retrofit2.Call

open  interface MovieDataSource {
    fun getMoviesByName(api_key:String,movieName:String,page:Int): Call<ResponseModel>
}