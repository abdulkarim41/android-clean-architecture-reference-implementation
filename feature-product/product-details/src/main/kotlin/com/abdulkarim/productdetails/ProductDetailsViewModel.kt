package com.abdulkarim.productdetails

import com.abdulkarim.common.base.BaseViewModel
import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.apiusecase.product.FetchProductDetailsApiUseCase
import com.abdulkarim.entity.product.ProductDetailsApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val fetchProductDetailsApiUseCase: FetchProductDetailsApiUseCase
) : BaseViewModel() {

    val action: (ProductDetailsUiAction) -> Unit = {
        when (it) {
            is ProductDetailsUiAction.FetchProductDetailsApi -> {
                fetchProductDetailsApi(it.productId)
            }
        }
    }

    private val _uiState = MutableStateFlow<ProductDetailsUiState<Any>>(ProductDetailsUiState.Loading(true))
    val uiState get() = _uiState

    private fun fetchProductDetailsApi(productId: Int){
        execute {
            fetchProductDetailsApiUseCase.execute(productId).collect { result ->
                when(result){
                    is Result.Loading -> {
                        _uiState.value = ProductDetailsUiState.Loading(result.loading)
                    }
                    is Result.Success -> {
                        _uiState.value = ProductDetailsUiState.ProductDetails(result.data)
                    }
                    is Result.Error -> _uiState.value = ProductDetailsUiState.ApiError(result.message)
                }
            }
        }
    }

}

sealed interface ProductDetailsUiState<out ResultType> {
    data class Loading(val isLoading: Boolean) : ProductDetailsUiState<Loading>
    data class ProductDetails(val data: ProductDetailsApiEntity) : ProductDetailsUiState<ProductDetails>
    data class ApiError(val message: String) : ProductDetailsUiState<ApiError>
}

sealed interface ProductDetailsUiAction {
    data class FetchProductDetailsApi(val productId: Int) : ProductDetailsUiAction
}