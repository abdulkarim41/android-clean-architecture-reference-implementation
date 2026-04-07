package com.abdulkarim.customview.button

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.withStyledAttributes
import com.abdulkarim.customview.R
import com.google.android.material.button.MaterialButton

class LoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialButton(context, attrs) {

    private var originalText: CharSequence? = null

    init {
        context.withStyledAttributes(attrs, R.styleable.LoadingButton) {

            // ONLY handle custom attributes
            val allCaps = getBoolean(R.styleable.LoadingButton_textAllCaps, false)
            isAllCaps = allCaps

            val loading = getBoolean(R.styleable.LoadingButton_loading, false)
            post {
                originalText = text
                setLoading(loading)
            } // important (wait until text is set)
        }
    }

    fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            if (originalText == null) {
                originalText = text
            }

            text = "Loading..."
            isEnabled = false
            alpha = 0.6f

        } else {
            text = originalText
            isEnabled = true
            alpha = 1f
        }
    }
}