package com.abdulkarim.domain.apiusecase.credential

import com.abdulkarim.domain.repository.CredentialRepository
import com.abdulkarim.domain.usecase.ApiUseCaseParams
import com.abdulkarim.entity.credential.LoginApiEntity
import kotlinx.coroutines.flow.Flow
import com.abdulkarim.common.base.Result
import javax.inject.Inject

class PostLoginApiUseCase @Inject constructor(
    private val credentialRepository: CredentialRepository,
) : ApiUseCaseParams<PostLoginApiUseCase.Params, LoginApiEntity> {

    data class Params(
        val mobile: String,
        val password: String,
    )

    override suspend fun execute(params: Params): Flow<Result<LoginApiEntity>> {
        return credentialRepository.postLoginApi(params = params)
    }
}

