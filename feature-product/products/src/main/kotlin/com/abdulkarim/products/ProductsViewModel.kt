package com.abdulkarim.products

import com.abdulkarim.common.base.Result
import com.abdulkarim.common.base.BaseViewModel
import com.abdulkarim.domain.apiusecase.product.FetchProductsApiUseCase
import com.abdulkarim.entity.product.ProductApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val fetchProductsApiUseCase: FetchProductsApiUseCase
) : BaseViewModel() {

    val action: (ProductsUiAction) -> Unit = {
        when (it) {
            is ProductsUiAction.FetchProductsApi -> fetchProductsApi()
        }
    }

    private val _uiState = MutableStateFlow<ProductsUiState<Any>>(ProductsUiState.Loading(false))
    val uiState get() = _uiState

    init { fetchProductsApi() }

    private fun fetchProductsApi(){
        execute {
            fetchProductsApiUseCase.execute().collect { result ->
                when(result){
                    is Result.Loading -> _uiState.value = ProductsUiState.Loading(result.loading)
                    is Result.Success -> _uiState.value = ProductsUiState.Products(result.data)
                    is Result.Error -> _uiState.value = ProductsUiState.ApiError(result.message)
                }
            }
        }
    }

}

sealed interface ProductsUiState<out ResultType> {
    data class Loading(val isLoading: Boolean) : ProductsUiState<Loading>
    data class Products(val data: List<ProductApiEntity>) : ProductsUiState<Products>
    data class ApiError(val message: String) : ProductsUiState<ApiError>
}

sealed interface ProductsUiAction {
    data object FetchProductsApi : ProductsUiAction
}