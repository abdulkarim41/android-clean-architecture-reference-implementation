package com.abdulkarim.data.apiservice

import com.abdulkarim.apiresponse.credential.LoginApiResponse
import retrofit2.Response
import retrofit2.http.*

interface CredentialApiService {

    @POST("auth/login")
    suspend fun postLoginApi(): Response<LoginApiResponse>

}