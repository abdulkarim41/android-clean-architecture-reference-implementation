package com.abdulkarim.android_clean_architecture.di

import com.abdulkarim.data.repoimpl.CommonRepoImpl
import com.abdulkarim.data.repoimpl.CredentialRepoImpl
import com.abdulkarim.data.repoimpl.NotificationRepoImpl
import com.abdulkarim.data.repoimpl.RepositoryImpl
import com.abdulkarim.domain.repository.CommonRepository
import com.abdulkarim.domain.repository.CredentialRepository
import com.abdulkarim.domain.repository.NotificationRepository
import com.abdulkarim.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(commonRepoImpl: CommonRepoImpl): CommonRepository

    @Binds
    fun bindRepository(credentialRepoImpl: CredentialRepoImpl): CredentialRepository

    @Binds
    fun bindNotificationRepository(repositoryImpl: NotificationRepoImpl): NotificationRepository

}