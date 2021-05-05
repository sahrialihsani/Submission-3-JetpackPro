package com.sahrial.submission_3_jetpackpro.data.source.control.response

import com.google.gson.annotations.SerializedName

data class ResponseTV(
    @SerializedName("id")
    var id:Int=0,
    @SerializedName("name")
    var title:String?=null,
    @SerializedName("vote_average")
    var vote_average: Float = 0F,
    @SerializedName("overview")
    var overview:String?=null,
    @SerializedName("first_air_date")
    var date:String?=null,
    @SerializedName("poster_path")
    var poster:String?=null,
    @SerializedName("backdrop_path")
    var backdrop:String?=null
)