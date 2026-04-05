package com.abdulkarim.splash

import com.abdulkarim.common.base.BaseViewModel
import com.abdulkarim.domain.apiusecase.common.FetchProfileApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import com.abdulkarim.common.base.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val fetchProfileApiUseCase: FetchProfileApiUseCase,
) : BaseViewModel() {

    val action: (SplashUiAction) -> Unit = {
        when(it) {
            is SplashUiAction.FetchProfileApiAction -> fetchProfileApi()
        }
    }

    private val _uiState = MutableStateFlow<SplashUiState<Any>>(SplashUiState.Loading(isLoading = true))
    val uiState get() = _uiState

    private val _uiEvent = Channel<SplashUiEvent<Any>>()
    val uiEvent get() = _uiEvent.receiveAsFlow()

    init { fetchProfileApi() }

    private fun fetchProfileApi() {
        execute {
            fetchProfileApiUseCase.execute().collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = SplashUiState.Loading(isLoading = result.loading)
                    is Result.Error -> {
                        if (result.code == 401 || result.code == 402) {
                            _uiEvent.send(SplashUiEvent.NavigateToLogin)
                            return@collect
                        }
                        _uiState.value = SplashUiState.ApiError(message = result.message, code = result.code)
                    }
                    is Result.Success -> _uiEvent.send(SplashUiEvent.NavigateToHome)
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