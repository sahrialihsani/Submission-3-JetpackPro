package com.sahrial.submission_3_jetpackpro.inject

import android.app.Application
import com.sahrial.submission_3_jetpackpro.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilders::class,
        ModuleApp::class,
        ModuleNetwork::class]
)
interface ComponentApp : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ComponentApp
    }
}