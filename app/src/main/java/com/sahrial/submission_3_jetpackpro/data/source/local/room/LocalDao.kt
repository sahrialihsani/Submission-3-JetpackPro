package com.sahrial.submission_3_jetpackpro.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.Dao
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity

@Dao
interface LocalDao {
    //Movie
    @Query("SELECT * FROM tb_mov")
    fun getAllMovies() : DataSource.Factory<Int, MovEntity>

    @Query("SELECT * FROM tb_mov WHERE isFav = 1")
    fun getAllFavoriteMovies() : DataSource.Factory<Int, MovEntity>

    @Query("SELECT * FROM tb_mov WHERE movId = :movId")
    fun getDetailMovieById(movId: Int) : LiveData<MovEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovEntity::class)
    fun insertMovie(movies: List<MovEntity>)

    @Update(entity = MovEntity::class)
    fun updateMovie(movie : MovEntity)

    //Tv
    @Query("SELECT * FROM tb_tv")
    fun getAllTv() : DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tb_tv WHERE isFav = 1")
    fun getAllFavoriteTv() : DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tb_tv WHERE tvId = :tvId")
    fun getDetailTvById(tvId: Int) : LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvEntity::class)
    fun insertTv(tv: List<TvEntity>)

    @Update(entity = TvEntity::class)
    fun updateTv(tv: TvEntity)

}