package com.abdulkarim.data.mapper.product

import com.abdulkarim.apiresponse.product.ProductDetailsApiResponse
import com.abdulkarim.data.mapper.Mapper
import com.abdulkarim.entity.product.ProductDetailsApiEntity
import javax.inject.Inject

class ProductDetailsApiMapper @Inject constructor() :
    Mapper<ProductDetailsApiResponse, ProductDetailsApiEntity> {

    override fun mapFromApiResponse(type: ProductDetailsApiResponse): ProductDetailsApiEntity {
        return ProductDetailsApiEntity(
            id = type.id ?: -1,
            title = type.title ?: "",
            description = type.description ?: "",
            category = type.category ?: "",
            price = type.price ?: 0.0,
            discountPercentage = type.discountPercentage ?: 0.0,
            rating = type.rating ?: 0.0,
            stock = type.stock ?: 0,
            brand = type.brand ?: "",
            thumbnail = type.thumbnail ?: "",
        )
    }
}