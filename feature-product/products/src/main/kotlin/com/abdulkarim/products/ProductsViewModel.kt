package com.abdulkarim.products

import com.abdulkarim.common.base.Result
import com.abdulkarim.common.base.BaseViewModel
import com.abdulkarim.domain.apiusecase.product.FetchProductsApiUseCase
import com.abdulkarim.entity.product.ProductApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val fetchProductsApiUseCase: FetchProductsApiUseCase
) : BaseViewModel() {

    val action: (ProductsUiAction) -> Unit = {
        when (it) {
            is ProductsUiAction.FetchAvailableProductsApi -> {
                pageNumber = 1
                isPageLoadFinished = false
                productList.clear()
                fetchProductsApi(pageNumber)
            }
            is ProductsUiAction.FetchNextPageAvailableProductsApi -> {
                if (!isPageLoadFinished) {
                    pageNumber++
                    fetchProductsApi(pageNumber)
                }
            }
        }
    }

    private var pageNumber = 1
    private var isPageLoadFinished = false

    private val productList = mutableListOf<ProductApiEntity>()

    private val _uiState = MutableStateFlow<ProductsUiState<Any>>(ProductsUiState.Loading(false))
    val uiState get() = _uiState

    init { fetchProductsApi(pageNumber) }

    private fun fetchProductsApi(page: Int){
        execute {
            fetchProductsApiUseCase.execute(page).collect { result ->
                when(result){
                    is Result.Loading -> {
                        if (productList.isNotEmpty()) _uiState.value = ProductsUiState.LoadingMore(result.loading)
                        else _uiState.value = ProductsUiState.Loading(result.loading)
                    }
                    is Result.Success -> {
                        if (result.data.isEmpty() && productList.isEmpty())
                            _uiState.value = ProductsUiState.EmptyProduct
                        else {
                            if (result.data.isEmpty()) {
                                isPageLoadFinished = true
                                return@collect
                            }
                            productList.addAll(result.data)
                            _uiState.value = ProductsUiState.Products(productList)
                        }
                    }
                    is Result.Error -> _uiState.value = ProductsUiState.ApiError(result.message)
                }
            }
        }
    }

}

sealed interface ProductsUiState<out ResultType> {
    data class Loading(val isLoading: Boolean) : ProductsUiState<Loading>
    data class LoadingMore(val isLoading:Boolean): ProductsUiState<LoadingMore>
    data class Products(val data: List<ProductApiEntity>) : ProductsUiState<Products>
    data object EmptyProduct : ProductsUiState<Products>
    data class ApiError(val message: String) : ProductsUiState<ApiError>
}

sealed interface ProductsUiAction {
    data object FetchAvailableProductsApi : ProductsUiAction
    data object FetchNextPageAvailableProductsApi : ProductsUiAction
}