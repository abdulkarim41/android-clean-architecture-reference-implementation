package com.abdulkarim.viewstate

sealed class VslState {
    object Content : VslState()
    object Loading : VslState()
    object Empty : VslState()
    data class Error(val message: String? = null) : VslState()
}