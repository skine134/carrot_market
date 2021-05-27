package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar

class RulerSeekBar(context: Context,attrs: AttributeSet?=null, defStyleAttr:Int=0) : AppCompatSeekBar(context,attrs,defStyleAttr) {

    /**
     * Tick brush
     */
    private lateinit var mRulerPaint : Paint

    /**
     * The number of tick marks, equal to the number of tick marks plus 1
     */
    private var mRulerCount = 3

    /**
     * width of each tick mark
     */
    private var mRulerWidth = 2

    /**
     * The color of the tick mark
     */
    private var mRulerColor = Color.WHITE

    /**
     * Is there a tick mark on the slider?
     */
    private var isShowTopOfThumb = false

    /**
     * Initialization
     */
    private fun init() {
        // Create a brush to draw tick marks
        mRulerPaint = Paint()
        mRulerPaint.color = mRulerColor
        mRulerPaint.isAntiAlias = true

        //둥글게 만들기 위함.
        mRulerPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

        splitTrack = false
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (width <= 0 || mRulerCount <= 0) {
            return
        }


        val length = (width - paddingLeft - paddingRight - mRulerCount * mRulerWidth) / (mRulerCount + 1)

        // Calculate the top and bottom coordinates of the tick mark
        val rulerTop = height / 2 - minimumHeight / 2
        val rulerBottom = rulerTop + minimumHeight

        // Get the position information of the slider
        var thumbRect:Rect? = null
        if (thumb != null) {
            thumbRect = thumb.bounds
        }

        // draw tick marks
        for (i in 1..mRulerCount) {
            // Calculate the left and right coordinates of the tick mark
            val rulerLeft = i * length + getPaddingLeft()
            val rulerRight = rulerLeft + mRulerWidth

            // Determine whether you need to draw a tick
            if (!isShowTopOfThumb && thumbRect != null && rulerLeft - paddingLeft > thumbRect.left && rulerRight - paddingLeft < thumbRect.right) {
                continue
            }

            // draw
            canvas!!.drawRect(rulerLeft.toFloat(),
                rulerTop.toFloat(), rulerRight.toFloat(), rulerBottom.toFloat(), mRulerPaint)
    }
    }

    /**
     * Set the number of tick marks
     *
     * @param mRulerCount
     */
    public fun setRulerCount(mRulerCount:Int) {
        this.mRulerCount = mRulerCount
        requestLayout()
    }

    /**
     * Set the width of the tick marks in units (px)
     *
     * @param mRulerWidth
     */
    fun setRulerWidth(mRulerWidth:Int) {
        this.mRulerWidth = mRulerWidth
        requestLayout()
    }

    /**
     * Set the color of the tick mark
     *
     * @param mRulerColor
     */
    fun setRulerColor(mRulerColor:Int) {
        this.mRulerColor = mRulerColor
        mRulerPaint.color = mRulerColor
        requestLayout()
    }

    /**
     * Is it necessary to display the tick marks on the slider?
     *
     * @param isShowTopOfThumb
     */
    fun setShowTopOfThumb(isShowTopOfThumb:Boolean) {
        this.isShowTopOfThumb = isShowTopOfThumb
        requestLayout()
    }
}
