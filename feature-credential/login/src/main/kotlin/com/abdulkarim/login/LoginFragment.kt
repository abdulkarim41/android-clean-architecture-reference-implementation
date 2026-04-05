package com.abdulkarim.login

import android.os.Bundle
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.domain.apiusecase.credential.PostLoginApiUseCase
import com.abdulkarim.login.databinding.FragmentLoginBinding
import com.abdulkarim.ui.extfun.navigate
import com.abdulkarim.ui.utils.clickWithDebounce
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun viewBindingLayout() = FragmentLoginBinding.inflate(layoutInflater)

    private val viewModel by viewModels<LoginViewModel>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.uiEvent bindTo ::handleUiEvent

        binding.loginBtn.clickWithDebounce {
            viewModel.action(LoginUiAction.PostLoginApiAction(PostLoginApiUseCase.Params(
                username = "emilys",
                password = "emilyspass"
            )))
        }

    }

    private fun handleUiEvent(event: LoginUiEvent<Any>) {

        when (event) {
            is LoginUiEvent.Loading -> {}
            is LoginUiEvent.ApiError -> showMessage(event.message)
            is LoginUiEvent.ApiSuccess -> {
                navigate(uri = getString(R.string.deep_link_post_list_fragment).toUri())
            }
            is LoginUiEvent.ProfileApiSuccess -> {}
        }
    }

}