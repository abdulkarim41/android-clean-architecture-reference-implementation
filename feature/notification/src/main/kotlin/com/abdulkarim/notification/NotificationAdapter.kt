package com.abdulkarim.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.abdulkarim.designsystem.R
import androidx.recyclerview.widget.DiffUtil
import com.abdulkarim.common.base.BaseListAdapter
import com.abdulkarim.entity.notification.NotificationEntity
import com.abdulkarim.notification.databinding.ItemNotificationBinding
import kotlin.collections.get

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

    private var colors = listOf(R.color.blue, R.color.green, R.color.purple, R.color.colorPrimary)

    override fun createBinding(parent: ViewGroup): ItemNotificationBinding =
        ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(
        binding: ItemNotificationBinding,
        item: NotificationEntity,
        position: Int
    ) {

        val index = if(position < 4 ) position else position % 4

        if (item.title?.isNotEmpty() == true) binding.notificationTitleTv.text = item.title
        if (item.description?.isNotEmpty() == true) binding.notificationDesTv.text = item.description

        binding.notificationIV.setColorFilter(ContextCompat.getColor(binding.root.context, colors[index]))
    }

    fun getNotificationEntity(position: Int): NotificationEntity = currentList[position]

}

