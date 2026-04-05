package com.abdulkarim.data.repoimpl

import com.abdulkarim.data.apiservice.ProductApiService
import com.abdulkarim.common.base.Result
import com.abdulkarim.data.mapper.product.ProductsApiMapper
import com.abdulkarim.data.mapper.mapFromApiResponse
import com.abdulkarim.data.wrapper.NetworkBoundResource
import com.abdulkarim.domain.repository.ProductRepository
import com.abdulkarim.entity.ProductApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepoImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val productApiService: ProductApiService,
    private val productsApiMapper: ProductsApiMapper,

    ) : ProductRepository {

    override suspend fun fetchProductsApi(): Flow<Result<List<ProductApiEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResource.downloadData {
                productApiService.fetchProductsApi()
            },
            mapper = productsApiMapper
        )
    }

}