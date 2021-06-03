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
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass.Companion.sSharedPreferences
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignUpRequest
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.models.SignupResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.RegisterAddressRequest
import com.skott.softsquared.outsourcing_simulation.src.main.home.HomeActivity
import com.skott.softsquared.outsourcing_simulation.src.main.profile_setting.ProfileFragment
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.ProfileImageView

class CreateProfileActivity :
    BaseActivity<CreateProfileLayoutBinding>(CreateProfileLayoutBinding::inflate),
    CreateProfileView {
    private lateinit var context: Context
    private lateinit var editor: SharedPreferences.Editor
    private var dongIndex = -1
    private lateinit var nextIntent: Intent
    private lateinit var createProfileService: CreateProfileService
    private lateinit var cellphone: String
    private lateinit var nickname: EditText
    private lateinit var profilImage: ImageView
    private lateinit var profileImageView: ProfileImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        cellphone = sSharedPreferences.getString(context.getString(R.string.phone_number_key), "")
            .toString()

        dongIndex = intent.getIntExtra(context.getString(R.string.create_profile_intent_key),-1)
        if (cellphone.equals("")||dongIndex==-1) {
            showCustomToast("회원 정보를 처리 중 오류가 발생했습니다 다시 회원가입 해주세요.")
            finishAffinity()
            System.runFinalization()
            System.exit(0)
        }
        editor = sSharedPreferences.edit()
        profileImageView =
            (supportFragmentManager.findFragmentById(R.id.create_profile_content_fragment) as ProfileFragment).profileImageViewListener()
        nickname =
            (supportFragmentManager.findFragmentById(R.id.create_profile_content_fragment) as ProfileFragment).profileNicknameListener()
        profilImage = profileImageView.findViewById(R.id.profile_image)
        nextIntent = Intent(this, HomeActivity::class.java)
        setMainIntentEvent(binding.createProfileNextButton, cellphone, nickname)
        createProfileService = CreateProfileService(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("image test", data!!.dataString!!)
    }

    private fun setMainIntentEvent(button: Button, cellphone: String, editText: EditText) {
        button.setOnClickListener {
            val nickname = editText.text.toString()
            Log.d("test", nickname)
            if (nickname.length < 2) {
                showCustomToast(context.getString(R.string.create_profile_check_nickname_min))
                return@setOnClickListener
            } else if (20 < nickname.length) {
                showCustomToast(context.getString(R.string.create_profile_check_nickname_max))
                return@setOnClickListener
            } else {
                Log.d("fire base url", profileImageView.getPicture().pictureUrl)
                val picture = profileImageView.getPicture()
                createProfileService.trySignUp(
                    SignUpRequest(
                        cellphone,
                        nickname,
                        picture.pictureId,
                        "https://firebasestorage.googleapis.com${
                            picture.pictureUrl.replace(
                                "image/",
                                "image%2F"
                            )
                        }?alt=media"
                    )
                )
            }
        }
    }


    private fun showNotBackToast() {
        showCustomToast(context.getString(R.string.certificate_not_back))
    }

    override fun onBackPressed() {
        showNotBackToast()
    }

    override fun onSignUpSuccess(signupResponse: SignupResponse) {
        if (!signupResponse.jwt.equals("")) {
            editor.putString(context.getString(R.string.jwt_key), signupResponse.jwt)
            editor.putString(context.getString(R.string.phone_number_key), cellphone)
            editor.commit()
            createProfileService.tryPostRegisterAddress(RegisterAddressRequest(dongIndex))
        }
    }

    override fun onPostRegisterAddressSuccess() {

        startActivity(nextIntent)
        showCustomToast("회원 가입 성공!")
        finish()
    }

    override fun onSignUpFailure(message: String) {
        Log.e("api error", message)
    }


    override fun onPostRegisterAddressFailure(message: String) {
        Log.e("api error", message)
    }

    // 프래그먼트에서 데이터를 받아오기 위한 인터페이스
    interface ProfileDataInterface {
        fun profileImageViewListener(): ProfileImageView
        fun profileNicknameListener(): EditText
    }
}