package com.abdulkarim.customview.layout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.abdulkarim.customview.R

@SuppressLint("CustomViewStyleable")
class CustomConstraintLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var cornerRadius = 0f
    private var strokeColor = 0
    private var strokeWidth = 0f
    private var backgroundColor = 0

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomLayout) {

            cornerRadius = getDimension(R.styleable.CustomLayout_cornerRadius, 0f)
            strokeColor = getColor(R.styleable.CustomLayout_strokeColor, 0)
            strokeWidth = getDimension(R.styleable.CustomLayout_strokeWidth, 0f)
            backgroundColor = getColor(R.styleable.CustomLayout_backgroundColor, 0)

        }

        applyBackground()
    }

    private fun applyBackground() {
        val drawable = GradientDrawable().apply {
            cornerRadius = this@CustomConstraintLayout.cornerRadius

            if (backgroundColor != 0) {
                setColor(backgroundColor)
            }

            if (strokeWidth > 0 && strokeColor != 0) {
                setStroke(strokeWidth.toInt(), strokeColor)
            }
        }

        background = drawable
    }
}