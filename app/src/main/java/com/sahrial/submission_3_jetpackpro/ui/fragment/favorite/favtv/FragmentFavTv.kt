package com.sahrial.submission_3_jetpackpro.ui.fragment.favorite.favtv

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
import com.sahrial.submission_3_jetpackpro.adapter.AdapterTv
import com.sahrial.submission_3_jetpackpro.adapter.callback.TvCallback
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import com.sahrial.submission_3_jetpackpro.helper.Const
import com.sahrial.submission_3_jetpackpro.ui.activity.detail.DetailActivity
import com.sahrial.submission_3_jetpackpro.viewmodel.FactoryViewModel
import com.sahrial.submission_3_jetpackpro.viewmodel.FavViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.blank_layout.*
import kotlinx.android.synthetic.main.fragment_fav_movie.*
import kotlinx.android.synthetic.main.fragment_fav_tv.*
import javax.inject.Inject

class FragmentFavTv : DaggerFragment(), TvCallback {

    private lateinit var viewModel: FavViewModel

    @Inject
    lateinit var factory: FactoryViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showRecyclerview()

        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory)[FavViewModel::class.java]
        }
        getFavoriteTv()

    }

    private fun getFavoriteTv() {
        viewModel.getAllFavoriteTv().observe(viewLifecycleOwner, Observer {
            if (it != null){
                rv_fav_tv.adapter?.let {adapter ->
                    when (adapter) {
                        is AdapterTv -> {
                            if (it.isNullOrEmpty()){
                                rv_fav_tv.visibility = View.GONE
                                emptyTv()
                            } else {
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
        rv_fav_tv.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = AdapterTv(this@FragmentFavTv)
        }
    }

    private fun emptyTv() {
        img_fav.setImageResource(R.drawable.ic_baseline_favorite_border)
        img_fav.contentDescription =
                resources.getString(R.string.nofav)
        tv_no_fav.text = resources.getString(R.string.nofav)
        emptyTv.visibility = View.VISIBLE
        emptyTv.visibility = View.VISIBLE
    }

    override fun itemClicked(data: TvEntity) {
        startActivity(Intent(context,DetailActivity::class.java).putExtra(DetailActivity.EX_DATA, data.tvId).putExtra(DetailActivity.EX_TYPE, Const.TVTYPE))
    }

}