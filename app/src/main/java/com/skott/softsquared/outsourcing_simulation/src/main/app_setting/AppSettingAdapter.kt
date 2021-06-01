package com.skott.softsquared.outsourcing_simulation.src.main.app_setting

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.main.certification.CertificationActivity
import com.skott.softsquared.outsourcing_simulation.src.main.delete_user.DeleteUserActivity
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getAlertDialog

interface TextViewListener
{
    fun signOutViewListener(textView: TextView)
    fun deleteUserViewListeber(textView:TextView)
}

class AppSettingAdapter(val context: Context,val arrayList: ArrayList<String>):RecyclerView.Adapter<AppSettingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AppSettingViewHolder {
        val textView = TextView(context)
        textView.setTextAppearance(R.style.AppSetting)
        return AppSettingViewHolder(context, textView)
    }

    override fun onBindViewHolder(holder: AppSettingViewHolder, position: Int) {
        holder.bind(arrayList[position])
        if ((holder.itemView as TextView).text.equals(context.getString(R.string.app_setting_sign_out)))
            (context as AppSettingActivity).signOutViewListener(holder.itemView)

        if ((holder.itemView as TextView).text.equals(context.getString(R.string.app_setting_delete_user)))
            (context as AppSettingActivity).deleteUserViewListeber(holder.itemView)
    }

    override fun getItemCount()=arrayList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}