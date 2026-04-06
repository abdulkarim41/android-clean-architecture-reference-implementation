package com.abdulkarim.apiresponse.common

data class ProfileApiResponse(
    val id: Int?,
    val username: String?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val maidenName: String?,
    val gender: String?,
    val image: String?,
    val phone: String?,
)