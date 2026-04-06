package com.abdulkarim.data.utils

import com.abdulkarim.entity.common.ProfileApiEntity
import com.abdulkarim.sharedpref.SharedPrefHelper
import com.abdulkarim.sharedpref.SpKey
import javax.inject.Inject

class CacheProfile @Inject constructor(
    private val sharedPrefHelper: SharedPrefHelper
){
    fun cacheProfile(data: ProfileApiEntity){
        with(data){
            sharedPrefHelper.putString(SpKey.firstName,firstName)
            sharedPrefHelper.putString(SpKey.maidenName,maidenName)
            sharedPrefHelper.putString(SpKey.lastName,lastName)
            sharedPrefHelper.putString(SpKey.gender,gender)
            sharedPrefHelper.putString(SpKey.phone, phone)
            sharedPrefHelper.putString(SpKey.email, email)
            sharedPrefHelper.putString(SpKey.imageUrl, imageUrl)
        }
    }
}