package com.sahrial.submission_3_jetpackpro.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sahrial.submission_3_jetpackpro.data.source.control.status.Resource
import com.sahrial.submission_3_jetpackpro.data.source.control.status.ResponseAPI
import com.sahrial.submission_3_jetpackpro.data.source.control.status.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkRes<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val source = loadDB()

        result.addSource(source) { data ->
            result.removeSource(source)
            if (fetchData(data)) {
                loadData(source)
            } else {
                result.addSource(source) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }
    private fun loadData(source: LiveData<ResultType>) {
        val apiResponse = call()

        result.addSource(source) { newData ->
            result.value = Resource.loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(source)
            when (response.status) {
                ResponseStatus.SUCCESS ->
                    CoroutineScope(IO).launch {
                        response.body?.let { saveCallResult(it) }
                        Log.d("1 : ", response.status.name)

                        withContext(Main) {
                            result.addSource(loadDB()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }

                    }

                ResponseStatus.ERROR -> {
                    onFetchFailed()
                    Log.d("2 : ", response.status.name)
                    result.addSource(source) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }
    private fun onFetchFailed() {}

    protected abstract fun loadDB(): LiveData<ResultType>

    protected abstract fun fetchData(data: ResultType?): Boolean

    protected abstract fun call(): LiveData<ResponseAPI<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    fun liveData(): LiveData<Resource<ResultType>> = result
}