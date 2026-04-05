package com.abdulkarim.splash

import android.os.Bundle
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.splash.databinding.FragmentSplashBinding
import com.abdulkarim.ui.extfun.navigate
import com.abdulkarim.ui.R
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun viewBindingLayout() = FragmentSplashBinding.inflate(layoutInflater)

    private val viewModel by viewModels< SplashViewModel>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.uiEvent bindTo ::handleUiEvent
        viewModel.uiState bindTo ::observeUiState

    }

    private fun handleUiEvent(event: SplashUiEvent<*>){
        when(event){
            is SplashUiEvent.NavigateToLogin -> navigate(
                getString(R.string.deep_link_login_fragment).toUri(),
                popupToId = com.abdulkarim.splash.R.id.splashFragment,
                popupToInclusive = true
            )

            is SplashUiEvent.NavigateToHome -> {
                // navigate to home fragment
            }
        }
    }

    private fun observeUiState(state: SplashUiState<*>){
        when(state){
            is SplashUiState.Loading -> {
                // show loading
            }

            is SplashUiState.ApiError -> {
                // show error
            }
        }
    }


}