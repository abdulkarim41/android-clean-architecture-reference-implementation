package com.abdulkarim.carts

import android.os.Bundle
import com.abdulkarim.carts.databinding.FragmentCartsBinding
import com.abdulkarim.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartsFragment : BaseFragment<FragmentCartsBinding>() {

    override fun viewBindingLayout() = FragmentCartsBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {

    }

}