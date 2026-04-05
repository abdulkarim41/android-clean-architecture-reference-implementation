package com.abdulkarim.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun viewBindingLayout() = FragmentLoginBinding.inflate(layoutInflater)

    private val viewModel by viewModels<LoginViewModel>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.uiEvent bindTo ::handleUiEvent

    }

    private fun handleUiEvent(event: LoginUiEvent<Any>) {

        when (event) {
            is LoginUiEvent.Loading -> {}
            is LoginUiEvent.ApiError -> showMessage(event.message)
            is LoginUiEvent.ApiSuccess -> {}
            is LoginUiEvent.ProfileApiSuccess -> {}
        }
    }

}