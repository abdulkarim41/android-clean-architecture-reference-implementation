package com.abdulkarim.domain.repository

import com.abdulkarim.common.base.Result
import com.abdulkarim.entity.PostApiEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getPostListApi() : Flow<Result<List<PostApiEntity>>>

}