package com.abdulkarim.productdetails

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.entity.product.ProductDetailsApiEntity
import com.abdulkarim.productdetails.databinding.FragmentProductDetailsBinding
import com.abdulkarim.ui.extfun.clickWithDebounce
import com.abdulkarim.ui.extfun.loadImage
import com.abdulkarim.ui.extfun.popBack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>() {

    override fun viewBindingLayout() = FragmentProductDetailsBinding.inflate(layoutInflater)

    private val arg by navArgs<ProductDetailsFragmentArgs>()
    private val viewmodel by viewModels<ProductDetailsViewModel>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewmodel.uiState bindTo :: handleUiState

        viewmodel.action(ProductDetailsUiAction.FetchProductDetailsApi(arg.productId))

        binding.backBtn.clickWithDebounce {
            popBack()
        }
    }

    private fun handleUiState(state: ProductDetailsUiState<Any>) {
        when (state) {
            is ProductDetailsUiState.Loading -> {}
            is ProductDetailsUiState.ProductDetails -> {
                dataBindWithUI(state.data)
            }
            is ProductDetailsUiState.ApiError -> {

            }
        }
    }

    private fun dataBindWithUI(data : ProductDetailsApiEntity){
        binding.productImage.loadImage(
            placeholder = R.drawable.ic_launcher_background,
            url = data.thumbnail
        )
        binding.titleTv.text = data.title
        binding.brandTv.text = data.brand
        binding.priceTv.text = "$ ${data.price}"
        binding.discountTv.text = "${data.discountPercentage}% OFF"
        binding.ratingTv.text = data.rating.toString()
        binding.stockTv.text = if (data.stock > 0) "In Stock" else "Out of Stock"
        binding.categoryTv.text = "Category: ${data.category}"
        binding.descriptionTv.text = data.description
    }

}