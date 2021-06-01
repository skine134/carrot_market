package com.skott.softsquared.outsourcing_simulation.src.main.delete_user

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.get
import androidx.core.view.isGone
import androidx.core.view.size
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.DeleteUserLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.certification.CertificationActivity


class DeleteUserActivity : BaseActivity<DeleteUserLayoutBinding>(DeleteUserLayoutBinding::inflate),DeleteUserView {
    private lateinit var context: Context
    private lateinit var radioDialog: AlertDialog
    private var currentCheckedNum = 0
    private lateinit var deleteUserService: DeleteUserService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
        val arrayList = ArrayList<String>()
        setRadioArray(arrayList)
        radioDialog = getRadioDialog(arrayList)
        deleteUserService = DeleteUserService(this)
        setShowRadioDialogEvent(binding.showRadioDialogLayout,radioDialog)
        setDeleteEvent(binding.deleteNo,binding.deleteYes)
    }
    private fun setShowRadioDialogEvent(layout:ViewGroup,dialog: AlertDialog)
    {
        layout.setOnClickListener{
            if(dialog.listView.size>0)
                dialog.listView.get(0).isClickable = currentCheckedNum == 0
            dialog.show()
        }
    }
    private fun setDeleteEvent(noButton:Button,yesButton:Button)
    {
        noButton.setOnClickListener{
            finish()
        }
        yesButton.setOnClickListener{
            //TODO intent 한번 더 하고 삭제 해야함.
            deleteUserService.tryPatchDeleteUser()
        }
    }
    private fun setRadioArray(arrayList: ArrayList<String>) {
        arrayList.add(context.getString(R.string.delete_user_please_select_delete_reason))
        arrayList.add(context.getString(R.string.delete_user_reason_too_many))
        arrayList.add(context.getString(R.string.delete_user_reason_not_found_wanted_item))
        arrayList.add(context.getString(R.string.delete_user_reason_not_sold_product))
        arrayList.add(context.getString(R.string.delete_user_reason_see_bad_man))
        arrayList.add(context.getString(R.string.delete_user_reason_create_new_user))
        arrayList.add(context.getString(R.string.delete_user_reason_other))
    }

    private fun getRadioDialog(arrayList: ArrayList<String>): AlertDialog {
        //TODO Custom Dialog
        val builder = AlertDialog.Builder(context)
        builder.setSingleChoiceItems(arrayList.toTypedArray(), currentCheckedNum)
        { dialogInterface: DialogInterface, checkedNum: Int ->
            currentCheckedNum=checkedNum
            binding.deleteUserBottomContent.isGone = currentCheckedNum == 0
            binding.selectReasonText.text = arrayList[currentCheckedNum]
            binding.deleteYes.isEnabled = !binding.selectReasonText.text.equals(context.getString(R.string.delete_user_reason_other))
            dialogInterface.dismiss()
        }
        val alertDialog = builder.create()
        return alertDialog
    }

    override fun onPatchDeleteUserSuccess() {
        ApplicationClass.sSharedPreferences.edit().remove(context.getString(R.string.jwt_key)).apply()
        val intent = Intent(context,CertificationActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPatchDeleteUserFailure(message: String) {
        Log.e("api error",message)
    }


}