package com.abdulkarim.profile

import android.os.Bundle
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun viewBindingLayout() = FragmentProfileBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {

    }

}