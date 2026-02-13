package com.abdulkarim.postlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.abdulkarim.common.base.BaseListAdapter
import com.abdulkarim.entity.PostApiEntity
import com.abdulkarim.postlist.databinding.ItemPostBinding
import com.abdulkarim.ui.utils.clickWithDebounce

class PostListAdapter(
    private val onItemClicked:(item: PostApiEntity) -> Unit,
) : BaseListAdapter<PostApiEntity, ItemPostBinding>(
    diffCallback = object : DiffUtil.ItemCallback<PostApiEntity>() {
        override fun areItemsTheSame(
            oldItem: PostApiEntity,
            newItem: PostApiEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PostApiEntity,
            newItem: PostApiEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
)  {
    override fun createBinding(parent: ViewGroup): ItemPostBinding =
        ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(
        binding: ItemPostBinding,
        item: PostApiEntity,
        position: Int
    ) {

        binding.titleTV.text = item.title
        binding.bodyTV.text = item.body

        binding.root.clickWithDebounce {
            onItemClicked.invoke(item)
        }
    }

}