package com.abdulkarim.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.abdulkarim.ui.databinding.ViewStateLayoutBinding

class ViewStateLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    // lazy বা nullable হিসেবে রাখা ভালো যাতে ইনফ্লেশন শেষ না হওয়া পর্যন্ত এটি কল না হয়
    private var _binding: ViewStateLayoutBinding? = null
    private val binding get() = _binding

    private var contentView: View? = null
    private var onRetry: (() -> Unit)? = null

    init {
        _binding = ViewStateLayoutBinding.inflate(LayoutInflater.from(context), this)

        binding?.btnRetry?.setOnClickListener {
            onRetry?.invoke()
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)

        // binding null না থাকলে এবং child প্রোগ্রেসবার বা এরর লেআউট না হলে সেটি কন্টেন্ট
        _binding?.let { b ->
            if (child != null && child.id != b.progressBar.id && child.id != b.errorLayout.id) {
                contentView = child
            }
        }
    }

    fun showLoading() {
        binding?.apply {
            progressBar.isVisible = true
            errorLayout.isVisible = false
            contentView?.isVisible = false
        }
    }

    fun showError(message: String?, retryAction: () -> Unit) {
        this.onRetry = retryAction
        binding?.apply {
            progressBar.isVisible = false
            errorLayout.isVisible = true
            contentView?.isVisible = false
            tvErrorMessage.text = message ?: "Unknown Error"
        }
    }

    fun showSuccess() {
        binding?.apply {
            progressBar.isVisible = false
            errorLayout.isVisible = false
            contentView?.isVisible = true
        }
    }
}