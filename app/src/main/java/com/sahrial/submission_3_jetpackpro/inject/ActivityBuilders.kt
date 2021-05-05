package com.sahrial.submission_3_jetpackpro.inject

import com.sahrial.submission_3_jetpackpro.inject.main.MainFragmentBuilders
import com.sahrial.submission_3_jetpackpro.ui.activity.MainActivity
import com.sahrial.submission_3_jetpackpro.ui.activity.detail.DetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilders {
    @ContributesAndroidInjector(modules = [MainFragmentBuilders::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity
}