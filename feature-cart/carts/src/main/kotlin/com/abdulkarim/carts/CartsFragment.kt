package com.abdulkarim.carts

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.abdulkarim.carts.databinding.FragmentCartsBinding
import com.abdulkarim.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartsFragment : BaseFragment<FragmentCartsBinding>() {

    override fun viewBindingLayout() = FragmentCartsBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {

        binding.btnSubmit.setOnClickListener {
            binding.btnSubmit.setLoading(true)

            lifecycleScope.launch {
                delay(2000)
                binding.btnSubmit.setLoading(false)
            }
        }

    }

}