package com.abdulkarim.android_clean_architecture.host

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.abdulkarim.android_clean_architecture.R
import com.abdulkarim.android_clean_architecture.databinding.ActivityMainBinding
import com.abdulkarim.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun viewBindingLayout(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->

            if (!isGranted &&
                !shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)
            ) {
                // User permanently denied → Open Settings
                startActivity(
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", packageName, null)
                    }
                )
            }
        }

    private lateinit var navController: NavController

    override fun initializeView(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime  = insets.getInsets(WindowInsetsCompat.Type.ime())

            val bottom = maxOf(ime.bottom, bars.bottom)
            v.updatePadding(top = bars.top, bottom = bottom)
            WindowInsetsCompat.CONSUMED
        }
        ViewCompat.requestApplyInsets(binding.root)

        setupNavigation()
        requestNotificationPermission()
    }

    private fun requestNotificationPermission() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Already granted
            }

            else -> {
                notificationPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.mainNavHostContainerView) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNav.isVisible = destination.id in setOf(
                R.id.productsFragment,
                R.id.notificationFragment,
                R.id.profileFragment
            )
        }

        NavigationUI.setupWithNavController(binding.bottomNav, navController)

        binding.bottomNav.setOnItemSelectedListener { item ->
            navController.popBackStack(R.id.productsFragment, false)
            navController.navigate(item.itemId)
            true
        }

    }

}