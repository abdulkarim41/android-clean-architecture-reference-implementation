package com.abdulkarim.profile

import android.os.Bundle
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.profile.databinding.FragmentProfileBinding
import com.abdulkarim.sharedpref.SharedPrefHelper
import com.abdulkarim.sharedpref.SpKey
import com.abdulkarim.ui.extfun.loadImage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun viewBindingLayout() = FragmentProfileBinding.inflate(layoutInflater)

    @Inject lateinit var sharedPref: SharedPrefHelper

    override fun initializeView(savedInstanceState: Bundle?) {

        binding.profileIv.loadImage(
            placeholder = R.drawable.ic_launcher_background,
            url = sharedPref.getString(SpKey.imageUrl)
        )
        binding.nameTv.text = getFullName()
        binding.emailTv.text = sharedPref.getString(SpKey.email)
        binding.phoneTv.text = sharedPref.getString(SpKey.phone)

    }

    private fun getFullName() : String {
        return "${sharedPref.getString(SpKey.firstName)} ${sharedPref.getString(SpKey.lastName)}"
    }

}