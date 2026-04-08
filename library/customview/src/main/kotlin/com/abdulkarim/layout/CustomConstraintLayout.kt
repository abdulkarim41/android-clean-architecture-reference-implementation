package com.abdulkarim.layout

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import com.abdulkarim.R
import com.abdulkarim.utils.GradientOrientation
import com.abdulkarim.utils.Shape
import com.abdulkarim.utils.dp
import androidx.core.content.withStyledAttributes

class CustomConstraintLayout : ConstraintLayout {

    private var drawableBuilder: GradientDrawable? = null

    private var attrCornerRadius = 0f
    private var attrTopLeftCornerRadius = 0f
    private var attrTopRightCornerRadius = 0f
    private var attrBottomLeftCornerRadius = 0f
    private var attrBottomRightCornerRadius = 0f

    private var attrShapeType = Shape.Rectangle

    private var attrStrokeColor = Color.TRANSPARENT
    private var attrStrokeWidth = 0f

    private var attrBgDisableColor = Color.GRAY

    private var attrGradientOrientation = GradientOrientation.TOP_BOTTOM
    private var attrGradientTopColor = -1
    private var attrGradientMiddleColor = -1
    private var attrGradientBottomColor = -1

    private var attrRippleColor = Color.TRANSPARENT

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    // ---------------- INIT ----------------
    private fun init(attrs: AttributeSet?) {
        attrs?.let { applyAttributes(it) }
        initBackgroundDrawable()
        applyShape()
        applyBackground()
    }

    // ---------------- ATTRIBUTES ----------------
    private fun applyAttributes(attrs: AttributeSet) {

        context.withStyledAttributes(
            attrs,
            R.styleable.CustomConstraintLayout,
            0,
            0
        ) {

            attrCornerRadius =
                getDimension(R.styleable.CustomConstraintLayout_cl_corner_radius, 0f)

            attrTopLeftCornerRadius =
                getDimension(R.styleable.CustomConstraintLayout_cl_topLeft_corner_radius, 0f)

            attrTopRightCornerRadius =
                getDimension(R.styleable.CustomConstraintLayout_cl_topRight_corner_radius, 0f)

            attrBottomLeftCornerRadius =
                getDimension(R.styleable.CustomConstraintLayout_cl_bottomLeft_corner_radius, 0f)

            attrBottomRightCornerRadius =
                getDimension(R.styleable.CustomConstraintLayout_cl_bottomRight_corner_radius, 0f)

            attrShapeType = Shape.entries.toTypedArray()[
                getInt(R.styleable.CustomConstraintLayout_cl_background_shape, 0)
            ]

            attrStrokeColor =
                getColor(R.styleable.CustomConstraintLayout_cl_stroke_color, Color.TRANSPARENT)

            attrStrokeWidth =
                getDimension(R.styleable.CustomConstraintLayout_cl_stroke_width, 0f)

            attrBgDisableColor =
                getColor(R.styleable.CustomConstraintLayout_cl_disable_bg_color, Color.GRAY)

            attrGradientOrientation = GradientOrientation.entries.toTypedArray()[
                getInt(R.styleable.CustomConstraintLayout_cl_gradient_orientation, 0)
            ]

            attrGradientTopColor =
                getColor(R.styleable.CustomConstraintLayout_cl_gradient_top_color, -1)

            attrGradientMiddleColor =
                getColor(R.styleable.CustomConstraintLayout_cl_gradient_middle_color, -1)

            attrGradientBottomColor =
                getColor(R.styleable.CustomConstraintLayout_cl_gradient_bottom_color, -1)

            attrRippleColor =
                getColor(R.styleable.CustomConstraintLayout_cl_ripple_color, Color.TRANSPARENT)

        }
    }

    // ---------------- BACKGROUND INIT ----------------
    private fun initBackgroundDrawable() {
        val bg = background

        drawableBuilder = when (bg) {
            is GradientDrawable -> bg

            is ColorDrawable -> {
                GradientDrawable().apply {
                    color = getBgColorStateList(bg.color)
                }
            }

            else -> {
                GradientDrawable().apply {
                    color = getBgColorStateList()
                }
            }
        }
    }

    // ---------------- APPLY SHAPE ----------------
    private fun applyShape() {
        val drawable = drawableBuilder ?: return

        drawable.shape = when (attrShapeType) {
            Shape.Rectangle, Shape.Stroke -> GradientDrawable.RECTANGLE
            Shape.Oval, Shape.StrokeCircle -> GradientDrawable.OVAL
        }

        // Corners
        if (attrShapeType == Shape.Rectangle || attrShapeType == Shape.Stroke) {
            if (attrCornerRadius > 0) {
                drawable.cornerRadius = attrCornerRadius
            } else {
                drawable.cornerRadii = floatArrayOf(
                    attrTopLeftCornerRadius, attrTopLeftCornerRadius,
                    attrTopRightCornerRadius, attrTopRightCornerRadius,
                    attrBottomRightCornerRadius, attrBottomRightCornerRadius,
                    attrBottomLeftCornerRadius, attrBottomLeftCornerRadius
                )
            }
        }

        // Stroke
        if (attrShapeType == Shape.Stroke || attrShapeType == Shape.StrokeCircle) {
            drawable.setStroke(attrStrokeWidth.toInt(), attrStrokeColor)
        }

        // Gradient
        if (attrGradientTopColor != -1) {
            drawable.colors = intArrayOf(
                attrGradientTopColor,
                if (attrGradientMiddleColor == -1) attrGradientTopColor else attrGradientMiddleColor,
                if (attrGradientBottomColor == -1) attrGradientTopColor else attrGradientBottomColor
            )
            drawable.orientation = getDrawableOrientation()
        }
    }

    // ---------------- APPLY BACKGROUND ----------------
    private fun applyBackground() {
        val drawable = drawableBuilder ?: return

        background = if (
            attrRippleColor != Color.TRANSPARENT
        ) {
            RippleDrawable(
                ColorStateList.valueOf(attrRippleColor),
                drawable,
                null
            )
        } else {
            drawable
        }
    }

    // ---------------- UTIL ----------------
    private fun getBgColorStateList(@ColorInt bgColor: Int? = null): ColorStateList {
        return if (bgColor == null) {
            ColorStateList(
                arrayOf(intArrayOf(-android.R.attr.state_enabled)),
                intArrayOf(attrBgDisableColor)
            )
        } else {
            ColorStateList(
                arrayOf(
                    intArrayOf(android.R.attr.state_enabled),
                    intArrayOf(-android.R.attr.state_enabled)
                ),
                intArrayOf(bgColor, attrBgDisableColor)
            )
        }
    }

    private fun getDrawableOrientation(): GradientDrawable.Orientation {
        return when (attrGradientOrientation) {
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

    // ---------------- PUBLIC API ----------------
    fun setShape(
        @ColorInt backgroundColor: Int,
        shapeType: Shape,
        @Dimension cornerRadius: Float = 0f
    ) {
        drawableBuilder = GradientDrawable().apply {
            color = getBgColorStateList(backgroundColor)
        }

        attrShapeType = shapeType
        attrCornerRadius = context.dp(cornerRadius)

        applyShape()
        applyBackground()
    }
}