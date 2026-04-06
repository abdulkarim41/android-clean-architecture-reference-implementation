package com.abdulkarim.domain.base

sealed class ValidationResult{
    data object Success : ValidationResult()
    data class Failure<T>(val ioErrorResult: T) : ValidationResult()
}

sealed class LoginInputErrorResult {
    data object EmptyUserName : LoginInputErrorResult()
    data object EmptyPassword:LoginInputErrorResult()
    data object InvalidPassword:LoginInputErrorResult()
}

