package com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownSettingBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.FindTownActivity
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.ChangeMyTownRequest
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.MyTownSettingResponse
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.RangeUpdateRequest
import com.skott.softsquared.outsourcing_simulation.src.main.seek_town.SeekTownFragment
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.MyTownButtonView
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getAlertDialog

class MyTownSettingActivity : BaseActivity<MyTownSettingBinding>(MyTownSettingBinding::inflate),
    MyTownSettingView {
    private var activeTownCount = 0
    private val context = this
    private val myTownService = MyTownSettingService(this)
    private lateinit var clickButtonView: MyTownButtonView
    private lateinit var seekTownFragment: SeekTownFragment
    private lateinit var townInfo: ArrayList<MyTownSettingResponse>
    private lateinit var alertDialog: androidx.appcompat.app.AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seekTownFragment =
            context.supportFragmentManager.findFragmentById(R.id.my_town_range_fragment) as SeekTownFragment
        seekTownFragment.seekMapUdpateEvent { myTownService.tryPatchRangeUpdate(
            RangeUpdateRequest(seekTownFragment.getCurrentTownRange()+1,townInfo[getCurrentFocusIndex()].idx)
        ) }
    }

    override fun onStart() {
        super.onStart()
        myTownService.tryGetMyTown()
    }

    //초기화 함수
    private fun initTownEvent(button: MyTownButtonView) {
        clickButtonView=button

        binding.myTownFirstButton.setDelete(false)
        binding.myTownFirstButton.setActive(clickButtonView==binding.myTownFirstButton)

        if (townInfo.size == 2) {
            binding.myTownSecondButton.setDelete(false)
            binding.myTownSecondButton.setActive(clickButtonView==binding.myTownSecondButton)
        }
        else{
            binding.myTownSecondButton.setDelete(true)
        }
    }

    //삭제 클릭시 경고 메세지를 띄우는 이벤트
    fun townDeleteEvent(button: MyTownButtonView) {
        if (activeTownCount == 1)
            showWarningDialog(button)   //삭제시 주소 찾기 화면
        else
            showCheckDialog(button)     //삭제시 동네가 2개 였다면 삭제
    }

    //클릭된 버튼을 활성화 시키는 이벤트
    fun changeTownEvent(button: MyTownButtonView) {
        if (::clickButtonView.isInitialized && clickButtonView == button)
            return
        clickButtonView = button
        if (clickButtonView == binding.myTownFirstButton)
            myTownService.tryPatchMyTown(ChangeMyTownRequest(townInfo[0].idx))
        else
            myTownService.tryPatchMyTown(ChangeMyTownRequest(townInfo[1].idx))
    }

    // 삭제 이벤트
    private fun deleteEvent(button: MyTownButtonView) {
        clickButtonView = button
        //클릭 버튼 동네의 인덱스를 서버로 보냄
        if (clickButtonView == binding.myTownFirstButton)
            myTownService.tryPatchDeleteMyTown(ChangeMyTownRequest(townInfo[0].idx))
        else
            myTownService.tryPatchDeleteMyTown(ChangeMyTownRequest(townInfo[1].idx))

    }


    //1번 버튼 삭제 시 2번 버튼을 1번으로 옮김
    private fun changeLocation(firstButton: MyTownButtonView, secondButton: MyTownButtonView) {
        firstButton.setText(secondButton.getText())
        firstButton.setActive(true)
        secondButton.setDelete(true)
    }

    private fun showWarningDialog(button: MyTownButtonView) {
        val checkListener =
            DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->

                --activeTownCount
                val intent = Intent(this, FindTownActivity::class.java)
                intent.putExtra(context.getString(R.string.my_carrot_find_my_address_intent_key),townInfo[0].idx)
                startActivity(intent)
            }
        val cancelListener =
            DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                alertDialog.dismiss()
            }
        alertDialog = getAlertDialog(
            context,
            "",
            context.getString(R.string.my_town_setting_my_town_warning),
            checkListener,
            cancelListener,
            context.getString(R.string.my_town_setting_my_town_warning_check)
        )
        alertDialog.show()
    }

    private fun showCheckDialog(button: MyTownButtonView) {

        val checkListener =
            DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->

                --activeTownCount
                deleteEvent(button)
            }
        val cancelListener =
            DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                alertDialog.dismiss()
            }
        alertDialog = getAlertDialog(
            context,
            "",
            context.getString(R.string.my_town_setting_my_town_notice),
            checkListener,
            cancelListener
        )
        alertDialog.show()
    }

    override fun onGetMyTownSuccess(myTownSettingResponseArray: ArrayList<MyTownSettingResponse>) {
        this.townInfo = myTownSettingResponseArray
        binding.myTownFirstButton.setText(myTownSettingResponseArray[0].dong)
        if (myTownSettingResponseArray.size == 2)
            binding.myTownSecondButton.setText(myTownSettingResponseArray[1].dong)
        var temp = -1
        if (myTownSettingResponseArray[0].isCurrent.equals("YES")) {
            initTownEvent(binding.myTownFirstButton)
            temp = 0
        } else {
            initTownEvent(binding.myTownSecondButton)
            temp = 1
        }
        seekTownFragment.changeSeekTownEvent(townInfo, temp)
        activeTownCount = myTownSettingResponseArray.size
    }

    private fun getCurrentFocusIndex():Int
    {
        return if(clickButtonView == binding.myTownSecondButton) 1 else 0
    }
    override fun onPatchMyTownSuccess() {

        binding.myTownSecondButton.setDelete(false)
        binding.myTownSecondButton.setActive(clickButtonView != binding.myTownFirstButton)
        binding.myTownFirstButton.setDelete(false)
        binding.myTownFirstButton.setActive(clickButtonView != binding.myTownSecondButton)
        seekTownFragment.changeSeekTownEvent(townInfo,getCurrentFocusIndex())
    }

    override fun onPatchDeleteMyTownSuccess() {

        if (clickButtonView == binding.myTownFirstButton)
            changeLocation(binding.myTownFirstButton, binding.myTownSecondButton)
        else
        {
            clickButtonView.setDelete(true)
        }
        seekTownFragment.changeSeekTownEvent(townInfo, getCurrentFocusIndex())
    }

    override fun onPatchRangeUpdateSuccess() {
        townInfo[getCurrentFocusIndex()].rangeLevel = seekTownFragment.getCurrentTownRange()
    }

    override fun onGetMyTownFailure(message: String) {
        Log.e("api error", message)
    }

    override fun onPatchMyTownFailure(message: String) {
        Log.e("api error", message)
    }

    override fun onPatchDeleteMyTownFailure(message: String) {
        Log.e("api error", message)
    }

    override fun onPatchRangeUpdateFailure(message: String) {
        Log.e("api error", message)
    }
}