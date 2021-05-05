package com.sahrial.submission_3_jetpackpro.ui.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sahrial.submission_3_jetpackpro.R
import com.sahrial.submission_3_jetpackpro.adapter.AdapterMovie
import com.sahrial.submission_3_jetpackpro.ui.activity.detail.DetailActivity
import com.sahrial.submission_3_jetpackpro.adapter.callback.MovieCallback
import com.sahrial.submission_3_jetpackpro.data.source.control.status.Status
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.helper.Const.MOVTYPE
import com.sahrial.submission_3_jetpackpro.viewmodel.FactoryViewModel

import com.sahrial.submission_3_jetpackpro.viewmodel.MainviewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class FragmentMovies : DaggerFragment(), MovieCallback {

    private lateinit var viewModel: MainviewModel

    @Inject
    lateinit var factory: FactoryViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showRecyclerview()

        activity?.let { configureViewmodel(it) }
        getAllMovies()

    }

    private fun configureViewmodel(fragmentActivity: FragmentActivity) {
        viewModel = ViewModelProvider(fragmentActivity, factory)[MainviewModel::class.java]
    }

    private fun getAllMovies() {
        viewModel.getAllPlayMoviesNow().observe(viewLifecycleOwner, Observer { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> pb_movie.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        pb_movie.visibility = View.GONE
                        rv_movie.adapter?.let { adapter ->
                            when (adapter) {
                                is AdapterMovie -> {
                                    adapter.submitList(listMovie.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }!!
                    }
                    Status.ERROR -> {
                        pb_movie.visibility = View.GONE
                        Toast.makeText(context, getString(R.string.nointernet), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showRecyclerview() {
        rv_movie.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = AdapterMovie(this@FragmentMovies)
        }
    }

    override fun itemClicked(data: MovEntity) {
        startActivity(Intent(context,DetailActivity::class.java).putExtra(DetailActivity.EX_DATA, data.movieId).putExtra(DetailActivity.EX_TYPE, MOVTYPE))
    }

}