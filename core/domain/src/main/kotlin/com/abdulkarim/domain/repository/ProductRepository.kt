package com.abdulkarim.domain.repository

import com.abdulkarim.common.base.Result
import com.abdulkarim.entity.product.ProductApiEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun fetchProductsApi() : Flow<Result<List<ProductApiEntity>>>

}