package com.example.myapplication.data

import com.example.myapplication.client.APIClient
import com.example.myapplication.model.Movie
import com.example.myapplication.modelkotlin.ResponseModel

class MovieRemoteDataSource: MovieDataSource {
    override fun getMoviesByName(api_key:String,movieName: String,page:Int)= APIClient.service.getMovieByID(api_key,movieName,page)
}