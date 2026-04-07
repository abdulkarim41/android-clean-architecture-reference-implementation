package com.abdulkarim.viewstate.utils

import android.os.SystemClock
import android.view.View

fun View.clickWithDebounce(
    interval: Long = 1000L,
    action: (View) -> Unit
) {
    var lastClickTime = 0L

    setOnClickListener {
        val currentTime = SystemClock.elapsedRealtime()
        if (currentTime - lastClickTime >= interval) {
            lastClickTime = currentTime
            action(it)
        }
    }
}