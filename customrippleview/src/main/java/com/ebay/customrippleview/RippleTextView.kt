package com.ebay.customrippleview

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet


/**
 * Created by ahurwitz on 5/13/17.
 */

class RippleTextView : AppCompatTextView {

    private var rippleEnabled: Boolean = false
    private var colorOfBackground: Int = 0
    private var rippleColor: Int = 0

    constructor(context: Context) : super(context) {
        //Retrieve attribute values at runtime
        getXMLAttributes(context, null)
        RippleEffect.addRippleEffect(this, rippleEnabled, colorOfBackground, rippleColor)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        //Retrieve attribute values at runtime
        getXMLAttributes(context, attrs)
        RippleEffect.addRippleEffect(this, rippleEnabled, colorOfBackground, rippleColor)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        //Retrieve attribute values at runtime
        getXMLAttributes(context, attrs)
        RippleEffect.addRippleEffect(this, rippleEnabled, colorOfBackground, rippleColor)
    }

    private fun getXMLAttributes(context: Context, attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.RippleText,
                0, 0)
        try {
            rippleEnabled = typedArray.getBoolean(
                    R.styleable.RippleText_rippleEnabled,
                    true)
            colorOfBackground = typedArray.getColor(
                    R.styleable.RippleText_backgroundColor,
                    resources.getColor(R.color.background_default))
            rippleColor = typedArray.getColor(
                    R.styleable.RippleText_rippleColor,
                    resources.getColor(R.color.ripple_default))
        } finally {
            typedArray.recycle()
        }
    }

    fun getRippleColor(): Int {
        return rippleColor
    }

    fun setRippleColor(rippleColor: Int) {
        this.rippleColor = rippleColor
        invalidate()
    }

}
