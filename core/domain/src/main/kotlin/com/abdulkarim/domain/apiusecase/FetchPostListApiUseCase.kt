package com.abdulkarim.domain.apiusecase

import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.repository.Repository
import com.abdulkarim.domain.usecase.ApiUseCaseNonParams
import com.abdulkarim.entity.PostApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPostListApiUseCase @Inject constructor(
    private val repository: Repository
): ApiUseCaseNonParams<List<PostApiEntity>> {

    override suspend fun execute(): Flow<Result<List<PostApiEntity>>> {
        return repository.getPostListApi()
    }

}

