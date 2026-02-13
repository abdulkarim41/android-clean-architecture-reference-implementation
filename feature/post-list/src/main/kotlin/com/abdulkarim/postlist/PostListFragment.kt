package com.abdulkarim.postlist

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.postlist.databinding.FragmentPostListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.getValue

@AndroidEntryPoint
class PostListFragment : BaseFragment<FragmentPostListBinding>() {

    override fun viewBindingLayout() = FragmentPostListBinding.inflate(layoutInflater)

    private val viewModel by viewModels<PostListViewModel>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.uiState bindTo :: handleUiState

    }

    private fun handleUiState(state: PostListUiState<Any>) {
        when (state) {
            is PostListUiState.Loading -> {
                // show progress bar
            }
            is PostListUiState.PostList -> {
                // set post list to adapter
                Timber.e("post list : ${state.data}")
            }
            is PostListUiState.ApiError -> {
                // show error message
            }
        }
    }

}