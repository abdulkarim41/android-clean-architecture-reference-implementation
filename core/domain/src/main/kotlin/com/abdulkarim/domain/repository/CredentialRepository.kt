package com.abdulkarim.domain.repository

import com.abdulkarim.domain.apiusecase.credential.PostLoginApiUseCase
import com.abdulkarim.common.base.Result
import com.abdulkarim.entity.credential.LoginApiEntity
import kotlinx.coroutines.flow.Flow

interface CredentialRepository {

    suspend fun postLoginApi(params: PostLoginApiUseCase.Params): Flow<Result<LoginApiEntity>>

}