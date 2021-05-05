package com.sahrial.submission_3_jetpackpro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sahrial.submission_3_jetpackpro.data.source.RepositoryData
import javax.inject.Inject

class FactoryViewModel @Inject constructor(private val localRepo: RepositoryData): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainviewModel::class.java) -> {
                MainviewModel(localRepo) as T
            }
            modelClass.isAssignableFrom(DetailsViewModel::class.java) -> {
                DetailsViewModel(localRepo) as T
            }
            modelClass.isAssignableFrom(FavViewModel::class.java) -> {
                FavViewModel(localRepo) as T
            }
            else -> throw Throwable("Can't View Viewmodel: " + modelClass.name)
        }

    }
}