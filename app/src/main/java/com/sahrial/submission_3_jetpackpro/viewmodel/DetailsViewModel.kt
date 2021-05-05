package com.sahrial.submission_3_jetpackpro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sahrial.submission_3_jetpackpro.data.source.RepositoryData
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val localRepo: RepositoryData) : ViewModel() {
    //Movie
    fun getMovieDetail(movId: Int): LiveData<MovEntity> =
        localRepo.getMovieDetail(movId)

    fun setFavoriteMovie(movie: MovEntity){
        localRepo.setFavoriteMovie(movie)
    }
    
    fun getTvDetail(tvId: Int): LiveData<TvEntity> =
        localRepo.getTvDetail(tvId)

    fun setFavoriteTv(tv: TvEntity){
        localRepo.setFavoriteTv(tv)
    }
}