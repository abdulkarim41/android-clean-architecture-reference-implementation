package com.abdulkarim.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    fun execute(job: suspend () -> Unit) {
        viewModelScope.launch {
            job.invoke()
        }
    }

    protected fun<T> Channel<T>.publishEvent(value:T){
        execute {
            this.send(value)
        }
    }

}