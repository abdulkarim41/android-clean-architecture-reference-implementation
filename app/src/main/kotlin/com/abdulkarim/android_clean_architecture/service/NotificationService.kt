package com.abdulkarim.android_clean_architecture.service

import com.abdulkarim.domain.notificationusecase.InsertNotificationUseCase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class NotificationService : FirebaseMessagingService() {

    @Inject lateinit var insertNotificationUseCase: InsertNotificationUseCase

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(serviceJob + Dispatchers.IO)

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.e(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val data = runCatching {
            NotificationParser.parse(remoteMessage.data)
        }.getOrElse {
            Timber.e(it, "Failed to parse FCM payload")
            return
        }

        serviceScope.launch {
            insertNotificationToDB(data)
        }

    }

    private suspend fun insertNotificationToDB(data: NotificationDataEntity) {
        if (data.title.isBlank()) {
            Timber.w("Skipping notification DB insert due to empty title")
            return
        }

        try {
            val notificationEntity = data.toNotificationEntity()
            insertNotificationUseCase.execute(notificationEntity)
        } catch (e: Exception) {
            Timber.e(e, "DB insert failed")
        }
    }

}