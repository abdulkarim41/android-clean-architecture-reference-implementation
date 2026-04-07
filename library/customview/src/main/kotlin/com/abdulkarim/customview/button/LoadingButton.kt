package com.abdulkarim.customview.button

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import com.abdulkarim.customview.R
import com.abdulkarim.customview.databinding.ViewLoadingButtonBinding

class LoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    var binding: ViewLoadingButtonBinding = ViewLoadingButtonBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
        )

    init {
        attrs?.let {
            context.withStyledAttributes(it, R.styleable.LoadingButton) {

                val text = getString(R.styleable.LoadingButton_text)
                binding.button.text = text ?: "Button"

                val allCaps = getBoolean(R.styleable.LoadingButton_textAllCaps, false)
                binding.button.isAllCaps = allCaps

            }
        }
    }

    fun setLoading(isLoading: Boolean) {
        binding.button.isEnabled = !isLoading

        if (isLoading) {
            binding.progressBar.visibility = VISIBLE
            binding.button.alpha = 0.6f
        } else {
            binding.progressBar.visibility = GONE
            binding.button.alpha = 1f
        }
    }

    fun setText(text: String) {
        binding.button.text = text
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        binding.button.setOnClickListener(listener)
    }
}