package com.skott.softsquared.outsourcing_simulation.src.main.app_setting

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.AppSettingLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.certification.CertificationActivity
import com.skott.softsquared.outsourcing_simulation.src.main.delete_user.DeleteUserActivity
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertPixelsToDp
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getAlertDialog

class AppSettingActivity : BaseActivity<AppSettingLayoutBinding>(AppSettingLayoutBinding::inflate),
    TextViewListener {
    private lateinit var context: Context
    private lateinit var signOutAlertDialog: AlertDialog
    private lateinit var userSettingAdapter: AppSettingAdapter
    private lateinit var otherSettingAdapter: AppSettingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        val userArrayList = ArrayList<String>()
        setUserSetting(userArrayList)
        userSettingAdapter = AppSettingAdapter(context, userArrayList)
        val otherArrayList = ArrayList<String>()
        setOtherSetting(otherArrayList)
        otherSettingAdapter = AppSettingAdapter(context, otherArrayList)
        binding.userSettingRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.otherSettingRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.userSettingRecyclerView.adapter = userSettingAdapter
        binding.otherSettingRecyclerView.adapter = otherSettingAdapter

        val itemDecoration=object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val margin = convertDpToPixel(context,20).toInt()
                outRect.top = margin
                outRect.bottom = margin

                val marginHorizontal = convertPixelsToDp(context,10f).toInt()
                outRect.left = marginHorizontal
            }
        }
        binding.userSettingRecyclerView.addItemDecoration(itemDecoration)
        binding.otherSettingRecyclerView.addItemDecoration(itemDecoration)
    }

    private fun setUserSetting(arrayList: ArrayList<String>) {
        arrayList.add(context.getString(R.string.app_setting_session_info_setting))
        arrayList.add(context.getString(R.string.app_setting_group_view_user_setting))
        arrayList.add(context.getString(R.string.app_setting_blacklist_user_setting))
        arrayList.add(context.getString(R.string.app_setting_no_show_post_user_setting))
        arrayList.add(context.getString(R.string.app_setting_other_setting))

    }

    private fun setOtherSetting(arrayList: ArrayList<String>) {
        arrayList.add(context.getString(R.string.app_setting_language_setting))
        arrayList.add(context.getString(R.string.app_setting_alert_cache))
        arrayList.add(context.getString(R.string.app_setting_license))
        arrayList.add(context.getString(R.string.app_setting_version))
        arrayList.add(context.getString(R.string.app_setting_sign_out))
        arrayList.add(context.getString(R.string.app_setting_delete_user))
    }


    private fun setSignOutEvent(button: View) {
        if (::signOutAlertDialog.isInitialized) {
            signOutAlertDialog.show()
        } else {
            val cancelEvent =
                DialogInterface.OnClickListener { dialog, which -> signOutAlertDialog.closeOptionsMenu() }
            val checkEvent = DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this, CertificationActivity::class.java)
                ApplicationClass.sSharedPreferences.edit()
                    .remove(context.getString(R.string.jwt_key)).apply()
                startActivity(intent)
            }
            signOutAlertDialog = getAlertDialog(
                context,
                context.getString(R.string.app_setting_sign_out_dialog_title),
                context.getString(
                    R.string.app_setting_sign_out_dialog_message
                ),
                checkEvent,
                cancelEvent
            )
            button.setOnClickListener{
                signOutAlertDialog.show()
            }
        }
    }

    private fun setDeleteUserIntentEvent(button: View) {
        button.setOnClickListener {
            val intent = Intent(context, DeleteUserActivity::class.java)
            startActivity(intent)
        }
    }

    override fun signOutViewListener(textView: TextView) {
        setSignOutEvent(textView)
    }

    override fun deleteUserViewListeber(textView: TextView) {
        setDeleteUserIntentEvent(textView)
    }

}