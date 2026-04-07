package com.abdulkarim.login

import android.os.Bundle
import androidx.core.net.toUri
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.domain.apiusecase.credential.PostLoginApiUseCase
import com.abdulkarim.domain.base.LoginInputErrorResult
import com.abdulkarim.login.databinding.FragmentLoginBinding
import com.abdulkarim.ui.extfun.navigate
import com.abdulkarim.ui.extfun.clickWithDebounce
import com.abdulkarim.ui.extfun.getTextFromEt
import com.abdulkarim.ui.extfun.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun viewBindingLayout() = FragmentLoginBinding.inflate(layoutInflater)

    private val viewModel by viewModels<LoginViewModel>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.uiEvent bindTo ::handleUiEvent
        viewModel.loginIoError bindTo ::handleIoError

        setupInputListeners()
        clickListener()

    }

    private fun clickListener(){
        binding.loginBtn.clickWithDebounce {
            viewModel.action(LoginUiAction.PostLoginApiAction(params = buildLoginApiParams()))
            binding.loginBtn.hideKeyboard()
        }
    }

    private fun setupInputListeners() {
        binding.userNameEt.doOnTextChanged { _, _, _, _ ->
            binding.userNameTIL.error = null
        }

        binding.passwordEt.doOnTextChanged { _, _, _, _ ->
            binding.passwordTIL.error = null
        }
    }

    private fun buildLoginApiParams(): PostLoginApiUseCase.Params {
        val userName = binding.userNameEt.getTextFromEt()
        val password = binding.passwordEt.getTextFromEt()
        return PostLoginApiUseCase.Params(username = userName, password = password)
    }

    private fun handleUiEvent(event: LoginUiEvent<Any>) {

        when (event) {
            is LoginUiEvent.Loading -> {
                binding.loginBtn.setLoading(true)
            }
            is LoginUiEvent.ApiError -> showMessage(event.message)
            is LoginUiEvent.ApiSuccess -> {
                binding.loginBtn.setLoading(false)
                navigate(
                    uri = getString(R.string.deep_link_products_fragment).toUri(),
                    popupToId = R.id.loginFragment,
                    popupToInclusive = true
                )
            }
        }
    }

    private fun handleIoError(loginInputErrorResult: LoginInputErrorResult) {

        when (loginInputErrorResult) {
            is LoginInputErrorResult.EmptyUserName -> {
                binding.userNameTIL.error = getString(R.string.message_enter_username)
            }
            is LoginInputErrorResult.EmptyPassword -> {
                binding.passwordTIL.error = getString(R.string.message_enter_password)
            }
            is LoginInputErrorResult.InvalidPassword -> {
                binding.passwordTIL.error = getString(R.string.message_enter_valid_password)
            }
        }
    }

}