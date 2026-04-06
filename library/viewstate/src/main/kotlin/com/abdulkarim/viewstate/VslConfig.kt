package com.abdulkarim.viewstate

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

object VslConfig {

    @DrawableRes
    var errorImage = android.R.drawable.ic_dialog_alert

    var errorTitle = "Something went wrong"
    var errorMessage = "Please try again"

    var emptyTitle = "No Data"
    var emptyMessage = "Nothing to show"

    @DrawableRes
    var emptyImage = android.R.drawable.ic_menu_report_image

    @ColorRes
    var backgroundColor = android.R.color.white
}