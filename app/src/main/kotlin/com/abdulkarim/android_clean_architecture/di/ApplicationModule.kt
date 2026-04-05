package com.abdulkarim.android_clean_architecture.di

import android.content.Context
import com.abdulkarim.sharedpref.SharedPrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun sharePrefHelper(@ApplicationContext context: Context): SharedPrefHelper = SharedPrefHelper(context)


}