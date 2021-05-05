package com.sahrial.submission_3_jetpackpro.inject.main.favorite


import com.sahrial.submission_3_jetpackpro.ui.fragment.favorite.favmovie.FragmentFavMovie
import com.sahrial.submission_3_jetpackpro.ui.fragment.favorite.favtv.FragmentFavTv
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuilders {

    @ContributesAndroidInjector
    abstract fun contributeFragmentFavMovie() : FragmentFavMovie

    @ContributesAndroidInjector
    abstract fun contributeFragmentFavTv() : FragmentFavTv
}