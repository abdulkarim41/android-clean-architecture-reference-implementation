package com.abdulkarim.android_clean_architecture.di

import android.content.Context
import com.abdulkarim.securestorage.SecureStorage
import com.abdulkarim.securestorage.SecureStorageImpl
import com.abdulkarim.sharedpref.SharedPrefHelper
import dagger.Binds
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

@Module
@InstallIn(SingletonComponent::class)
abstract class SecureStorageModule {

    @Binds
    @Singleton
    abstract fun bindSecureStorage(
        secureStorageImpl: SecureStorageImpl
    ): SecureStorage
}