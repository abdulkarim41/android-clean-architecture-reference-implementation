package com.abdulkarim.login

import android.os.Bundle
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun viewBindingLayout() = FragmentLoginBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {

    }

}