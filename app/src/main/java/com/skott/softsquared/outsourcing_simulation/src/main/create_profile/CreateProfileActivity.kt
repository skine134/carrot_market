package com.skott.softsquared.outsourcing_simulation.src.main.create_profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.CreateProfileLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass.Companion.sSharedPreferences
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupRequest
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import com.skott.softsquared.outsourcing_simulation.src.main.fragments.ProfileFragment
import com.skott.softsquared.outsourcing_simulation.src.main.home.HomeActivity
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        showCustomToast(data!!.dataString!!)
        super.onActivityResult(requestCode, resultCode, data)
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
                createProfileService.trySignUp(SignupRequest(phonenumber, nickname))
        }
    }

    override fun onSignInSuccess(signinResponse: SigninResponse) {
        //TODO jwt 값이 들어오면 변겅.
        if(!signinResponse.jwt.equals(""))
        {
            editor.putString(context.getString(R.string.jwt_key),signinResponse.jwt)
            editor.apply()
        }
        startActivity(nextIntent)
        showCustomToast("회원 가입 성공!")
        finish()
    }

    override fun onSignUpSuccess(signupResponse: SignupResponse) {
        createProfileService.trySignIn(SigninRequest(phonenumber))
    }

    override fun onSignInFailure(message: String) {
        showCustomToast(message)
    }

    override fun onSignUpFailure(message: String) {
        showCustomToast(message)
    }

    interface ProfileDataInterface {
        fun profileImageListener(): ImageView
        fun profileEditTextListener(): EditText
    }
}