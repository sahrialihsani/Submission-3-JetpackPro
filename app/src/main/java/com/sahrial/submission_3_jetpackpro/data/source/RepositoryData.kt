package com.sahrial.submission_3_jetpackpro.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sahrial.submission_3_jetpackpro.data.source.control.ControlDataSource
import com.sahrial.submission_3_jetpackpro.data.source.control.response.ResponseMovie
import com.sahrial.submission_3_jetpackpro.data.source.control.response.ResponseTV
import com.sahrial.submission_3_jetpackpro.data.source.control.status.Resource
import com.sahrial.submission_3_jetpackpro.data.source.control.status.ResponseAPI
import com.sahrial.submission_3_jetpackpro.data.source.local.LocalSource
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


class RepositoryData @Inject constructor(
    private val controlSource: ControlDataSource,
    private val localSource: LocalSource
) : SourceData {
    //MOVIE
    override fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovEntity>>> {
        return object : NetworkRes<PagedList<MovEntity>, List<ResponseMovie>>() {
            public override fun loadDB(): LiveData<PagedList<MovEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localSource.getAllMovie(), config).build()
            }

            override fun fetchData(data: PagedList<MovEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun call(): LiveData<ResponseAPI<List<ResponseMovie>>> =
                controlSource.getNowPlayingMovies()

            public override fun saveCallResult(data: List<ResponseMovie>) {
                val movieList = ArrayList<MovEntity>()
                for (item in data) {
                    val movie = MovEntity(
                        null,
                        item.id,
                        item.title,
                        item.vote_average,
                        item.overview,
                        item.date,
                        item.poster,
                        item.backdrop,
                        false
                    )
                    movieList.add(movie)
                }

                localSource.insertMovie(movieList)
            }

        }.liveData()
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<MovEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localSource.getAllFavoriteMovies(), config).build()
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovEntity> =
        localSource.getDetailMovie(movieId)

    override fun setFavoriteMovie(movie: MovEntity) {
        CoroutineScope(IO).launch {
            localSource.setFavoriteMovie(movie)
        }
    }

    //TV
    override fun getTvNow(): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkRes<PagedList<TvEntity>, List<ResponseTV>>() {
            public override fun loadDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localSource.getAllTv(), config).build()
            }

            override fun fetchData(data: PagedList<TvEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun call(): LiveData<ResponseAPI<List<ResponseTV>>> =
                controlSource.getTvNow()


            public override fun saveCallResult(data: List<ResponseTV>) {
                val tvList = ArrayList<TvEntity>()
                for (item in data) {
                    val tv = TvEntity(
                        null,
                        item.id,
                        item.title,
                        item.vote_average,
                        item.overview,
                        item.date,
                        item.poster,
                        item.backdrop,
                        false
                    )
                    tvList.add(tv)
                }

                localSource.insertTv(tvList)
            }

        }.liveData()
    }

    override fun getAllFavoriteTv(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localSource.getAllFavoriteTv(), config).build()
    }

    override fun getTvDetail(tvId: Int): LiveData<TvEntity> =
        localSource.getDetailTv(tvId)

    override fun setFavoriteTv(tv: TvEntity) {
        CoroutineScope(IO).launch {
            localSource.setFavoriteTv(tv)
        }
    }
}