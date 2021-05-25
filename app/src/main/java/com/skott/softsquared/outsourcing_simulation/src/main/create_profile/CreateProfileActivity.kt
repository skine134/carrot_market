package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.skott.config.ApplicationClass
import com.skott.config.ApplicationClass.Companion.sSharedPreferences
import com.skott.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.CreateProfileLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home.HomeActivity
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupRequest
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import com.skott.softsquared.outsourcing_simulation.src.main.fragments.ProfileFragment

class CreateProfileActivity :
    BaseActivity<CreateProfileLayoutBinding>(CreateProfileLayoutBinding::inflate),
    CreateProfileView {
    private lateinit var context: Context
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var nextIntent: Intent
    private lateinit var createProfileService: CreateProfileService
    private lateinit var phonenumber: String
    private lateinit var nickname: EditText
    private lateinit var profilImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        phonenumber =
            intent.getStringExtra(context.getString(R.string.sign_in_to_create_profile_phone_number_intent_key))!!
                .toString()
        editor = sSharedPreferences.edit()
        nickname =
            (supportFragmentManager.findFragmentById(R.id.create_profile_content_fragment) as ProfileFragment).profileEditTextListener()
        profilImage =
            (supportFragmentManager.findFragmentById(R.id.create_profile_content_fragment) as ProfileFragment).profileImageListener()
        nextIntent = Intent(this, HomeActivity::class.java)
        setMainIntentEvent(binding.createProfileNextButton, phonenumber, nickname)
        createProfileService = CreateProfileService(this)
    }

    private fun setMainIntentEvent(button: Button, phonenumber: String, editText: EditText) {
        button.setOnClickListener {
            val nickname = editText.text.toString()
            Log.d("test", nickname)
            if (nickname.length < 2) {
                showCustomToast(context.getString(R.string.create_profile_check_nickname_min))
                return@setOnClickListener
            }
            if (20 < nickname.length) {
                showCustomToast(context.getString(R.string.create_profile_check_nickname_max))
                return@setOnClickListener
            } else
                createProfileService.tryGetJwt(SignupRequest(phonenumber, nickname))
        }
    }

    override fun jwtListener(signupResponse: SignupResponse) {
        //TODO jwt 값이 들어오면 변겅.
//        if(!signupResponse.jwt.equals(""))
//        {
//            editor.putString(context.getString(R.string.jwt_key),signupResponse.jwt)
//            editor.apply()
//        }
        startActivity(nextIntent)
        showCustomToast("회원 가입 성공!")
        finish()
    }

    override fun signUpErrorListener(message: String) {
        showCustomToast(message)
    }

    interface ProfileDataInterface {
        fun profileImageListener(): ImageView
        fun profileEditTextListener(): EditText
    }
}