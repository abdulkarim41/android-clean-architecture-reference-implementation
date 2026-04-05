package com.abdulkarim.data.repoimpl

import com.abdulkarim.data.apiservice.CredentialApiService
import com.abdulkarim.data.mapper.credential.LoginApiMapper
import com.abdulkarim.data.mapper.mapFromApiResponse
import com.abdulkarim.data.wrapper.NetworkBoundResource
import com.abdulkarim.domain.apiusecase.credential.PostLoginApiUseCase
import com.abdulkarim.entity.credential.LoginApiEntity
import kotlinx.coroutines.flow.Flow
import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.repository.CredentialRepository
import javax.inject.Inject

class CredentialRepoImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val credentialApiService: CredentialApiService,
    private val loginApiMapper: LoginApiMapper
) : CredentialRepository {

    override suspend fun postLoginApi(params: PostLoginApiUseCase.Params): Flow<Result<LoginApiEntity>> {
        return mapFromApiResponse(
            result = networkBoundResource.downloadData {
                credentialApiService.postLoginApi(params)
            }, mapper = loginApiMapper
        )
    }
}