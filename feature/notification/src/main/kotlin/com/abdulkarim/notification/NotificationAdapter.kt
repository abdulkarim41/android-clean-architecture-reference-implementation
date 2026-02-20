package com.abdulkarim.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.abdulkarim.common.base.BaseListAdapter
import com.abdulkarim.entity.notification.NotificationEntity
import com.abdulkarim.notification.databinding.ItemNotificationBinding


class NotificationAdapter : BaseListAdapter<NotificationEntity, ItemNotificationBinding>(
    diffCallback = object : DiffUtil.ItemCallback<NotificationEntity>() {
        override fun areItemsTheSame(oldItem: NotificationEntity, newItem: NotificationEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NotificationEntity, newItem: NotificationEntity): Boolean {
            return oldItem == newItem
        }
    }
) {


    override fun createBinding(parent: ViewGroup): ItemNotificationBinding =
        ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(binding: ItemNotificationBinding, item: NotificationEntity, position: Int) {

        if (item.title?.isNotEmpty() == true) binding.notificationTitleTv.text = item.title
        if (item.description?.isNotEmpty() == true) binding.notificationDesTv.text = item.description

    }

    fun getNotificationEntity(position: Int): NotificationEntity = currentList[position]


}

