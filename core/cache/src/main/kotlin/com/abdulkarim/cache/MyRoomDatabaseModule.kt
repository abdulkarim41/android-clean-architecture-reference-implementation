package com.abdulkarim.cache

import android.app.Application
import androidx.room.Room
import com.abdulkarim.cache.dao.NotificationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyRoomDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application):MyRoomDatabase {
        return Room.databaseBuilder(
            application,
            MyRoomDatabase::class.java,
            "abdulkarim.db"
        ).fallbackToDestructiveMigration(true).build()
    }

    @Singleton
    @Provides
    fun provideNotificationDao(myRoomDatabase: MyRoomDatabase): NotificationDao{
        return myRoomDatabase.notificationDao()
    }

}
