package com.abdulkarim.data.repoimpl

import com.abdulkarim.data.apiservice.ApiService
import com.abdulkarim.common.base.Result
import com.abdulkarim.data.mapper.PostApiMapper
import com.abdulkarim.data.mapper.mapFromApiResponse
import com.abdulkarim.data.wrapper.NetworkBoundResource
import com.abdulkarim.domain.repository.Repository
import com.abdulkarim.entity.PostApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val apiService: ApiService,
    private val postApiMapper: PostApiMapper,

    ) : Repository {

    override suspend fun getPostListApi(): Flow<Result<List<PostApiEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResource.downloadData {
                apiService.getPostListApi()
            },
            mapper = postApiMapper
        )
    }

}