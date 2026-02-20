package com.abdulkarim.domain.notificationusecase

import com.abdulkarim.domain.base.RoomUseCaseNonParams
import com.abdulkarim.domain.repository.NotificationRepository
import com.abdulkarim.entity.notification.NotificationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotificationUseCase  @Inject constructor(
    private val repository: NotificationRepository
) : RoomUseCaseNonParams<Flow<List<NotificationEntity>>> {

    override suspend fun execute(): Flow<List<NotificationEntity>> {
        return repository.getAllNotification()
    }
}