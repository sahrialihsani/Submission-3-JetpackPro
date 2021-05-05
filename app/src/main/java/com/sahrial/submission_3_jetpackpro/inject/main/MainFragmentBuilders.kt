package com.sahrial.submission_3_jetpackpro.inject.main

import com.sahrial.submission_3_jetpackpro.inject.main.favorite.FavoriteFragmentBuilders
import com.sahrial.submission_3_jetpackpro.ui.fragment.favorite.FragmentFavorite
import com.sahrial.submission_3_jetpackpro.ui.fragment.movie.FragmentMovies
import com.sahrial.submission_3_jetpackpro.ui.fragment.tv.FragmentTv
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilders {

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuilders::class])
    abstract fun contributeFragmentFavorite() : FragmentFavorite

    @ContributesAndroidInjector
    abstract fun contributeFragmentMovies() : FragmentMovies

    @ContributesAndroidInjector
    abstract fun contributeFragmentTv() : FragmentTv
}