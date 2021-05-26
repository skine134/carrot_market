package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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