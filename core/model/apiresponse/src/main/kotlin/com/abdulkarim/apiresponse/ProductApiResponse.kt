package com.abdulkarim.apiresponse

data class ProductApiResponse(
    val id: String?,
    val title: String?,
    val description: String?,
    val category: String?,
    val price: Double?,
    val discountPercentage: Double?,
    val rating: Double?,
    val stock: Int?,
    val brand: String?,
)