package com.abdulkarim.domain.repository

import com.abdulkarim.entity.notification.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun insertNotification(notificationEntity: NotificationEntity)
    fun getAllNotification():Flow<List<NotificationEntity>>
    suspend fun deleteNotificationById(notificationId:Int)
}