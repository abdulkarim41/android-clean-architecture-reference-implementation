package com.abdulkarim.android_clean_architecture.di

import com.abdulkarim.data.repoimpl.RepositoryImpl
import com.abdulkarim.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository

}