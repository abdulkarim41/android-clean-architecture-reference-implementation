package com.abdulkarim.android_clean_architecture.service.utils

import android.content.Context
import android.graphics.Bitmap
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.abdulkarim.designsystem.R
import com.abdulkarim.android_clean_architecture.service.NotificationDataEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * [NotificationManager] is the central component responsible for displaying rich push notifications
 * based on incoming notification payloads ([NotificationDataEntity]) in the Rental Owner app.
 *
 * Primary responsibilities:
 * - Parsing and handling incoming notification data.
 * - Constructing a context-aware [NotificationCompat.Builder] instance.
 * - Dynamically resolving deep links and destinations using [PendingIntentFactory].
 * - Enhancing user experience with BigPicture styles when an image URL is present.
 * - Delegating the actual rendering to [NotificationHelper].
 *
 * Key behaviors:
 * - Dynamically determines notification channels via [ChannelIdProvider] based on action type.
 * - Uses [BitmapFetcher] to load notification images asynchronously.
 * - Supports both text-only and image-based notification styles (BigText and BigPicture).
 * - Ensures each notification is cancellable, vibrates on delivery, and respects system sound settings.
 */

class NotificationManager @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val notificationHelper: NotificationHelper,
    private val pendingIntentFactory: PendingIntentFactory,
    private val channelIdProvider: ChannelIdProvider,
    private val bitmapFetcher: BitmapFetcher
) {

    suspend fun handleNotification(data: NotificationDataEntity) {

        val pendingIntent = pendingIntentFactory.create(data)
        val channelId = channelIdProvider.getChannelId(data.clickAction)

        val builder = NotificationCompat.Builder(context, channelId).apply {
            setContentTitle(data.title)
            setContentText(data.description)
            setStyle(NotificationCompat.BigTextStyle().bigText(data.description))
            setSmallIcon(R.drawable.ic_notification_rounded)
            setVibrate(longArrayOf(0, 2000))
            setAutoCancel(true)
            setPriority(NotificationCompat.PRIORITY_HIGH)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            setContentIntent(pendingIntent)
        }

        if (data.image.isNotBlank()) {
            val bitmap = bitmapFetcher.fetch(data.image)
            bitmap?.let {
                builder.setLargeIcon(it)
                builder.setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(it)
                        .bigLargeIcon(null as Bitmap?)
                )
            }
        }

        notificationHelper.showNotification(data.clickAction.hashCode(), builder)
    }
}
