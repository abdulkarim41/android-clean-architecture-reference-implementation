package com.abdulkarim.apiresponse.product

data class ProductApiResponse(
    val products : List<ProductItem>
){
    data class ProductItem(
        val id: Int?,
        val title: String?,
        val description: String?,
        val category: String?,
        val price: Double?,
        val discountPercentage: Double?,
        val rating: Double?,
        val stock: Int?,
        val brand: String?,
    )
}