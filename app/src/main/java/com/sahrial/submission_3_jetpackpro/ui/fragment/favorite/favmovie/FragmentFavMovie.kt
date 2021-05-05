package com.sahrial.submission_3_jetpackpro.ui.fragment.favorite.favmovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahrial.submission_3_jetpackpro.R
import com.sahrial.submission_3_jetpackpro.adapter.AdapterMovie
import com.sahrial.submission_3_jetpackpro.adapter.callback.MovieCallback
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.helper.Const
import com.sahrial.submission_3_jetpackpro.ui.activity.detail.DetailActivity
import com.sahrial.submission_3_jetpackpro.viewmodel.FactoryViewModel
import com.sahrial.submission_3_jetpackpro.viewmodel.FavViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.blank_layout.*
import kotlinx.android.synthetic.main.fragment_fav_movie.*
import javax.inject.Inject

class FragmentFavMovie : DaggerFragment(), MovieCallback {

    private lateinit var viewModel: FavViewModel

    @Inject
    lateinit var factory: FactoryViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showRecyclerview()

        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory)[FavViewModel::class.java]
        }
        getFavoriteMovie()

    }

    private fun getFavoriteMovie() {
        viewModel.getAllFavoriteMovie().observe(viewLifecycleOwner, Observer {
            if (it != null){
                rv_fav_movie.adapter?.let {adapter ->
                    when (adapter) {
                        is AdapterMovie -> {
                            if (it.isNullOrEmpty()){
                                rv_fav_movie.visibility = View.GONE
                                emptyMovie()
                            } else {
                                rv_fav_movie.visibility = View.VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }
    private fun showRecyclerview() {
        rv_fav_movie.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = AdapterMovie(this@FragmentFavMovie)
        }
    }

    private fun emptyMovie() {
        img_fav.setImageResource(R.drawable.ic_baseline_favorite_border)
        img_fav.contentDescription =
                resources.getString(R.string.nofav)
        tv_no_fav.text = resources.getString(R.string.nofav)
        empty.visibility = View.VISIBLE
    }

    override fun itemClicked(data: MovEntity) {
        startActivity(
        Intent(context,DetailActivity::class.java).putExtra(DetailActivity.EX_DATA, data.movieId) .putExtra(DetailActivity.EX_TYPE, Const.MOVTYPE))
    }

}