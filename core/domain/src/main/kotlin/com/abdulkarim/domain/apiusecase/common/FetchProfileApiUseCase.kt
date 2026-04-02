package com.abdulkarim.domain.apiusecase.common

import com.abdulkarim.domain.repository.CommonRepository
import com.abdulkarim.domain.usecase.ApiUseCaseNonParams
import com.abdulkarim.entity.common.ProfileApiEntity
import javax.inject.Inject

class FetchProfileApiUseCase @Inject constructor(
    private val commonRepository: CommonRepository
) : ApiUseCaseNonParams<ProfileApiEntity> {

    override suspend fun execute() = commonRepository.fetchProfileApi()
}