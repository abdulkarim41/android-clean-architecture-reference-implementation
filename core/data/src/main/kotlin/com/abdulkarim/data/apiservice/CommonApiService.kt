package com.abdulkarim.data.apiservice

import com.abdulkarim.apiresponse.common.ProfileApiResponse
import retrofit2.Response
import retrofit2.http.*

interface CommonApiService {

    @GET("auth/me")
    suspend fun fetchProfileApi(): Response<ProfileApiResponse>

}