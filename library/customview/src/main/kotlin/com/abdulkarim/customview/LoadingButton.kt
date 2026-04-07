package com.abdulkarim.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.content.withStyledAttributes

class LoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val button: Button
    private val progressBar: ProgressBar

    init {
        LayoutInflater.from(context).inflate(R.layout.view_loading_button, this, true)

        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)

        attrs?.let {
            context.withStyledAttributes(it, R.styleable.LoadingButton) {

                val text = getString(R.styleable.LoadingButton_text)
                button.text = text ?: "Button"

                val allCaps = getBoolean(R.styleable.LoadingButton_textAllCaps, false)
                button.isAllCaps = allCaps

            }
        }
    }

    fun setLoading(isLoading: Boolean) {
        button.isEnabled = !isLoading

        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            button.alpha = 0.6f
        } else {
            progressBar.visibility = View.GONE
            button.alpha = 1f
        }
    }

    fun setText(text: String) {
        button.text = text
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        button.setOnClickListener(listener)
    }
}