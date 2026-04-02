package com.abdulkarim.apiresponse.credential

data class LoginApiResponse(
    val accessToken: String?,
    val refreshToken: String?,
)