package com.abdulkarim.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdulkarim.cache.dao.NotificationDao
import com.abdulkarim.entity.notification.NotificationEntity

@Database(entities = [NotificationEntity::class], version = 1,exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}