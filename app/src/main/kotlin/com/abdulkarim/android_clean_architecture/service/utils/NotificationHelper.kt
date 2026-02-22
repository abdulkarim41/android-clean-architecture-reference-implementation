package com.abdulkarim.android_clean_architecture.service.utils

import android.content.Context
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import timber.log.Timber
import javax.inject.Inject
import android.Manifest
import android.content.pm.PackageManager
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * [NotificationHelper] is a utility class that safely handles notification delivery
 * through the Android system, abstracting permission checks and compatibility logic
 * across different OS versions.
 *
 * Core Responsibilities:
 * - Builds and displays a notification using [NotificationManagerCompat], while
 *   ensuring the necessary permissions (e.g., POST_NOTIFICATIONS on Android 13+).
 * - Prevents crashes due to missing permissions by catching [SecurityException]
 *   and logging the issue via Timber instead of failing silently or crashing.
 * - Offers a single entry point for triggering notifications from within the app’s
 *   notification pipeline.
 */

class NotificationHelper @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {

    private val notificationManager: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(context)
    }

    fun showNotification(id: Int, builder: NotificationCompat.Builder) {
        try {
            if (hasPermission()) {
                notificationManager.notify(id, builder.build())
            }
        } catch (e: SecurityException) {
            Timber.e(e, "SecurityException when trying to show notification")
        }
    }

    private fun hasPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.checkSelfPermission(
                context, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }
}
