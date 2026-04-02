package com.abdulkarim.login

import com.abdulkarim.common.base.Result
import com.abdulkarim.common.base.BaseViewModel
import com.abdulkarim.domain.apiusecase.credential.PostLoginApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginApiUseCase: PostLoginApiUseCase,
) : BaseViewModel() {

    val action: (LoginUiAction) -> Unit = { action ->
        when (action) {
            is LoginUiAction.PostLoginApiAction -> postLoginApi(action.params)
        }
    }

    private val _uiEvent = Channel<LoginUiEvent<Any>>()
    val uiEvent get() = _uiEvent.receiveAsFlow()

    private fun postLoginApi(params: PostLoginApiUseCase.Params) {
        execute {
            postLoginApiUseCase.execute(params).collect { result ->
                when (result) {
                    is Result.Success -> _uiEvent.send(LoginUiEvent.ApiSuccess)
                    is Result.Loading -> _uiEvent.send(LoginUiEvent.Loading)
                    is Result.Error -> _uiEvent.send(LoginUiEvent.LoginApiError(message = result.message))
                }
            }
        }
    }
}

sealed interface LoginUiEvent<out ResultType> {
    data object Loading : LoginUiEvent<Loading>
    data object ApiSuccess : LoginUiEvent<ApiSuccess>
    data class LoginApiError(val message: String) : LoginUiEvent<LoginApiError>
}

sealed interface LoginUiAction {
    data class PostLoginApiAction(val params: PostLoginApiUseCase.Params) : LoginUiAction
}
