package com.abdulkarim.domain.base

import com.abdulkarim.domain.apiusecase.credential.PostLoginApiUseCase
import javax.inject.Inject

class IoValidation @Inject constructor(){

    /** validation for user login and all required fields **/

    fun validateLoginDataInput(params : PostLoginApiUseCase.Params) : ValidationResult {
        return when {
            params.username.isEmpty() -> ValidationResult.Failure(LoginInputErrorResult.EmptyUserName)
            params.password.isEmpty() -> ValidationResult.Failure(LoginInputErrorResult.EmptyPassword)
            params.password.length < 5 -> ValidationResult.Failure(LoginInputErrorResult.InvalidPassword)
            else -> ValidationResult.Success
        }
    }

}