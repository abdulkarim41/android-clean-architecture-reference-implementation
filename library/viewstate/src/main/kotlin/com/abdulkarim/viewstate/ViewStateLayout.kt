package com.abdulkarim.viewstate

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.abdulkarim.viewstate.databinding.ViewStateLayoutBinding

class ViewStateLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewStateLayoutBinding.inflate(LayoutInflater.from(context), this)

    // -------------------------
    // Public API
    // -------------------------

    fun setState(
        state: VslState,
        onRetry: (() -> Unit)? = null
    ) {
        when (state) {
            is VslState.Loading -> showLoading()
            is VslState.Content -> showContent()
            is VslState.Empty -> showEmpty(onRetry)
            is VslState.Error -> showError(state.message, onRetry)
        }
    }

    fun showLoading() {
        hideAll()
        binding.progressBar.visibility = VISIBLE
    }

    fun showContent() {
        hideAll()
        binding.contentContainer.visibility = VISIBLE
    }

    fun showError(
        message: String?,
        onRetry: (() -> Unit)?
    ) {
        hideAll()

        binding.errorLayout.root.visibility = VISIBLE
        binding.errorLayout.titleText.text = VslConfig.errorTitle
        binding.errorLayout.messageText.text = message ?: VslConfig.errorMessage

        binding.errorLayout.retryButton.setOnClickListener {
            onRetry?.invoke()
        }
    }

    fun showEmpty(onAction: (() -> Unit)?) {
        hideAll()

        binding.emptyLayout.root.visibility = VISIBLE
        binding.emptyLayout.titleText.text = VslConfig.emptyTitle
        binding.emptyLayout.messageText.text = VslConfig.emptyMessage

        binding.emptyLayout.actionButton.setOnClickListener {
            onAction?.invoke()
        }
    }

    private fun hideAll() {
        binding.progressBar.visibility = GONE
        binding.errorLayout.root.visibility = GONE
        binding.emptyLayout.root.visibility = GONE
        binding.contentContainer.visibility = GONE
    }
}