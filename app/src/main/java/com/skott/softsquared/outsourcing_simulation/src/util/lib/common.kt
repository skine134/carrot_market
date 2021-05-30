package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun getScrollListener(event:()->Unit): RecyclerView.OnScrollListener
{
    return object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() // 화면에 보이는 마지막 아이템의 position
            val itemTotalCount = recyclerView.adapter!!.itemCount -5 // 어댑터에 등록된 아이템의 총 개수 -5
            Log.d("lastVisibleItem",lastVisibleItemPosition.toString())

                // 마지막으로 보여진 아이템 position 이
                // 전체 아이템 개수보다 5개 모자란 경우, 데이터를 loadMore 한다
                if (lastVisibleItemPosition >= itemTotalCount) {
                    event()
                }
        }
    }
}


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
//fun findView(context: Context,x:Int,y:Int):View?
//{
//    val viewGroup = context as Activity
//    for(view in viewGroup.window.do)
//        return view
//    return null
//}