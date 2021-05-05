package com.sahrial.submission_3_jetpackpro.data.source.control

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sahrial.submission_3_jetpackpro.data.source.control.api.ServiceAPI
import com.sahrial.submission_3_jetpackpro.data.source.control.response.ResponseMovie
import com.sahrial.submission_3_jetpackpro.data.source.control.response.ResponseTV
import com.sahrial.submission_3_jetpackpro.data.source.control.status.ResponseAPI
import com.sahrial.submission_3_jetpackpro.helper.IdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class ControlDataSource @Inject constructor(private val serviceApi: ServiceAPI) {

    fun getNowPlayingMovies(): LiveData<ResponseAPI<List<ResponseMovie>>> {
        IdlingResource.incr()
        val result = MutableLiveData<ResponseAPI<List<ResponseMovie>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = serviceApi.getNowPlayingMovies().await()
                result.postValue(ResponseAPI.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                result.postValue(
                    ResponseAPI.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        IdlingResource.decr()
        return result
    }

    fun getTvNow(): LiveData<ResponseAPI<List<ResponseTV>>> {
        IdlingResource.incr()
        val resultResponseTV = MutableLiveData<ResponseAPI<List<ResponseTV>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = serviceApi.getTvNow().await()
                resultResponseTV.postValue(ResponseAPI.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultResponseTV.postValue(
                    ResponseAPI.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        IdlingResource.decr()
        return resultResponseTV
    }

}