package com.abdulkarim.android_clean_architecture.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NotificationService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.e(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.e("notification $remoteMessage")

    }

}