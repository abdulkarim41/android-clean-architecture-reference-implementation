package com.abdulkarim.customview.layout

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.withStyledAttributes
import com.abdulkarim.R
import com.abdulkarim.customview.utils.GradientOrientation
import com.abdulkarim.customview.utils.Shape
import com.abdulkarim.customview.utils.dp

class CustomLinearLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private var drawable: GradientDrawable = GradientDrawable()

    private var cornerRadius = 0f
    private var topLeft = 0f
    private var topRight = 0f
    private var bottomLeft = 0f
    private var bottomRight = 0f

    private var shapeType = Shape.Rectangle

    private var strokeColor = Color.TRANSPARENT
    private var strokeWidth = 0f

    private var disableColor = Color.GRAY

    private var gradientOrientation = GradientOrientation.TOP_BOTTOM
    private var gradientTopColor = -1
    private var gradientMiddleColor = -1
    private var gradientBottomColor = -1

    private var rippleColor = Color.TRANSPARENT

    init {
        attrs?.let { applyAttributes(it) }
        build()
    }

    // ---------------- ATTRIBUTES ----------------
    private fun applyAttributes(attrs: AttributeSet) {

        context.withStyledAttributes(attrs, R.styleable.CustomLinearLayout) {

            cornerRadius = getDimension(R.styleable.CustomLinearLayout_ll_corner_radius, 0f)

            topLeft = getDimension(R.styleable.CustomLinearLayout_ll_topLeft_corner_radius, 0f)
            topRight = getDimension(R.styleable.CustomLinearLayout_ll_topRight_corner_radius, 0f)
            bottomLeft = getDimension(R.styleable.CustomLinearLayout_ll_bottomLeft_corner_radius, 0f)
            bottomRight = getDimension(R.styleable.CustomLinearLayout_ll_bottomRight_corner_radius, 0f)

            shapeType = Shape.entries[
                getInt(R.styleable.CustomLinearLayout_ll_background_shape, 0)
            ]

            strokeColor = getColor(
                R.styleable.CustomLinearLayout_ll_stroke_color,
                Color.TRANSPARENT
            )

            strokeWidth = getDimension(
                R.styleable.CustomLinearLayout_ll_stroke_width,
                0f
            )

            disableColor = getColor(
                R.styleable.CustomLinearLayout_ll_disable_bg_color,
                Color.GRAY
            )

            gradientOrientation = GradientOrientation.entries[
                getInt(R.styleable.CustomLinearLayout_ll_gradient_orientation, 0)
            ]

            gradientTopColor = getColor(
                R.styleable.CustomLinearLayout_ll_gradient_top_color,
                -1
            )

            gradientMiddleColor = getColor(
                R.styleable.CustomLinearLayout_ll_gradient_middle_color,
                -1
            )

            gradientBottomColor = getColor(
                R.styleable.CustomLinearLayout_ll_gradient_bottom_color,
                -1
            )

            rippleColor = getColor(
                R.styleable.CustomLinearLayout_ll_ripple_color,
                Color.TRANSPARENT
            )
        }
    }

    // ---------------- BUILD ----------------
    private fun build() {

        drawable = GradientDrawable()

        applyShape()
        applyCorners()
        applyStroke()
        applyBackground()

        background = if (rippleColor != Color.TRANSPARENT) {
            RippleDrawable(
                ColorStateList.valueOf(rippleColor),
                drawable,
                null
            )
        } else {
            drawable
        }
    }

    // ---------------- SHAPE ----------------
    private fun applyShape() {
        drawable.shape = when (shapeType) {
            Shape.Rectangle, Shape.Stroke -> GradientDrawable.RECTANGLE
            Shape.Oval, Shape.StrokeCircle -> GradientDrawable.OVAL
        }
    }

    // ---------------- CORNERS ----------------
    private fun applyCorners() {

        if (shapeType == Shape.Oval || shapeType == Shape.StrokeCircle) return

        if (cornerRadius > 0) {
            drawable.cornerRadius = cornerRadius
        } else {
            drawable.cornerRadii = floatArrayOf(
                topLeft, topLeft,
                topRight, topRight,
                bottomRight, bottomRight,
                bottomLeft, bottomLeft
            )
        }
    }

    // ---------------- STROKE ----------------
    private fun applyStroke() {
        if (shapeType == Shape.Stroke || shapeType == Shape.StrokeCircle) {
            drawable.setStroke(strokeWidth.toInt(), strokeColor)
        }
    }

    // ---------------- BACKGROUND ----------------
    private fun applyBackground() {

        when (val bg = background) {

            is ColorDrawable -> {
                drawable.color = getColorStateList(bg.color)
            }

            is GradientDrawable -> {
                drawable.color = bg.color
            }

            else -> {
                drawable.color = getColorStateList(Color.TRANSPARENT)
            }
        }

        // Gradient override
        if (gradientTopColor != -1) {
            drawable.colors = intArrayOf(
                gradientTopColor,
                if (gradientMiddleColor == -1) gradientTopColor else gradientMiddleColor,
                if (gradientBottomColor == -1) gradientTopColor else gradientBottomColor
            )
            drawable.orientation = getGradientOrientation()
        }
    }

    // ---------------- API ----------------
    fun setShape(
        @ColorInt bgColor: Int,
        shape: Shape,
        @Dimension radius: Float = 0f
    ) {
        this.shapeType = shape
        this.cornerRadius = context.dp(radius)

        drawable.color = getColorStateList(bgColor)

        build()
    }

    // ---------------- COLOR ----------------
    private fun getColorStateList(@ColorInt color: Int): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_enabled)
            ),
            intArrayOf(color, disableColor)
        )
    }

    // ---------------- ORIENTATION ----------------
    private fun getGradientOrientation(): GradientDrawable.Orientation {
        return when (gradientOrientation) {
            GradientOrientation.TOP_BOTTOM -> GradientDrawable.Orientation.TOP_BOTTOM
            GradientOrientation.BOTTOM_TOP -> GradientDrawable.Orientation.BOTTOM_TOP
            GradientOrientation.LEFT_RIGHT -> GradientDrawable.Orientation.LEFT_RIGHT
            GradientOrientation.RIGHT_LEFT -> GradientDrawable.Orientation.RIGHT_LEFT
            GradientOrientation.TL_BR -> GradientDrawable.Orientation.TL_BR
            GradientOrientation.TR_BL -> GradientDrawable.Orientation.TR_BL
            GradientOrientation.BL_TR -> GradientDrawable.Orientation.BL_TR
            GradientOrientation.BR_TL -> GradientDrawable.Orientation.BR_TL
        }
    }
}