package com.sahrial.submission_3_jetpackpro

import com.sahrial.submission_3_jetpackpro.inject.DaggerComponentApp
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerComponentApp.builder().application(this).build()

}