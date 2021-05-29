package com.skott.softsquared.outsourcing_simulation.src.main.certification

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.CertificationLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.config.CERTIFICATIONS_TIME
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.CertificationResponse
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.MobileCheckRequest
import com.skott.softsquared.outsourcing_simulation.src.main.certification.models.SignInResponse
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.CreateProfileActivity
import com.skott.softsquared.outsourcing_simulation.src.main.findid.FindIdForEmailActivity
import com.skott.softsquared.outsourcing_simulation.src.main.home.HomeActivity
import java.util.*
import kotlin.concurrent.fixedRateTimer


class CertificationActivity : BaseActivity<CertificationLayoutBinding>(CertificationLayoutBinding::inflate),CertificationActivityView {
    private var currentSelection = 0
    private lateinit var context : Context
    private lateinit var nextIntent:Intent
    private lateinit var certificateService: CertificationService
    private lateinit var authTimer: Timer
    private var authTime = 0
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var phoneNumber: String
    private lateinit var mobileCheckRequest:MobileCheckRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        certificateService = CertificationService(this)
        editor = ApplicationClass.sSharedPreferences.edit()
        authTimer = getAuthNumberTimerEvent(binding.certificateAuthTimer)
        //TODO: 로그아웃 또는 회원 탈퇴시 어떻게?
        setBackButtonEvent(binding.certificateAppBar.getBackButton())
        setCellphoneInputEvent(binding.certificateCellphoneEditText,binding.certificateTakeAuthNumberButton)
        setFindIdForEmailIntentEvent(binding.certificateFindIdForEmailTextView)
        setCallAuthNumEvent(binding.certificateTakeAuthNumberButton,binding.certificateCellphoneEditText)
        setAuthInputEvent(binding.certificateAuthNumberEditText,binding.certificateAcceptAndStartButton)
        setAcceptAndStartIntentEvent(
            binding.certificateAcceptAndStartButton,
            binding.certificateAuthNumberEditText,
            binding.certificateCellphoneEditText
        )
    }

    private fun setBackButtonEvent(button: ImageButton)
    {
        button.setOnClickListener{
            showNotBackToast()
        }
    }
    private fun setCellphoneInputEvent(inputField: EditText, button: Button) {
        inputField.setOnKeyListener(object: View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                val editText = v as EditText
                val text = editText.text.toString()
                val isMaxLength = text.replace(" ","").length>=11
                button.isEnabled = isMaxLength
//                if(keyCode== KeyEvent.KEYCODE_BACK)
//                    showNotBackToast()
                if(keyCode== KeyEvent.KEYCODE_ENTER)
                {
                    val keyBoard = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputField.clearFocus() //에딧 텍스트 포커스를 제거
                    keyBoard.hideSoftInputFromWindow(inputField.windowToken,0); //키보드 내려줌.
                }
                if(keyCode!= KeyEvent.KEYCODE_DEL){
                    //TODO ',' ' ' 필터 없이 바로 처리됌.
                    try {
                        Integer.parseInt((event.unicodeChar.toChar()).toString())
                    } catch (ne: NumberFormatException) {
                        return true
                    }
                    if (isMaxLength) {
                        return true
                    }
                }
                if(event.action == KeyEvent.ACTION_DOWN)
                    return false
                //editText selection은 1부터 시작
                currentSelection = editText.selectionStart
                editText.setText(filterPhoneNumber(editText.text.toString()))
                if(keyCode== KeyEvent.KEYCODE_DEL)
                {
                    if(editText.text.length==currentSelection-1) {
                        if(currentSelection==9||currentSelection==4)
                            --currentSelection
                    }
                }
                editText.setSelection(currentSelection)
                return false
            }
        })
    }
    private fun setFindIdForEmailIntentEvent(textView: TextView)
    {
        val content = SpannableString(textView.text.toString())
        content.setSpan(UnderlineSpan(), 0, textView.text.toString().length, 0)
        textView.text = content
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setOnClickListener{
            val intent = Intent(this, FindIdForEmailActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setCallAuthNumEvent(button: Button, textView: TextView)
    {
        button.setOnClickListener{
//            nextIntent = Intent(this, certificateActivity::class.java)
//            nextIntent.putExtra(context.getString(R.string.certificate_to_sign_in_phone_number_intent_key),textView.text.toString())
            certificateService.tryGetCertifications(textView.text.toString().replace(" ",""))
        }
    }
    private fun filterPhoneNumber(phoneNumber:String):String
    {

        val textArray = phoneNumber.replace(" ", "").toMutableList()
        when (textArray.count()) {
            4, 5, 6, 7
            -> {
                textArray.add(3, ' ')
                if(currentSelection==4)
                    ++currentSelection
            }
            8, 9, 10, 11
            -> {
                textArray.add(3, ' ')
                textArray.add(8, ' ')
                if(currentSelection==9)
                    ++currentSelection
            }
        }
        return textArray.joinToString("")
    }
    private fun showNotBackToast()
    {
        showCustomToast(context.getString(R.string.certificate_not_back))
    }
    private fun setAuthInputEvent(editText: EditText,button:Button)
    {
        editText.setOnKeyListener { v, keyCode, event ->
            val editText = v as EditText
            val text = editText.text.toString()
            if(keyCode== KeyEvent.KEYCODE_BACK)
                showNotBackToast()
            if(keyCode== KeyEvent.KEYCODE_ENTER) {
                val keyBoard = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                editText.clearFocus() //에딧 텍스트 포커스를 제거
                keyBoard.hideSoftInputFromWindow(editText.windowToken,0); //키보드 내려줌.
            }
            button.isEnabled = editText.text.toString().isNotEmpty()
            return@setOnKeyListener false
        }
    }

    private fun getAuthNumberTimerEvent(textView: TextView): Timer {
        this.authTime = CERTIFICATIONS_TIME
        return fixedRateTimer(period = 1000) {
            --authTime

            val remainTime = getRemainTime(authTime)
            runOnUiThread {

                if (authTime <= 0) {
                    textView.text = ""
                    return@runOnUiThread
                }
                textView.text = remainTime
            }

        }
    }
    private fun setAcceptAndStartIntentEvent(
        button: Button,
        authEditext: EditText,
        phonenumberEditText:EditText
    ) {

        button.setOnClickListener {
            mobileCheckRequest=MobileCheckRequest(phonenumberEditText.text.toString().replace(" ", ""),authEditext.text.toString())
            if(ApplicationClass.sSharedPreferences.getString(context.getString(R.string.jwt_key),"").equals(""))
                certificateService.tryPostSignUpMobileCheck(mobileCheckRequest)
            else
                certificateService.tryGetJwt(mobileCheckRequest)
        }
    }

    private fun getRemainTime(remainSeconds: Int): String {
        return "${String.format("%02d", remainSeconds / 60)}:${
            String.format(
                "%02d",
                remainSeconds % 60
            )
        }"
    }
    private fun showCertificationEditText()
    {
        binding.certificateIsChangePhoneNumberTextView.isGone=true
        binding.certificateFindIdForEmailTextView.isGone=true
        binding.inputAuthNumLayout.isVisible=true
        binding.certificateNoticeLayout.isGone=true
        binding.certificateAppBar.getTitle().text = context.getString(R.string.certificate_app_bar_title)
    }

    override fun onSignUpMobileCheckSuccess() {
        val nextActivity = CreateProfileActivity::class.java
        val intent = Intent(this,nextActivity)
        intent.putExtra(context.getString(R.string.certificate_to_create_profile_phone_number_intent_key),mobileCheckRequest.mobile)
        showCustomToast("사용자 인증 성공!")
        startActivity(intent)
        finish()
    }

    override fun onSignUpMobileCheckFailure(message: String) {
        Log.e("api error",message)
    }

    override fun onSignInSuccess(certificateResponse: SignInResponse) {
        editor.putString(context.getString(R.string.jwt_key), certificateResponse.jwt)
        editor.putString(context.getString(R.string.phone_number_key),mobileCheckRequest.mobile)
        editor.apply()
        val nextActivity = HomeActivity::class.java
        val intent = Intent(this,nextActivity)
        showCustomToast("로그인 인증 성공!")
        startActivity(intent)
        finish()
    }

    override fun onSignInFailure(message: String){
        Log.e("api error",message)
    }
    override fun onBackPressed() {
        showNotBackToast()
    }

    override fun onCertificationsSuccess(response: CertificationResponse) {
        showCustomToast(response.interimMessage)
        authTimer=getAuthNumberTimerEvent(binding.certificateAuthTimer)
        showCertificationEditText()
    }

    override fun onCertificationsFailure(message: String) {
        Log.e("api error",message)
    }
}