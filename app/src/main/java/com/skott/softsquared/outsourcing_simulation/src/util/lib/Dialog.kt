package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.content.Context
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ListPopupWindow


private fun getPopupMenu(context : Context, adapter: BaseAdapter, button : ImageButton, width:Int, height:Int,bias:Int=-100) : ListPopupWindow {
    /**
     * 팝업 메뉴를 생성합니다.
     */
    val popupWindow = ListPopupWindow(context)
    popupWindow.setAdapter(adapter)
    popupWindow.width = width
    popupWindow.height = height
// TODO: maxheight 지정해야하면 여기 아래 지정할 것.
    popupWindow.horizontalOffset = convertDpToPixel(context,bias).toInt()
    popupWindow.anchorView = button
    return popupWindow
}