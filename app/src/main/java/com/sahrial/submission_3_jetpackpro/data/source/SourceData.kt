package com.sahrial.submission_3_jetpackpro.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sahrial.submission_3_jetpackpro.data.source.control.status.Resource
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity


interface SourceData {
    //Movie
    fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovEntity>>>

    fun getAllFavoriteMovies(): LiveData<PagedList<MovEntity>>

    fun getMovieDetail(movieId: Int): LiveData<MovEntity>

    fun setFavoriteMovie(movie: MovEntity)

    //Tv
    fun getTvNow(): LiveData<Resource<PagedList<TvEntity>>>

    fun getAllFavoriteTv(): LiveData<PagedList<TvEntity>>

    fun getTvDetail(tvId: Int): LiveData<TvEntity>

    fun setFavoriteTv(tv: TvEntity)

}