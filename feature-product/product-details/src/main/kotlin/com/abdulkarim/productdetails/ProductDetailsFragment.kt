package com.abdulkarim.productdetails

import android.os.Bundle
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.productdetails.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>() {

    override fun viewBindingLayout() = FragmentProductDetailsBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {

    }

}