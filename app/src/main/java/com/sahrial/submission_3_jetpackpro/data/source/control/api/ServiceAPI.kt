package com.sahrial.submission_3_jetpackpro.data.source.control.api

import com.sahrial.submission_3_jetpackpro.BuildConfig
import com.sahrial.submission_3_jetpackpro.data.source.control.response.ResponseAll
import com.sahrial.submission_3_jetpackpro.data.source.control.response.ResponseMovie
import com.sahrial.submission_3_jetpackpro.data.source.control.response.ResponseTV
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {
    //Movie
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Call<ResponseAll<ResponseMovie>>

    //Tv
    @GET("tv/on_the_air")
    fun getTvNow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Call<ResponseAll<ResponseTV>>

}