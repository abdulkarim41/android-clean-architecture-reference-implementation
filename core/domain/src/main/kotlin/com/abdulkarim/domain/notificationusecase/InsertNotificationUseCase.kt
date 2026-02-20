package com.abdulkarim.domain.notificationusecase

import com.abdulkarim.domain.base.CoroutineUseCase
import com.abdulkarim.domain.repository.NotificationRepository
import com.abdulkarim.entity.notification.NotificationEntity
import javax.inject.Inject

class InsertNotificationUseCase  @Inject constructor(
    private val repository: NotificationRepository
) : CoroutineUseCase<NotificationEntity, Unit> {

    data class Params(val notificationEntity: NotificationEntity)

    override suspend fun execute(params: NotificationEntity?){
        params?.let {
            repository.insertNotification(params)
        }
    }
}