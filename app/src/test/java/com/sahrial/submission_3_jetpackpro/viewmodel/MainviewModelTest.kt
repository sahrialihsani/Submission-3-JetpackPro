package com.sahrial.submission_3_jetpackpro.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.sahrial.submission_3_jetpackpro.data.source.RepositoryData
import com.sahrial.submission_3_jetpackpro.data.source.control.status.Resource
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainviewModelTest {
    private lateinit var vm: MainviewModel
    @get:Rule
    var instantExecutor= InstantTaskExecutorRule()

    @Mock
    private lateinit var repositoryData: RepositoryData

    @Mock
    private lateinit var observeMov: Observer<Resource<PagedList<MovEntity>>>

    @Mock
    private lateinit var observeTv: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var movListPage: PagedList<MovEntity>

    @Mock
    private lateinit var tvlistPage: PagedList<TvEntity>

    @Before
    fun setUp() {
        vm = MainviewModel(repositoryData)
    }

    //Movie
    @Test
    fun getAllPlayMoviesNow() {
        val movDummy = Resource.success(movListPage)
        Mockito.`when`(movDummy.data?.size).thenReturn(5)
        val data = MutableLiveData<Resource<PagedList<MovEntity>>>()
        data.value = movDummy

        Mockito.`when`(repositoryData.getNowPlayingMovies()).thenReturn(data)
        val movieEntity = vm.getAllPlayMoviesNow().value?.data
        Mockito.verify(repositoryData).getNowPlayingMovies()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        vm.getAllPlayMoviesNow().observeForever(observeMov)
        Mockito.verify(observeMov).onChanged(movDummy)
    }

    @Test
    fun getAllTvNow() {
        val tvDummy = Resource.success(tvlistPage)
        Mockito.`when`(tvDummy.data?.size).thenReturn(5)
        val data = MutableLiveData<Resource<PagedList<TvEntity>>>()
        data.value = tvDummy

        Mockito.`when`(repositoryData.getTvNow()).thenReturn(data)
        val tvEntity = vm.getAllTvNow().value?.data
        Mockito.verify(repositoryData).getTvNow()
        assertNotNull(tvEntity)
        assertEquals(5, tvEntity?.size)

        vm.getAllTvNow().observeForever(observeTv)
        Mockito.verify(observeTv).onChanged(tvDummy)
    }
}