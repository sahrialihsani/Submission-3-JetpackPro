package com.sahrial.submission_3_jetpackpro.ui.activity.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.sahrial.submission_3_jetpackpro.BuildConfig
import com.sahrial.submission_3_jetpackpro.R
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import com.sahrial.submission_3_jetpackpro.helper.Const
import com.sahrial.submission_3_jetpackpro.helper.Const.MOVTYPE
import com.sahrial.submission_3_jetpackpro.helper.Const.TVTYPE
import com.sahrial.submission_3_jetpackpro.helper.loadUrl
import com.sahrial.submission_3_jetpackpro.viewmodel.DetailsViewModel
import com.sahrial.submission_3_jetpackpro.viewmodel.FactoryViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.cardmovietv.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {
    companion object {
        const val EX_DATA = "ex_data"
        const val EX_TYPE = "ex_type"
    }
    private lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var vm: FactoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        
        configureViewmodel()

        val id = intent.getIntExtra(EX_DATA, 0)
        val type = intent.getStringExtra(EX_TYPE)

        if (type.equals(MOVTYPE, ignoreCase = true)) {
            supportActionBar?.title = getString(R.string.tabmovie)
            getDetailMovie(id)

        } else if (type.equals(TVTYPE, ignoreCase = true)) {
            supportActionBar?.title = getString(R.string.tabtv)
            getDetailTv(id)
        }

    }

    private fun getDetailMovie(movieId: Int) {
        viewModel.getMovieDetail(movieId).observe(this, Observer {
            showData(it, null)
        })
    }

    private fun getDetailTv(tv: Int) {
        viewModel.getTvDetail(tv).observe(this, Observer {
            it?.let {
                showData(null, it)
            }
        })
    }

    private fun configureViewmodel() {
        viewModel = ViewModelProvider(this@DetailActivity,vm)[DetailsViewModel::class.java]
    }

    private fun showData(movie: MovEntity?, tv: TvEntity?) {
        supportActionBar?.title=movie?.title?:tv?.title
        val dateFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val urlImage = movie?.poster ?: tv?.poster
        val urlHighlight = movie?.backdrop ?: tv?.backdrop
        val statusFavorite = movie?.isFavorite ?: tv?.isFavorite
        tv_detail_title.text = movie?.title ?: tv?.title
        tv_overview.text = movie?.overview ?: tv?.overview
        tv_detail_date.text = dateFormat.format(df.parse(movie?.date ?: tv?.date))
        tv_rate.text = movie?.vote_average?.toString() ?: tv?.vote_average.toString()
        statusFavorite?.let { status ->
            setState(status)
        }
        iv_detail_poster.loadUrl(BuildConfig.IMAGE_DB + Const.POSTERSIZE + urlImage)
        iv_backdrop.loadUrl(BuildConfig.IMAGE_DB + Const.POSTERSIZE + urlHighlight)

        fab_fav.setOnClickListener {
            setFavorite(movie, tv)
        }

    }

    private fun setState(status: Boolean){
        if (status) {
            fab_fav.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            fab_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun setFavorite(movie: MovEntity?, tv: TvEntity?) {
        if (movie != null) {
            if (movie.isFavorite){
                showSnackBar("${movie.title} ${getString(R.string.rmvFav)}")
            }else {
                showSnackBar("${movie.title} ${getString(R.string.addFav)}")
            }
            viewModel.setFavoriteMovie(movie)
        } else {
            if (tv != null) {
                if (tv.isFavorite){
                    showSnackBar("${tv.title} ${getString(R.string.rmvFav)}")
                }else {
                    showSnackBar("${tv.title} ${getString(R.string.addFav)}")
                }
                viewModel.setFavoriteTv(tv)
            }
        }
    }
}