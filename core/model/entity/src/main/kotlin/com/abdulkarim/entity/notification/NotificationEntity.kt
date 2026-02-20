package com.abdulkarim.entity.notification

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val title:String? = "",
    val description:String? = "",
    val target:String? = "",
    val type:String? = "",
)
