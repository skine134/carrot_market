package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.get
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.CreateProfileLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass.Companion.sSharedPreferences
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignUpRequest
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import com.skott.softsquared.outsourcing_simulation.src.main.home.HomeActivity
import com.skott.softsquared.outsourcing_simulation.src.main.profile.ProfileFragment
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.ProfileImageView

class CreateProfileActivity :
    BaseActivity<CreateProfileLayoutBinding>(CreateProfileLayoutBinding::inflate),
    CreateProfileView {
    private lateinit var context: Context
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var nextIntent: Intent
    private lateinit var createProfileService: CreateProfileService
    private lateinit var cellphone: String
    private lateinit var nickname: EditText
    private lateinit var profilImage: ImageView
    private lateinit var profileImageView:ProfileImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        cellphone =
            intent.getStringExtra(context.getString(R.string.certificate_to_create_profile_phone_number_intent_key))!!
                .toString()
        editor = sSharedPreferences.edit()
        profileImageView = (supportFragmentManager.findFragmentById(R.id.create_profile_content_fragment) as ProfileFragment).profileImageViewListener()
        nickname = (supportFragmentManager.findFragmentById(R.id.create_profile_content_fragment) as ProfileFragment).profileNicknameListener()
        profilImage = profileImageView.findViewById(R.id.profile_image)
        nextIntent = Intent(this, HomeActivity::class.java)
        setMainIntentEvent(binding.createProfileNextButton, cellphone, nickname)
        createProfileService = CreateProfileService(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("image test",data!!.dataString!!)
    }
    private fun setMainIntentEvent(button: Button, cellphone: String, editText: EditText) {
        button.setOnClickListener {
            val nickname = editText.text.toString()
            Log.d("test", nickname)
            if (nickname.length < 2) {
                showCustomToast(context.getString(R.string.create_profile_check_nickname_min))
                return@setOnClickListener
            }
            else if (20 < nickname.length) {
                showCustomToast(context.getString(R.string.create_profile_check_nickname_max))
                return@setOnClickListener
            } else
            {
                Log.d("fire base url",profileImageView.getPicture().pictureUrl)
                createProfileService.trySignUp(SignUpRequest(cellphone, nickname,profileImageView.getPicture()))
            }
        }
    }


    private fun showNotBackToast()
    {
        showCustomToast(context.getString(R.string.certificate_not_back))
    }

    override fun onBackPressed() {
        showNotBackToast()
    }
    override fun onSignUpSuccess(signupResponse: SignupResponse) {
        if(!signupResponse.jwt.equals(""))
        {
            editor.putString(context.getString(R.string.jwt_key),signupResponse.jwt)
            editor.putString(context.getString(R.string.phone_number_key),cellphone)
            editor.apply()
        }
        startActivity(nextIntent)
        showCustomToast("회원 가입 성공!")
        finish()
    }
    override fun onSignUpFailure(message: String) {
        Log.e("api error",message)
    }
    // 프래그먼트에서 데이터를 받아오기 위한 인터페이스
    interface ProfileDataInterface {
        fun profileImageViewListener():ProfileImageView
        fun profileNicknameListener():EditText
    }
}