package com.abdulkarim.android_clean_architecture.service

import com.abdulkarim.entity.notification.NotificationEntity

data class NotificationDataEntity(
    val title : String,
    val description : String,
    val image : String,
    val clickAction : String,
    val clickActionData : String
)

// prepare for room database
fun NotificationDataEntity.toNotificationEntity(): NotificationEntity {
    return NotificationEntity(
        title = this.title,
        description = this.description,
        target = this.clickActionData,
        type = this.clickAction,
    )
}

