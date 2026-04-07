package com.abdulkarim.android_clean_architecture.application

import android.app.Application
import com.abdulkarim.android_clean_architecture.BuildConfig
import com.abdulkarim.android_clean_architecture.R
import com.abdulkarim.viewstate.ViewStateConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CleanArchitectureApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        // view global config
        ViewStateConfig.progressBarColor = R.color.colorPrimary
    }
}