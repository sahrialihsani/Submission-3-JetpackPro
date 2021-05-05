package com.sahrial.submission_3_jetpackpro.ui.fragment.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sahrial.submission_3_jetpackpro.R
import com.sahrial.submission_3_jetpackpro.adapter.favorite.ViewPagerAdapter
import com.sahrial.submission_3_jetpackpro.viewmodel.FactoryViewModel
import com.sahrial.submission_3_jetpackpro.viewmodel.FavViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject

class FragmentFavorite : DaggerFragment() {

    private lateinit var viewModel: FavViewModel

    @Inject
    lateinit var factory: FactoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let { configureViewpager(it) }
        viewModel = ViewModelProvider(this@FragmentFavorite, factory)[FavViewModel::class.java]
    }

    private fun configureViewpager(context: Context) {
        val sectionsPagerAdapter = ViewPagerAdapter(context, childFragmentManager)
        fav_pager.adapter = sectionsPagerAdapter
        fav_tab.setupWithViewPager(fav_pager)
    }

}