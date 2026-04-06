package com.abdulkarim.domain.apiusecase.credential

import com.abdulkarim.domain.repository.CredentialRepository
import com.abdulkarim.domain.usecase.ApiUseCaseParams
import com.abdulkarim.entity.credential.LoginApiEntity
import kotlinx.coroutines.flow.Flow
import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.base.IoValidation
import com.abdulkarim.domain.base.LoginInputErrorResult
import com.abdulkarim.domain.base.ValidationResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class PostLoginApiUseCase @Inject constructor(
    private val credentialRepository: CredentialRepository,
    private val ioValidation: IoValidation,
) : ApiUseCaseParams<PostLoginApiUseCase.Params, LoginApiEntity> {

    val ioError = Channel<LoginInputErrorResult>()

    data class Params(
        val username: String,
        val password: String,
    )

    override suspend fun execute(params: Params): Flow<Result<LoginApiEntity>> {
        return when (val result = ioValidation.validateLoginDataInput(params)) {
            is ValidationResult.Failure<*> -> {
                ioError.send(result.ioErrorResult as LoginInputErrorResult)
                emptyFlow()
            }

            is ValidationResult.Success -> credentialRepository.postLoginApi(params = params)
        }
    }
}

