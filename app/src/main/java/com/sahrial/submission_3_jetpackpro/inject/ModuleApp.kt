package com.sahrial.submission_3_jetpackpro.inject

import android.app.Application
import com.sahrial.submission_3_jetpackpro.data.source.RepositoryData
import com.sahrial.submission_3_jetpackpro.data.source.control.ControlDataSource
import com.sahrial.submission_3_jetpackpro.data.source.control.api.ServiceAPI
import com.sahrial.submission_3_jetpackpro.data.source.local.LocalSource
import com.sahrial.submission_3_jetpackpro.data.source.local.room.LocalDao
import com.sahrial.submission_3_jetpackpro.data.source.local.room.LocalDatabase
import com.sahrial.submission_3_jetpackpro.viewmodel.FactoryViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ModuleApp {

    companion object {

        @Singleton
        @Provides
        fun provideLocalDatabase(application: Application): LocalDatabase =
            LocalDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideLocalDao(localDb: LocalDatabase): LocalDao =
            localDb.localDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(localDao: LocalDao): LocalSource =
            LocalSource(localDao)

        @Singleton
        @Provides
        fun provideRemoteDataSource(api: ServiceAPI): ControlDataSource =
            ControlDataSource(api)

        @Singleton
        @Provides
        fun provideCatalogRepository(
            controlSource: ControlDataSource,
            localDataSource: LocalSource
        ): RepositoryData = RepositoryData(controlSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(localRepository: RepositoryData): FactoryViewModel =
            FactoryViewModel(localRepository)

    }
}