package com.abdulkarim.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.abdulkarim.common.base.BaseListAdapter
import com.abdulkarim.entity.product.ProductApiEntity
import com.abdulkarim.postlist.databinding.ItemProductBinding
import com.abdulkarim.ui.extfun.clickWithDebounce

class ProductsAdapter(
    private val onItemClicked:(item: ProductApiEntity) -> Unit,
) : BaseListAdapter<ProductApiEntity, ItemProductBinding>(
    diffCallback = object : DiffUtil.ItemCallback<ProductApiEntity>() {
        override fun areItemsTheSame(
            oldItem: ProductApiEntity,
            newItem: ProductApiEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductApiEntity,
            newItem: ProductApiEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
)  {
    override fun createBinding(parent: ViewGroup): ItemProductBinding =
        ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(
        binding: ItemProductBinding,
        item: ProductApiEntity,
        position: Int
    ) {

        binding.titleTV.text = item.title
        binding.bodyTV.text = item.description

        binding.root.clickWithDebounce {
            onItemClicked.invoke(item)
        }
    }

}