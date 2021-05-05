package com.sahrial.submission_3_jetpackpro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sahrial.submission_3_jetpackpro.data.source.RepositoryData
import com.sahrial.submission_3_jetpackpro.data.source.control.status.Resource
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import javax.inject.Inject

class MainviewModel @Inject constructor(private val localRepo: RepositoryData) : ViewModel() {

    fun getAllPlayMoviesNow(): LiveData<Resource<PagedList<MovEntity>>> = localRepo.getNowPlayingMovies()

    fun getAllTvNow(): LiveData<Resource<PagedList<TvEntity>>> = localRepo.getTvNow()

}