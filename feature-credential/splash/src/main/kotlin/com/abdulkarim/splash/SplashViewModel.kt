package com.abdulkarim.splash

import com.abdulkarim.common.base.BaseViewModel
import com.abdulkarim.domain.apiusecase.common.FetchProfileApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import com.abdulkarim.common.base.Result
import com.abdulkarim.sharedpref.SharedPrefHelper
import com.abdulkarim.sharedpref.SpKey
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedPrefHelper: SharedPrefHelper,
    private val fetchProfileApiUseCase: FetchProfileApiUseCase,
) : BaseViewModel() {

    val action: (SplashUiAction) -> Unit = {
        when(it) {
            is SplashUiAction.FetchProfileApiAction -> fetchProfileApi()
        }
    }

    private val _uiState = MutableStateFlow<SplashUiState<Any>>(SplashUiState.Loading(isLoading = true))
    val uiState get() = _uiState

    private val _uiEvent = MutableSharedFlow<SplashUiEvent<Any>>()
    val uiEvent get() = _uiEvent

    init { checkLoginStatus() }

    private fun checkLoginStatus() {
        if (sharedPrefHelper.getBoolean(SpKey.isUserLoggedIn)) {
            fetchProfileApi()
            return
        }
        _uiEvent.tryEmit(SplashUiEvent.NavigateToLogin)
    }

    private fun fetchProfileApi() {
        execute {
            fetchProfileApiUseCase.execute().collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = SplashUiState.Loading(isLoading = result.loading)
                    is Result.Error -> {
                        if (result.code == 401 || result.code == 402) {
                            _uiEvent.tryEmit(SplashUiEvent.NavigateToLogin)
                            return@collect
                        }
                        _uiState.value = SplashUiState.ApiError(message = result.message, code = result.code)
                    }
                    is Result.Success -> _uiEvent.tryEmit(SplashUiEvent.NavigateToHome)
                }
            }
        }
    }

}

sealed interface SplashUiEvent<out ResultType> {
    data object NavigateToLogin : SplashUiEvent<NavigateToLogin>
    data object NavigateToHome : SplashUiEvent<NavigateToHome>
}

sealed interface SplashUiState<out ResultType> {
    data class Loading(val isLoading: Boolean) : SplashUiState<Loading>
    data class ApiError(val message: String, val code: Int) : SplashUiState<ApiError>
}

sealed interface SplashUiAction {
    data object FetchProfileApiAction : SplashUiAction
}