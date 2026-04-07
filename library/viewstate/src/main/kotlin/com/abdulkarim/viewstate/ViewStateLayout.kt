package com.abdulkarim.viewstate

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.abdulkarim.viewstate.databinding.ViewStateConstraintLayoutBinding
import com.abdulkarim.viewstate.extension.dataEmptyLottieView
import com.abdulkarim.viewstate.extension.networkErrorView
import com.abdulkarim.viewstate.extension.progressBarLottieView
import com.abdulkarim.viewstate.extension.progressbarView
import com.abdulkarim.viewstate.utils.resetViewState

class ViewStateLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewStateConstraintLayoutBinding.inflate(LayoutInflater.from(context), this)

    fun progressbarView(
        progressStatus: Boolean,
        progressBarColor: Int = ViewStateConfig.progressBarColor,
        backgroundColor: Int = ViewStateConfig.progressbarViewBackgroundResources
    ) {
        binding.progressbarView(progressStatus, progressBarColor, backgroundColor)
    }

    fun progressBarLottieView(
        progressStatus: Boolean,
        lottieRes: Int = ViewStateConfig.progressBarLottie,
        backgroundColor: Int = ViewStateConfig.progressbarViewBackgroundResources
    ) {
        binding.progressBarLottieView(progressStatus, lottieRes, backgroundColor)
    }

    fun networkErrorView(
        title: String,
        message: String,
        buttonText: String,
        refreshCallback: () -> Unit
    ) {
        binding.networkErrorView(title, message, buttonText, refreshCallback)
    }

    fun dataEmptyLottieView(
        message: String,
        buttonText: String,
        refreshCallback: () -> Unit
    ) {
        binding.dataEmptyLottieView(message, buttonText, refreshCallback)
    }

    fun reset() {
        binding.resetViewState()
    }
}