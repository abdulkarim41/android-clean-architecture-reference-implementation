package com.abdulkarim.postlist

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.postlist.databinding.FragmentPostListBinding
import com.abdulkarim.ui.extfun.setUpVerticalRecyclerView
import com.abdulkarim.ui.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class PostListFragment : BaseFragment<FragmentPostListBinding>() {

    override fun viewBindingLayout() = FragmentPostListBinding.inflate(layoutInflater)

    private val viewModel by viewModels<PostListViewModel>()
    private var adapter by autoCleared<PostListAdapter>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.uiState bindTo :: handleUiState

        adapter = PostListAdapter(
            onItemClicked = {
                showMessage(it.title)
            }
        )

        requireContext().setUpVerticalRecyclerView(binding.postListRV, adapter)

    }

    private fun handleUiState(state: PostListUiState<Any>) {
        when (state) {
            is PostListUiState.Loading -> {
                binding.viewState.showLoading()
            }
            is PostListUiState.PostList -> {
                binding.viewState.showSuccess()
                adapter.submitList(state.data)
            }
            is PostListUiState.ApiError -> {
                binding.viewState.showError(
                    message = state.message,
                    retryAction = {
                        viewModel.action(PostListUiAction.FetchPostListApi)
                    }
                )
            }
        }
    }

}