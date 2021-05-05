package com.sahrial.submission_3_jetpackpro.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.sahrial.submission_3_jetpackpro.data.source.RepositoryData
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import com.sahrial.submission_3_jetpackpro.helper.DummyData
import org.junit.*

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {
    private val movData = DummyData.generateMovData()[0]
    private val movId = movData.movieId
    private val tvData = DummyData.generateTvData()[0]
    private val tvId = tvData.tvId
    private lateinit var vm: DetailsViewModel

    @get:Rule
    var instantExecutor= InstantTaskExecutorRule()
    @Mock
    private lateinit var repositoryData: RepositoryData
    @Mock
    private lateinit var observeMov: Observer<MovEntity>
    @Mock
    private lateinit var observeTv: Observer<TvEntity>
    @Before
    fun setUp() {
        vm = DetailsViewModel(repositoryData)
    }
    //Movie
    @Test
    fun getMovieDetail() {
        val movDummy = MutableLiveData<MovEntity>()
        movDummy.value = movData
        Mockito.`when`(repositoryData.getMovieDetail(movId)).thenReturn(movDummy)
        val data = vm.getMovieDetail(movId).value
        assertNotNull(data)
        assertEquals(movData.id, data?.id)
        assertEquals(movData.movieId, data?.movieId)
        assertEquals(movData.title, data?.title)
        assertEquals(movData.vote_average, data?.vote_average)
        assertEquals(movData.overview, data?.overview)
        assertEquals(movData.date, data?.date)
        assertEquals(movData.poster, data?.poster)
        assertEquals(movData.backdrop, data?.backdrop)
        vm.getMovieDetail(movId).observeForever(observeMov)
        verify(observeMov).onChanged(movData)
    }
    //TV
    @Test
    fun getTvDetail() {
        val tvDummy = MutableLiveData<TvEntity>()
        tvDummy.value = tvData
        Mockito.`when`(repositoryData.getTvDetail(tvId)).thenReturn(tvDummy)
        val data = vm.getTvDetail(tvId).value
        assertNotNull(data)
        assertEquals(tvData.id, data?.id)
        assertEquals(tvData.tvId, data?.tvId)
        assertEquals(tvData.title, data?.title)
        assertEquals(tvData.vote_average, data?.vote_average)
        assertEquals(tvData.overview, data?.overview)
        assertEquals(tvData.date, data?.date)
        assertEquals(tvData.poster, data?.poster)
        assertEquals(tvData.backdrop, data?.backdrop)
        vm.getTvDetail(tvId).observeForever(observeTv)
        verify(observeTv).onChanged(tvData)
    }
}