package com.abdulkarim.android_clean_architecture.service

/**
 * [NotificationParser] is a utility object responsible for mapping raw push notification
 * payloads (typically from FCM) into structured [NotificationDataEntity] instances.
 *
 * Responsibilities:
 * - Extracts predefined keys from a `Map<String, String>` payload.
 * - Ensures no `null` values are propagated by using `orEmpty()` on each field.
 * - Serves as the single source of truth for interpreting push notification fields,
 *   ensuring consistency across the notification pipeline.
 */

object NotificationParser {
    fun parse(map: Map<String, String>): NotificationDataEntity {
        return NotificationDataEntity(
            title = map["title"].orEmpty(),
            description = map["description"].orEmpty(),
            image = map["image"].orEmpty(),
            clickAction = map["click_action"].orEmpty(),
            clickActionData = map["click_action_data"].orEmpty()
        )
    }
}
