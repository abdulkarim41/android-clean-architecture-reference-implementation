package com.abdulkarim.viewstate.extension

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.abdulkarim.viewstate.databinding.ViewStateConstraintLayoutBinding
import com.abdulkarim.viewstate.utils.changeMainViewVisibility
import com.abdulkarim.viewstate.utils.resetViewState

internal fun ViewStateConstraintLayoutBinding.progressbarView(
    progressStatus: Boolean,
    progressBarColor: Int,
    backgroundColor: Int
) {
    resetViewState()

    if (!progressStatus) return

    incSimpleProgress.root.setBackgroundResource(backgroundColor)

    incSimpleProgress.progressBar.indeterminateTintList =
        ColorStateList.valueOf(
            ContextCompat.getColor(parentCl.context, progressBarColor)
        )

    incSimpleProgress.simpleProgressParent.isVisible = true

    changeMainViewVisibility(true)
}

internal fun ViewStateConstraintLayoutBinding.progressBarLottieView(
    progressStatus: Boolean,
    lottieRes: Int,
    backgroundColor: Int
) {
    resetViewState()

    if (!progressStatus) return

    incLottieProgressLayout.root.setBackgroundResource(backgroundColor)

    incLottieProgressLayout.lottieProgressBarLayout.isVisible = true

    incLottieProgressLayout.lottieProgressBar.apply {
        setAnimation(lottieRes)
        playAnimation()
    }

    changeMainViewVisibility(true)
}