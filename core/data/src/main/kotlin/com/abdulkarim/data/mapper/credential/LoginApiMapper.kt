package com.abdulkarim.data.mapper.credential

import com.abdulkarim.apiresponse.credential.LoginApiResponse
import com.abdulkarim.data.mapper.Mapper
import com.abdulkarim.entity.credential.LoginApiEntity
import javax.inject.Inject

class LoginApiMapper @Inject constructor(): Mapper<LoginApiResponse, LoginApiEntity> {

    override fun mapFromApiResponse(type: LoginApiResponse): LoginApiEntity {
        return LoginApiEntity(
            accessToken = type.accessToken ?: "",
            refreshToken = type.refreshToken ?: ""
        )
    }
}