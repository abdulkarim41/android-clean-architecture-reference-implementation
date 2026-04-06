package com.abdulkarim.data.repoimpl

import com.abdulkarim.data.apiservice.ProductApiService
import com.abdulkarim.common.base.Result
import com.abdulkarim.data.mapper.product.ProductsApiMapper
import com.abdulkarim.data.mapper.mapFromApiResponse
import com.abdulkarim.data.mapper.product.ProductDetailsApiMapper
import com.abdulkarim.data.wrapper.NetworkBoundResource
import com.abdulkarim.domain.repository.ProductRepository
import com.abdulkarim.entity.product.ProductApiEntity
import com.abdulkarim.entity.product.ProductDetailsApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepoImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val productApiService: ProductApiService,
    private val productsApiMapper: ProductsApiMapper,
    private val productDetailsApiMapper: ProductDetailsApiMapper,

 ) : ProductRepository {

    override suspend fun fetchProductsApi(page: Int): Flow<Result<List<ProductApiEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResource.downloadData {
                productApiService.fetchProductsApi(page)
            },
            mapper = productsApiMapper
        )
    }

    override suspend fun fetchProductDetailsApi(productId: Int): Flow<Result<ProductDetailsApiEntity>> {
        return mapFromApiResponse(
            result = networkBoundResource.downloadData {
                productApiService.fetchProductDetailsApi(productId)
            },
            mapper = productDetailsApiMapper
        )
    }

}