package com.abdulkarim.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abdulkarim.entity.notification.NotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

        @Insert
        suspend fun insertNotification(notification: NotificationEntity)

        @Query("DELETE FROM notifications")
        suspend fun deleteAllNotifications()

        @Query("DELETE FROM notifications WHERE id =:id")
        suspend fun deleteSingleNotification(id:Int)

        @Query("SELECT * FROM notifications ORDER by id DESC")
        fun getAllNotifications(): Flow<List<NotificationEntity>>

}