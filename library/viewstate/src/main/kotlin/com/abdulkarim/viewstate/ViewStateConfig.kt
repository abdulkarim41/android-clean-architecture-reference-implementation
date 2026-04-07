package com.abdulkarim.viewstate

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

object ViewStateConfig {

    @ColorRes
    var progressBarColor: Int = android.R.color.holo_blue_light

    @ColorRes
    var progressbarViewBackgroundResources: Int = android.R.color.white

    @DrawableRes
    var networkErrorImageDrawableRes: Int = android.R.drawable.ic_dialog_alert

    var networkErrorTitleText: String = "Oops!"
    var networkErrorMessageText: String = "Something went wrong"
    var networkErrorButtonText: String = "Retry"

    @RawRes
    var progressBarLottie: Int = 0
}