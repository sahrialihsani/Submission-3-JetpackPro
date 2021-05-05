package com.sahrial.submission_3_jetpackpro.data.source.control.response

import com.google.gson.annotations.SerializedName

data class ResponseAll<T>(
    @SerializedName("status_message")
    val status:String?=null,
    @SerializedName("status_code")
    val code:Int?=null,
    @SerializedName("results")
    val result: List<T>? = null
)