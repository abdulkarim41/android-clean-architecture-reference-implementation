package com.abdulkarim.textview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class TextBold @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseTextView(context, attrs) {

    override fun applyFont() {
        typeface = Typeface.DEFAULT_BOLD
    }
}