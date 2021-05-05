package com.sahrial.submission_3_jetpackpro.inject

import com.sahrial.submission_3_jetpackpro.BuildConfig
import com.sahrial.submission_3_jetpackpro.data.source.control.api.ServiceAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ModuleNetwork {

    companion object {
        @Singleton
        @Provides
        fun provideClient(): OkHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()

        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        @Provides
        fun provideAPI(retrofit: Retrofit): ServiceAPI = retrofit.create(ServiceAPI::class.java)

    }

}