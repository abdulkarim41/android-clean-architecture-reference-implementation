package com.abdulkarim.data.apiservice

import com.abdulkarim.apiresponse.product.ProductApiResponse
import com.abdulkarim.apiresponse.product.ProductDetailsApiResponse
import retrofit2.Response
import retrofit2.http.*

interface ProductApiService {

    @GET("products")
    suspend fun fetchProductsApi(
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 10,
    ) : Response<ProductApiResponse>

    @GET("products/{productId}")
    suspend fun fetchProductDetailsApi(
        @Path("productId") productId: Int,
    ) : Response<ProductDetailsApiResponse>

}