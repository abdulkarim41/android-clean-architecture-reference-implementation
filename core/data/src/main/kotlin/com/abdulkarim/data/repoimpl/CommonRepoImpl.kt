package com.abdulkarim.data.repoimpl

import com.abdulkarim.common.base.Result
import com.abdulkarim.data.apiservice.CommonApiService
import com.abdulkarim.data.mapper.common.ProfileApiMapper
import com.abdulkarim.data.mapper.mapFromApiResponse
import com.abdulkarim.data.wrapper.NetworkBoundResource
import com.abdulkarim.domain.repository.CommonRepository
import com.abdulkarim.entity.common.ProfileApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommonRepoImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val apiService: CommonApiService,
    private val profileApiMapper: ProfileApiMapper,

    ) : CommonRepository {

    override suspend fun fetchProfileApi(): Flow<Result<ProfileApiEntity>> {
        return mapFromApiResponse(
            result = networkBoundResource.downloadData {
                apiService.fetchProfileApi()
            },
            mapper = profileApiMapper
        )
    }

}