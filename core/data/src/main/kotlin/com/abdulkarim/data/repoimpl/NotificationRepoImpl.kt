package com.abdulkarim.data.repoimpl

import com.abdulkarim.cache.dao.NotificationDao
import com.abdulkarim.domain.repository.NotificationRepository
import com.abdulkarim.entity.notification.NotificationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepoImpl @Inject constructor(
    private val notificationDao: NotificationDao
): NotificationRepository {

    override suspend fun insertNotification(notificationEntity: NotificationEntity) {
        notificationDao.insertNotification(notificationEntity)
    }

    override fun getAllNotification(): Flow<List<NotificationEntity>> {
        return notificationDao.getAllNotifications()
    }

    override suspend fun deleteNotificationById(notificationId: Int) {
        return notificationDao.deleteSingleNotification(notificationId)
    }

}