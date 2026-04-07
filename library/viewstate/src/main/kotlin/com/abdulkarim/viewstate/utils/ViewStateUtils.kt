package com.abdulkarim.viewstate.utils

import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.core.view.isVisible
import com.abdulkarim.viewstate.databinding.ViewStateConstraintLayoutBinding

fun ViewStateConstraintLayoutBinding.resetViewState() = with(this) {

    changeMainViewVisibility(false)

    // Progress
    incLottieProgressLayout.lottieProgressBarLayout.isVisible = false
    incLottieProgressLayout.lottieProgressBar.cancelAnimation()
    incSimpleProgress.simpleProgressParent.isVisible = false

    // Error
    incSimpleErrorLayout.simpleErrorLayout.isVisible = false
    incLottieErrorLayout.lottieErrorLayout.isVisible = false
    incLottieErrorLayout.networkErrorIv.cancelAnimation()

    // Empty
    incSimpleDataEmptyLayout.simpleDataEmptyLayout.isVisible = false
    incLottieDataEmptyLayout.lottieDataEmptyLayout.isVisible = false
    incLottieDataEmptyLayout.dataEmptyIv.cancelAnimation()
}

fun ViewStateConstraintLayoutBinding.changeMainViewVisibility(status: Boolean) {
    val parentView = parentCl.parent
    if (parentView is ViewGroup) {
        parentView.forEach {
            if (it.id != parentCl.id) {
                it.isVisible = !status
            }
        }
    }
}