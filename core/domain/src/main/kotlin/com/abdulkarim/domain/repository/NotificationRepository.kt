package com.abdulkarim.domain.repository

import com.abdulkarim.entity.notification.NotificationEntity

interface NotificationRepository {
    suspend fun insertNotification(notificationEntity: NotificationEntity)
}