package com.abdulkarim.customview.utils

import android.content.Context

fun Context.dp(value: Float): Float {
    return value * resources.displayMetrics.density
}