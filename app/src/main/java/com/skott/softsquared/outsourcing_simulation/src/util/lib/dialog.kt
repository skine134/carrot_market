package com.skott.softsquared.outsourcing_simulation.src.util.lib

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ListPopupWindow
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.skott.softsquared.outsourcing_simulation.R


fun getPopupMenu(
    context: Context,
    adapter: BaseAdapter,
    button: ImageButton,
    width: Int,
    height: Int,
    bias: Int = -100
): ListPopupWindow {
    /**
     * 팝업 메뉴를 생성합니다.
     */
    val popupWindow = ListPopupWindow(context)
    popupWindow.setAdapter(adapter)
    popupWindow.width = width
    popupWindow.height = height
// TODO: maxheight 지정해야하면 여기 아래 지정할 것.
    popupWindow.horizontalOffset = convertDpToPixel(context, bias).toInt()
    popupWindow.anchorView = button
    return popupWindow
}

fun getListDialog(
    context: Context,
    itemList:ArrayList<String>,
    event: DialogInterface.OnClickListener
): AlertDialog {
    val builder = AlertDialog.Builder(context as Activity)
    builder.setItems(
        itemList.toArray(arrayOfNulls(itemList.size)),
         event)
    return builder.create()
}

fun getAlertDialog(
    context: Context,
    title:String,
    message:String,
    checkEvent: DialogInterface.OnClickListener,
    cancelEvent: DialogInterface.OnClickListener,
    checkText:String="확인",
    cancelText:String="취소",
): androidx.appcompat.app.AlertDialog {
    val builder = MaterialAlertDialogBuilder(context as Activity,R.style.AlertDialogTheme)
    builder.setMessage(message).setPositiveButton(checkText,checkEvent).setNegativeButton(cancelText,cancelEvent)
    builder.setTitle(title)
    val dialog = builder.create()
    return dialog
}