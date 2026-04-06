package com.abdulkarim.products

import android.os.Bundle
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.products.databinding.FragmentProductsBinding
import com.abdulkarim.ui.extfun.navigate
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
                navigate(uri = getString(R.string.deep_link_product_details_fragment).toUri())
            }
        )

        requireContext().setUpVerticalRecyclerView(binding.productsRV, adapter)
        binding.productsRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.productsRV.canScrollVertically(RecyclerView.FOCUS_DOWN) && adapter.itemCount > 9) {
                    viewModel.action(ProductsUiAction.FetchNextPageAvailableProductsApi)
                }
            }
        })

    }

    private fun handleUiState(state: ProductsUiState<Any>) {
        when (state) {
            is ProductsUiState.Loading -> {
                //binding.viewState.showLoading()
            }
            is ProductsUiState.Products -> {
                //binding.viewState.showSuccess()
                adapter.submitList(state.data)
            }
            is ProductsUiState.ApiError -> {
//                binding.viewState.showError(
//                    message = state.message,
//                    retryAction = {
//                        viewModel.action(ProductsUiAction.FetchAvailableProductsApi)
//                    }
//                )
            }

            ProductsUiState.EmptyProduct -> {}
            is ProductsUiState.LoadingMore -> binding.progressbar.isVisible = state.isLoading
        }
    }

}