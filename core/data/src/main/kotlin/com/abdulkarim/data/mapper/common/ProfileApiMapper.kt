package com.abdulkarim.data.mapper.common

import com.abdulkarim.apiresponse.common.ProfileApiResponse
import com.abdulkarim.data.mapper.Mapper
import com.abdulkarim.entity.common.ProfileApiEntity
import javax.inject.Inject

class ProfileApiMapper @Inject constructor(): Mapper<ProfileApiResponse, ProfileApiEntity> {

    override fun mapFromApiResponse(type: ProfileApiResponse): ProfileApiEntity {
        return ProfileApiEntity(
            email = type.email ?: "",
            firstName = type.firstName ?: "",
            lastName = type.lastName ?: "",
            gender = type.gender ?: "",
            imageUrl = type.image ?: "",
            phone = type.phone ?: "",
            maidenName = type.maidenName ?: "",
        )
    }
}