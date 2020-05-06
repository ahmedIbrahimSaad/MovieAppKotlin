package com.example.myapplication.client

import com.example.myapplication.modelkotlin.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WebServices {
@GET("search/movie")
fun getMovieByID(@Query("api_key") apiKey:String,@Query ("query") name:String,@Query("page")page:Int): Call<ResponseModel>
}