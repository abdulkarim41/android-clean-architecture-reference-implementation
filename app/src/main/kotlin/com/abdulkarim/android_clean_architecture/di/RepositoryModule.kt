package com.abdulkarim.android_clean_architecture.di

import com.abdulkarim.data.repoimpl.CommonRepoImpl
import com.abdulkarim.data.repoimpl.CredentialRepoImpl
import com.abdulkarim.data.repoimpl.NotificationRepoImpl
import com.abdulkarim.data.repoimpl.ProductRepoImpl
import com.abdulkarim.domain.repository.CommonRepository
import com.abdulkarim.domain.repository.CredentialRepository
import com.abdulkarim.domain.repository.NotificationRepository
import com.abdulkarim.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCredentialRepository(credentialRepoImpl: CredentialRepoImpl): CredentialRepository

    @Binds
    fun bindCommonRepository(commonRepoImpl: CommonRepoImpl): CommonRepository

    @Binds
    fun bindProductRepository(productRepoImpl: ProductRepoImpl): ProductRepository

    @Binds
    fun bindNotificationRepository(repositoryImpl: NotificationRepoImpl): NotificationRepository

}