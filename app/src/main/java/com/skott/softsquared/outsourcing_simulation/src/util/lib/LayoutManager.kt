package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class SpacesItemDecoration(val spanCount:Int, private val spacing: Int,val includeEdge:Boolean) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view) // item position
        if (position >= 0) {
            val column = position % spanCount // item column
            outRect.left =
                spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
                (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect.left = 0
            outRect.right = 0
            outRect.top = 0
            outRect.bottom = 0
        }
    }
}
fun getLinearLayoutManager(
    context: Context,
    widthPercent: Float,
    heightPercent: Float,
    orientation: Int = LinearLayoutManager.VERTICAL
): LinearLayoutManager {
    /**
     * 리니어레이아웃을 설정해서 반환합니다.
     *
     * @param contex : Context
     * @param widthPercent : 가로비율
     * @param heightPercent : 세로 비율
     * @param orientation : 리스트의 방향
     *
     * @return linearlayout
     */
    return object : LinearLayoutManager(context, orientation, false) {
        override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
            lp.width = (width * widthPercent).toInt()
            lp.height = (height * heightPercent).toInt()
            return super.checkLayoutParams(lp)
        }
    }
}