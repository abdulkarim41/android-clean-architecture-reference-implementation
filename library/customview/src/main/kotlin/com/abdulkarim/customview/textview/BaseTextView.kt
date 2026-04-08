package com.abdulkarim.customview.textview

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.withStyledAttributes
import com.abdulkarim.R

abstract class BaseTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    abstract fun applyFont()

    init {
        applyFont()
        applyShape(attrs)
    }

    private fun applyShape(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.BaseTextView) {

            val radius = getDimension(R.styleable.BaseTextView_tvCornerRadius, 0f)
            val bgColor = getColor(R.styleable.BaseTextView_tvBackgroundColor, 0)

            val strokeColor = getColor(R.styleable.BaseTextView_tvStrokeColor, 0)
            val strokeWidth = getDimension(R.styleable.BaseTextView_tvStrokeWidth, 0f)

            if (radius > 0 || bgColor != 0 || strokeWidth > 0) {

                val drawable = GradientDrawable().apply {

                    cornerRadius = radius

                    if (bgColor != 0) {
                        setColor(bgColor)
                    }

                    if (strokeWidth > 0 && strokeColor != 0) {
                        setStroke(strokeWidth.toInt(), strokeColor)
                    }
                }

                background = drawable
            }
        }
    }
}