package com.abdulkarim.domain.notificationusecase

import com.abdulkarim.domain.base.CoroutineUseCase
import com.abdulkarim.domain.repository.NotificationRepository
import javax.inject.Inject

class DeleteNotificationByIdUseCase  @Inject constructor(
    private val pushNotificationRepository: NotificationRepository
) : CoroutineUseCase<DeleteNotificationByIdUseCase.Params, Unit> {

    data class Params(val notificationId:Int)

    override suspend fun execute(params: Params?) {
        params?.notificationId?.let {
            pushNotificationRepository.deleteNotificationById(it)
        }
    }
}