package com.sahrial.submission_3_jetpackpro.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.sahrial.submission_3_jetpackpro.data.source.RepositoryData
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import org.junit.*

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavViewModelTest {
    private lateinit var vm: FavViewModel
    @get:Rule
    var instantExecutor= InstantTaskExecutorRule()

    @Mock
    private lateinit var repositoryData: RepositoryData

    @Mock
    private lateinit var observeMov: Observer<PagedList<MovEntity>>

    @Mock
    private lateinit var observeTv: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var movListPage: PagedList<MovEntity>

    @Mock
    private lateinit var tvlistPage: PagedList<TvEntity>

    @Before
    fun setUp() {
        vm = FavViewModel(repositoryData)
    }
    @Test
    fun getAllFavoriteMovie() {
        val movDummy = movListPage
        Mockito.`when`(movDummy.size).thenReturn(5)
        val data = MutableLiveData<PagedList<MovEntity>>()
        data.value = movDummy
        Mockito.`when`(repositoryData.getAllFavoriteMovies()).thenReturn(data)
        val movEntity = vm.getAllFavoriteMovie().value
        Mockito.verify(repositoryData).getAllFavoriteMovies()
        assertNotNull(movEntity)
        assertEquals(5, movEntity?.size)
        vm.getAllFavoriteMovie().observeForever(observeMov)
        Mockito.verify(observeMov).onChanged(movDummy)
    }

    @Test
    fun getAllFavoriteTv() {
        val tvDummy = tvlistPage
        Mockito.`when`(tvDummy.size).thenReturn(5)
        val data = MutableLiveData<PagedList<TvEntity>>()
        data.value = tvDummy
        Mockito.`when`(repositoryData.getAllFavoriteTv()).thenReturn(data)
        val tvEntity = vm.getAllFavoriteTv().value
        Mockito.verify(repositoryData).getAllFavoriteTv()
        assertNotNull(tvEntity)
        assertEquals(5, tvEntity?.size)
        vm.getAllFavoriteTv().observeForever(observeTv)
        Mockito.verify(observeTv).onChanged(tvDummy)

    }
}