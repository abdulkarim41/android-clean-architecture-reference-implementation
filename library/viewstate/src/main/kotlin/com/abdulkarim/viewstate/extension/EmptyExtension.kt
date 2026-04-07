package com.abdulkarim.viewstate.extension

import androidx.core.view.isVisible
import com.abdulkarim.viewstate.databinding.ViewStateConstraintLayoutBinding
import com.abdulkarim.viewstate.utils.changeMainViewVisibility
import com.abdulkarim.viewstate.utils.clickWithDebounce
import com.abdulkarim.viewstate.utils.resetViewState

internal fun ViewStateConstraintLayoutBinding.dataEmptyLottieView(
    message: String,
    buttonText: String,
    refreshCallback: () -> Unit
) {
    resetViewState()

    incLottieDataEmptyLayout.apply {
        lottieDataEmptyLayout.isVisible = true
        tvMessage.text = message
        btnRetry.text = buttonText

        btnRetry.clickWithDebounce {
            refreshCallback.invoke()
        }
    }

    changeMainViewVisibility(true)
}