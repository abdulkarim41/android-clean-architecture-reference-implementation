package com.abdulkarim.domain.repository

import com.abdulkarim.common.base.Result
import com.abdulkarim.entity.common.ProfileApiEntity
import kotlinx.coroutines.flow.Flow

interface CommonRepository {

    suspend fun fetchProfileApi(): Flow<Result<ProfileApiEntity>>

}