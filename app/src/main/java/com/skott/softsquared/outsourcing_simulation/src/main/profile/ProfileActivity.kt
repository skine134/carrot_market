package com.skott.softsquared.outsourcing_simulation.src.main.profile

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import androidx.core.os.bundleOf
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProfileLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.profile.model.ProfileResponse

class ProfileActivity : BaseActivity<ProfileLayoutBinding>(ProfileLayoutBinding::inflate),
    ProfileView {
    private lateinit var context: Context
    private val profileService = ProfileService(this)
    private var userIndex=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        userIndex = intent.getIntExtra(context.getString(R.string.profile_intent_key),-1)
        if(userIndex!=-1)
            profileService.tryGetProfile(userIndex)
        else
            showCustomToast("사용자 정보를 불러오지 못하였습니다.")
        setCollectSeller(binding.productCollect)
        setDontTouchSeekBar(binding.profileTemperatureSeekBar)
    }
    private fun setDontTouchSeekBar(seekBar: SeekBar)
    {
        seekBar.setOnTouchListener { v, event ->  return@setOnTouchListener true}
    }
    private fun setCollectSeller(checkBox: CheckBox)
    {
        checkBox.setOnClickListener{
            profileService.tryPostCollectUser(userIndex)
            if(checkBox.isChecked)
            {

                checkBox.setTextColor(context.getColor(R.color.white))
                checkBox.text = context.getString(R.string.collect_user_management_enable_button)
                showCustomToast("모아보기에 추가 되었습니다.")
            }else
            {   checkBox.setTextColor(context.getColor(R.color.black))
                checkBox.text = context.getString(R.string.collect_user_management_disable_button)
                showCustomToast("모아보기에서 삭제 되었습니다.")
            }
        }
    }
    override fun onGetProfileSuccess(profileResponse: ProfileResponse) {
        binding.profileUserInfoNickname.text =
            context.getString(R.string.profile_nickname).replace("name", profileResponse.nickName)
        binding.profileUserInfoCode.text = context.getString(R.string.profile_user_code)
            .replace("code", String.format("%07d", profileResponse.idx))

        //TODO 온도에따라 색 변화
        binding.temperature.text =
            context.getString(R.string.profile_manner_current_temperature)
                .replace("count", profileResponse.mannerTemperature.toString())
        binding.rememberWanted.text = context.getString(R.string.profile_remember_wanted_percent)
            .replace("percent", profileResponse.reDealHopeRate.toString())
        binding.satisfy.text = context.getString(R.string.profile_satisfy)
            .replace("n", profileResponse.numOfTotalDeals.toString())
            .replace("m", profileResponse.numOfGoodDeals.toString())
        binding.commentPercent.text = context.getString(R.string.profile_comment_percent)
            .replace("-", profileResponse.answerRate.toString())
        binding.commentAverrageTime.text = context.getString(R.string.profile_comment_time)
            .replace("time", profileResponse.answerSpeed)
        if(profileResponse.userLocation!=null&&profileResponse.userLocation.size>0)
        binding.authCount.text = context.getString(R.string.profile_auth_count)
            .replace("town", profileResponse.userLocation[0].siGunGuDong)
            .replace("count", profileResponse.userLocation[0].numOfAuthorization.toString())
        binding.signUpDay.text = context.getString(R.string.profile_active_time).replace("registerDate",profileResponse.registerDate).replace("count",profileResponse.recentActivePeriod)
        binding.actionBadgeText.text=context.getString(R.string.profile_action_badge).replace("count",profileResponse.numOfBadges.toString())
        binding.saleProductText.text= context.getString(R.string.sold_count).replace("n",profileResponse.numOfItems.toString())
    }

    override fun onGetProfileFailure(message: String) {
        Log.e("api error", message)
    }

    override fun onPostCollectUserSuccess() {
    }

    override fun onPostCollectUserFailure(message: String) {
        Log.e("api error", message)
    }
}