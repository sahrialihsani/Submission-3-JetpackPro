package com.sahrial.submission_3_jetpackpro.data.source.control.response

import com.google.gson.annotations.SerializedName

data class ResponseMovie (
    @SerializedName("id")
    var id:Int=0,
    @SerializedName("title")
    var title:String?=null,
    @SerializedName("vote_average")
    var vote_average: Float = 0F,
    @SerializedName("overview")
    var overview:String?=null,
    @SerializedName("release_date")
    var date:String?=null,
    @SerializedName("poster_path")
    var poster:String?=null,
    @SerializedName("backdrop_path")
    var backdrop:String?=null
)