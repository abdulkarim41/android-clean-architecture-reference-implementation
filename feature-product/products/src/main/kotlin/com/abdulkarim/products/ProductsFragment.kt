package com.abdulkarim.products

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.postlist.databinding.FragmentProductsBinding
import com.abdulkarim.ui.extfun.setUpVerticalRecyclerView
import com.abdulkarim.ui.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class ProductsFragment : BaseFragment<FragmentProductsBinding>() {

    override fun viewBindingLayout() = FragmentProductsBinding.inflate(layoutInflater)

    private val viewModel by viewModels<ProductsViewModel>()
    private var adapter by autoCleared<ProductsAdapter>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.uiState bindTo :: handleUiState

        adapter = ProductsAdapter(
            onItemClicked = {
                showMessage(it.title)
            }
        )

        requireContext().setUpVerticalRecyclerView(binding.postListRV, adapter)

    }

    private fun handleUiState(state: ProductsUiState<Any>) {
        when (state) {
            is ProductsUiState.Loading -> {
                binding.viewState.showLoading()
            }
            is ProductsUiState.Products -> {
                binding.viewState.showSuccess()
                adapter.submitList(state.data)
            }
            is ProductsUiState.ApiError -> {
                binding.viewState.showError(
                    message = state.message,
                    retryAction = {
                        viewModel.action(ProductsUiAction.FetchProductsApi)
                    }
                )
            }
        }
    }

}