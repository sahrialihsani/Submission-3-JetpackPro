package com.sahrial.submission_3_jetpackpro.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sahrial.submission_3_jetpackpro.LiveDataTest
import com.sahrial.submission_3_jetpackpro.PagingTest
import com.sahrial.submission_3_jetpackpro.data.source.control.ControlDataSource
import com.sahrial.submission_3_jetpackpro.data.source.control.status.Resource
import com.sahrial.submission_3_jetpackpro.data.source.local.LocalSource
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import com.sahrial.submission_3_jetpackpro.helper.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RepositoryDataTest {
    @get:Rule
    var taskExecutor = InstantTaskExecutorRule()

    //Inisiasi Fake Data
    private val control = mock(ControlDataSource::class.java)
    private val local = mock(LocalSource::class.java)
    private val repoDataSource= FakeRepositoryData(control, local)

    //Inisisasi data dummy
    private val allMovie = DummyData.generateMovData()
    private val allTv = DummyData.generateTvData()
    private val chooseMovie = DummyData.generateMovData()[1]
    private val chooseTv = DummyData.generateTvData()[1]
    //Movie
    @Test
    fun getNowPlayingMovies() {
        val factory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovEntity>
        Mockito.`when`(local.getAllMovie()).thenReturn(factory)
        repoDataSource.getNowPlayingMovies()
        val movEntity = Resource.success(PagingTest.listPage(DummyData.generateMovData()))
        Mockito.verify(local).getAllMovie()
        assertNotNull(movEntity.data)
        assertEquals(allMovie.size.toLong(), movEntity.data?.size?.toLong())

    }
    @Test
    fun getMovieDetail() {
        val movDummmy = MutableLiveData<MovEntity>()
        movDummmy.value = chooseMovie
        Mockito.`when`(local.getDetailMovie(chooseMovie.movieId)).thenReturn(movDummmy)

        val data = LiveDataTest.value(repoDataSource.getMovieDetail(chooseMovie.movieId))
        Mockito.verify(local).getDetailMovie(chooseMovie.movieId)
        assertNotNull(data)
        assertEquals(chooseMovie.movieId, data.movieId)
    }
    @Test
    fun getAllFavoriteMovies() {
        val factory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovEntity>
        Mockito.`when`(local.getAllFavoriteMovies()).thenReturn(factory)
        repoDataSource.getAllFavoriteMovies()
        val movieEntity = Resource.success(PagingTest.listPage(DummyData.generateMovData()))
        Mockito.verify(local).getAllFavoriteMovies()
        assertNotNull(movieEntity.data)
        assertEquals(allMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }
    //TV
    @Test
    fun getTvNow() {
        val factory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getAllTv()).thenReturn(factory)
        repoDataSource.getTvNow()
        val tvEntity = Resource.success(PagingTest.listPage(DummyData.generateMovData()))
        Mockito.verify(local).getAllTv()
        assertNotNull(tvEntity.data)
        assertEquals(allTv.size.toLong(), tvEntity.data?.size?.toLong())
    }
    @Test
    fun getTvDetail() {
        val tvDummy = MutableLiveData<TvEntity>()
        tvDummy.value = chooseTv
        Mockito.`when`(local.getDetailTv(chooseTv.tvId)).thenReturn(tvDummy)
        val data = LiveDataTest.value(repoDataSource.getTvDetail(chooseTv.tvId))
        Mockito.verify(local).getDetailTv(chooseTv.tvId)
        assertNotNull(data)
        assertEquals(chooseTv.tvId, data.tvId)
    }
    @Test
    fun getAllFavoriteTv() {
        val factory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getAllFavoriteTv()).thenReturn(factory)
        repoDataSource.getAllFavoriteTv()
        val tvEntity = Resource.success(PagingTest.listPage(DummyData.generateTvData()))
        Mockito.verify(local).getAllFavoriteTv()
        assertNotNull(tvEntity.data)
        assertEquals(allTv.size.toLong(), tvEntity.data?.size?.toLong())
    }
}