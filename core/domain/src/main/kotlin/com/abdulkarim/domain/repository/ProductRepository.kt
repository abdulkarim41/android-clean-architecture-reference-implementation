package com.abdulkarim.domain.repository

import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.apiusecase.product.FetchProductsApiUseCase
import com.abdulkarim.entity.product.ProductApiEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun fetchProductsApi(page: Int) : Flow<Result<List<ProductApiEntity>>>

}