package com.abdulkarim.domain.apiusecase.product

import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.repository.ProductRepository
import com.abdulkarim.domain.usecase.ApiUseCaseParams
import com.abdulkarim.entity.product.ProductDetailsApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchProductDetailsApiUseCase @Inject constructor(
    private val productRepository: ProductRepository
): ApiUseCaseParams<Int, ProductDetailsApiEntity> {

    override suspend fun execute(params: Int): Flow<Result<ProductDetailsApiEntity>> {
        return productRepository.fetchProductDetailsApi(params)
    }

}