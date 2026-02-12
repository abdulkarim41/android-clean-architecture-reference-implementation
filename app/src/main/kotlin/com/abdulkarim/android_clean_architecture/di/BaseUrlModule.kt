package com.abdulkarim.android_clean_architecture.di

import com.abdulkarim.di.qualifer.AppBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BaseUrlModule{

    @Singleton
    @Provides
    @AppBaseUrl
    fun provideBaseUrl():String = "https://jsonplaceholder.typicode.com/"

}
