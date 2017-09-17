package com.ebay.customrippleview

import android.content.res.ColorStateList
import android.graphics.drawable.*
import android.graphics.drawable.shapes.RectShape
import android.os.Build
import android.support.v4.view.ViewCompat
import android.util.StateSet
import android.view.View


/**
 * Created by ahurwitz on 5/13/17.
 */

class RippleEffect {

    companion object{
        @JvmStatic
        fun addRippleEffect(view: View, rippleEnabled: Boolean, backgroundColor: Int, rippleColor: Int) {

            if (rippleEnabled && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                //Create RippleDrawable
                view.background = getPressedColorRippleDrawable(backgroundColor, rippleColor)

                //Customize Ripple color
                val rippleDrawable = view.background as RippleDrawable
                val states = arrayOf(intArrayOf(android.R.attr.state_enabled))
                val colors = intArrayOf(rippleColor)
                val colorStateList = ColorStateList(states, colors)
                rippleDrawable.setColor(colorStateList)
                view.background = rippleDrawable

            } else if (rippleEnabled) {
                //Create Selector for pre Lollipop
                ViewCompat.setBackground(view, createStateListDrawable(backgroundColor, rippleColor))
            } else {
                //Ripple Disabled
                view.background = ColorDrawable(backgroundColor)
            }
        }

        @JvmStatic
        private fun getPressedColorRippleDrawable(normalColor: Int, pressedColor: Int): Drawable {
            return RippleDrawable(getPressedColorSelector(normalColor, pressedColor), getColorDrawableFromColor(normalColor), null)
        }

        @JvmStatic
        private fun createStateListDrawable(backgroundColor: Int, rippleColor: Int): StateListDrawable {
            val stateListDrawable = StateListDrawable()
            stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), createDrawable(rippleColor))
            stateListDrawable.addState(StateSet.WILD_CARD, createDrawable(backgroundColor))
            return stateListDrawable
        }

        @JvmStatic
        private fun getPressedColorSelector(normalColor: Int, pressedColor: Int): ColorStateList {
            return ColorStateList(
                    arrayOf(intArrayOf()),
                    intArrayOf(pressedColor)
            )
        }

        @JvmStatic
        private fun getColorDrawableFromColor(color: Int): ColorDrawable {
            return ColorDrawable(color)
        }

        @JvmStatic
        private fun createDrawable(background: Int): Drawable {
            val shapeDrawable = ShapeDrawable(RectShape())
            shapeDrawable.paint.color = background
            return shapeDrawable
        }
    }
}
