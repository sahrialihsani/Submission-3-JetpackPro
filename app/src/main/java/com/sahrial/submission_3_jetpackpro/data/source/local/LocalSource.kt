package com.sahrial.submission_3_jetpackpro.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.room.LocalDao
import javax.inject.Inject

class LocalSource @Inject constructor(private val localDao: LocalDao) {
    
    //Movie
    fun getAllMovie() : DataSource.Factory<Int, MovEntity> = localDao.getAllMovies()

    fun getAllFavoriteMovies() : DataSource.Factory<Int, MovEntity> = localDao.getAllFavoriteMovies()
    
    fun getDetailMovie(movieId: Int) : LiveData<MovEntity> = localDao.getDetailMovieById(movieId)

    fun insertMovie(movies: List<MovEntity>) = localDao.insertMovie(movies)

    //Tv
    fun getAllTv() : DataSource.Factory<Int, TvEntity> = localDao.getAllTv()

    fun getAllFavoriteTv() : DataSource.Factory<Int, TvEntity> = localDao.getAllFavoriteTv()

    fun getDetailTv(tvId: Int) : LiveData<TvEntity> = localDao.getDetailTvById(tvId)

    fun insertTv(tvs: List<TvEntity>) = localDao.insertTv(tvs)

    fun setFavoriteMovie(movie : MovEntity) {
        movie.isFavorite = !movie.isFavorite
        localDao.updateMovie(movie)
    }

    fun setFavoriteTv(tv : TvEntity) {
        tv.isFavorite = !tv.isFavorite
        localDao.updateTv(tv)
    }
}