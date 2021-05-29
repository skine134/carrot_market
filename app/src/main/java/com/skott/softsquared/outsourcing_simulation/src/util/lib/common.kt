package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun dispatchKeyboardEvent(context: Context,motionEvent:MotionEvent): MotionEvent {
        val focusView = (context as Activity).currentFocus
        if (focusView != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = motionEvent.x.toInt()
            val y = motionEvent.y.toInt()
            if (!rect.contains(x, y)) {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0)
                focusView.clearFocus()
            }
        }
        return motionEvent
}