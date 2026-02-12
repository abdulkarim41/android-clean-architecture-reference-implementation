package com.abdulkarim.data.apiservice

import com.abdulkarim.apiresponse.PostApiResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("posts")
    suspend fun getPostListApi() : Response<List<PostApiResponse>>

}