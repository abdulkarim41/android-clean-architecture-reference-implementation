package com.abdulkarim.data.apiservice

import com.abdulkarim.apiresponse.credential.LoginApiResponse
import com.abdulkarim.domain.apiusecase.credential.PostLoginApiUseCase
import retrofit2.Response
import retrofit2.http.*

interface CredentialApiService {

    @POST("auth/login")
    suspend fun postLoginApi(
        @Body params: PostLoginApiUseCase.Params
    ): Response<LoginApiResponse>

}