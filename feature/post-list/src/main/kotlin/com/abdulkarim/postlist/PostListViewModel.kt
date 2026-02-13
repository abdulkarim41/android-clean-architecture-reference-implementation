package com.abdulkarim.postlist

import com.abdulkarim.common.base.Result
import com.abdulkarim.common.base.BaseViewModel
import com.abdulkarim.domain.apiusecase.FetchPostListApiUseCase
import com.abdulkarim.entity.PostApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val fetchPostListApiUseCase: FetchPostListApiUseCase
) : BaseViewModel() {

    val action: (PostListUiAction) -> Unit = {
        when (it) {
            is PostListUiAction.FetchPostListApi -> fetchPostListApi()
        }
    }

    private val _uiState = MutableStateFlow<PostListUiState<Any>>(PostListUiState.Loading(false))
    val uiState get() = _uiState

    init { fetchPostListApi() }

    private fun fetchPostListApi(){
        execute {
            fetchPostListApiUseCase.execute().collect { result ->
                when(result){
                    is Result.Loading -> _uiState.value = PostListUiState.Loading(result.loading)
                    is Result.Success -> _uiState.value = PostListUiState.PostList(result.data)
                    is Result.Error -> _uiState.value = PostListUiState.ApiError(result.message)
                }
            }
        }
    }

}

sealed interface PostListUiState<out ResultType> {
    data class Loading(val isLoading: Boolean) : PostListUiState<Loading>
    data class PostList(val data: List<PostApiEntity>) : PostListUiState<PostList>
    data class ApiError(val message: String) : PostListUiState<ApiError>
}

sealed interface PostListUiAction {
    data object FetchPostListApi : PostListUiAction
}