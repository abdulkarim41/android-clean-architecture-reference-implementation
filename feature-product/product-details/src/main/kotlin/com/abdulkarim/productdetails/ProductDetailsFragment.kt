package com.abdulkarim.productdetails

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.productdetails.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>() {

    override fun viewBindingLayout() = FragmentProductDetailsBinding.inflate(layoutInflater)

    private val arg by navArgs<ProductDetailsFragmentArgs>()

    override fun initializeView(savedInstanceState: Bundle?) {


        binding.resultTv.text = arg.productId.toString()
    }

}