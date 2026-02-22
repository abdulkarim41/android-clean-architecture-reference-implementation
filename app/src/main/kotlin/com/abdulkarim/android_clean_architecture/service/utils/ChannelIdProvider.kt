package com.abdulkarim.android_clean_architecture.service.utils

import android.content.Context
import com.abdulkarim.designsystem.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import timber.log.Timber

/**
 * [ChannelIdProvider] is responsible for dynamically resolving the correct notification channel ID
 * based on the incoming notification's `clickAction` and the user's sound/vibration preferences.
 *
 * Responsibilities:
 * - Provides context-sensitive notification channel IDs for use with [NotificationCompat.Builder].
 * - Special-cases high-priority actions like trip requests to honor user-specific settings
 *   for sound and vibration preferences.
 * - Falls back to a default notification channel for all other types of actions.
 */

class ChannelIdProvider @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {
    fun getChannelId(clickAction: String): String {
        Timber.e("click action is : $clickAction")
        return context.getString(R.string.default_notification_id)
    }
}
