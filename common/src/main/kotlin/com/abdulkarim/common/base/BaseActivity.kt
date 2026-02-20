package com.abdulkarim.common.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

abstract class BaseActivity<D:ViewBinding> : AppCompatActivity(){

    protected lateinit var binding:D
    protected var activityContext: Activity? = null
    protected var weekActivityContext: WeakReference<Activity>? = null
    protected abstract fun viewBindingLayout(): D
    protected abstract fun initializeView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBindingLayout()
        setContentView(binding.root)
        activityContext = this
        weekActivityContext = WeakReference(this)
        initializeView(savedInstanceState)
    }

    override fun onDestroy() {
        weekActivityContext = null
        super.onDestroy()
    }

    protected inline fun<T> Flow<T>.execute(crossinline action:(T)->Unit){
        with(this){
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    collect{
                        action(it)
                    }
                }
            }
        }
    }

    protected inline fun execute(crossinline action: suspend () -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                action.invoke()
            }
        }
    }

}