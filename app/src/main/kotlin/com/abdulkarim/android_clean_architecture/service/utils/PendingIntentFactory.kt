package com.abdulkarim.android_clean_architecture.service.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavDeepLinkBuilder
import com.abdulkarim.android_clean_architecture.R
import com.abdulkarim.android_clean_architecture.host.MainActivity
import com.abdulkarim.android_clean_architecture.service.NotificationDataEntity
import com.abdulkarim.common.utils.AppConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * [PendingIntentFactory] is responsible for generating context-aware [PendingIntent] instances
 * for push notification interactions. It serves as an abstraction layer that maps notification
 * actions (typically delivered as string constants in `clickAction`) to deep-linked destinations
 * within the app's navigation graph.
 *
 * This factory encapsulates:
 * - Destination resolution logic for all supported notification types.
 * - Navigation argument bundling when applicable (e.g., trip ID, credit history flag).
 * - Handling of custom behaviors (e.g., internal broadcasts for special flows).
 * - Safe fallback to [MainActivity] in unsupported or unknown scenarios.
 *
 * Key considerations:
 * - `clickAction` values should be validated upstream or standardized via sealed classes/enums
 *   in future iterations to reduce coupling with hardcoded string constants.
 * - This factory assumes that `nav_graph_credential` contains all referenced destinations.
 * - Broadcasts used here (e.g., trip request popups) must be handled appropriately by registered
 *   receivers scoped to the app lifecycle.
 */

class PendingIntentFactory @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {

    fun create(data: NotificationDataEntity): PendingIntent {
        val destination = when (data.clickAction) {

            AppConstants.LANDING_POSTS_FRAGMENT -> R.id.productsFragment

            else -> return defaultIntent()
        }

        return buildIntent(destination)
    }

    private fun buildIntent(destId: Int, args: Bundle? = null): PendingIntent {
        return NavDeepLinkBuilder(context)
            .setGraph(R.navigation.nav_graph_main)
            .setDestination(destId)
            .setArguments(args)
            .createPendingIntent()
    }

    private fun defaultIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

}