package com.abdulkarim.data.mapper.product

import com.abdulkarim.apiresponse.ProductApiResponse
import com.abdulkarim.data.mapper.Mapper
import com.abdulkarim.entity.ProductApiEntity
import javax.inject.Inject

class ProductsApiMapper @Inject constructor() :
    Mapper<List<ProductApiResponse>, List<ProductApiEntity>> {

    override fun mapFromApiResponse(type: List<ProductApiResponse>): List<ProductApiEntity> {
        return type.map { item ->
            ProductApiEntity(
                id = item.id ?: "",
                title = item.title ?: "",
                description = item.description ?: "",
                category = item.category ?: "",
                price = item.price ?: 0.0,
                discountPercentage = item.discountPercentage ?: 0.0,
                rating = item.rating ?: 0.0,
                stock = item.stock ?: 0,
                brand = item.brand ?: "",
            )
        }
    }
}