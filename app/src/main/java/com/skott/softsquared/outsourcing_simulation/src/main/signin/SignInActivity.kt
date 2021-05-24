package com.skott.softsquared.outsourcing_simulation.src.main.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.skott.config.ApplicationClass
import com.skott.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SignInLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.main.CreateProfileActivity
import com.skott.softsquared.outsourcing_simulation.src.main.MainActivity
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signin.models.SigninResponse
import java.util.*
import kotlin.concurrent.fixedRateTimer

class SignInActivity : BaseActivity<SignInLayoutBinding>(SignInLayoutBinding::inflate),
    SigninActivityView {
    private var preText = ""
    private var currentSelection = 0
    private lateinit var context: Context
    private lateinit var authTimer: Timer
    private var authTime = 0
    private lateinit var certificationNumber: String
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var signInService:SigninService
    private lateinit var phoneNumber: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        phoneNumber =
            intent.getStringExtra(context.getString(R.string.sign_up_to_sign_in_phone_number_intent_key))
                .toString()
        certificationNumber =
            intent.getStringExtra(context.getString(R.string.sign_up_to_sign_in_certification_intent_key))
                .toString()
        //TODO test 아닐 때 지워야함.
        showCustomToast(certificationNumber)
        ApplicationClass.sSharedPreferences =
            applicationContext.getSharedPreferences(
                context.getString(R.string.default_key),
                MODE_PRIVATE
            )
        editor = ApplicationClass.sSharedPreferences.edit()
        signInService = SigninService(this)
        setPhoneNumber(binding.signInCellphoneEditText, phoneNumber)
        setBackButtonEvent(binding.backButton)
        setInputEvent(binding.signInCellphoneEditText, binding.signInTakeAuthNumberButton)
        authTimer = getAuthNumberTimerEvent(binding.signInAuthTimer)
        setTakeAuthNumberEvent(binding.signInTakeAuthNumberButton, binding.signInAuthTimer,binding.signInCellphoneEditText)
        setAcceptAndStartIntentEvent(
            binding.signInAcceptAndStartButton,
            binding.signInAuthNumberEditText,
            binding.signInWarningTextView
        )
    }

    private fun setPhoneNumber(editText: EditText, phoneNumber: String) {
        editText.setText(phoneNumber)
    }

    private fun setBackButtonEvent(button: ImageButton) {
        button.setOnClickListener {
            showNotBackToast()
        }
    }

    private fun setInputEvent(inputField: EditText, button: Button) {
        inputField.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                val editText = v as EditText
                val text = editText.text.toString()
                val isMaxLength = text.replace(" ", "").length >= 11
                button.isEnabled = isMaxLength
                if (keyCode == KeyEvent.KEYCODE_BACK)
                    showNotBackToast()
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    val keyBoard =
                        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputField.clearFocus() //에딧 텍스트 포커스를 제거
                    keyBoard.hideSoftInputFromWindow(inputField.windowToken, 0); //키보드 내려줌.
                }
                if (keyCode != KeyEvent.KEYCODE_DEL) {
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
                if (event.action == KeyEvent.ACTION_DOWN)
                    return false
                //editText selection은 1부터 시작
                currentSelection = editText.selectionStart
                editText.setText(filterPhoneNumber(editText.text.toString()))
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (editText.text.length == currentSelection - 1) {
                        if (currentSelection == 9 || currentSelection == 4)
                            --currentSelection
                    }
                }
                editText.setSelection(currentSelection)
                preText = text
                return false
            }
        })
    }

    private fun filterPhoneNumber(phoneNumber: String): String {

        val textArray = phoneNumber.replace(" ", "").toMutableList()
        when (textArray.count()) {
            4, 5, 6, 7
            -> {
                textArray.add(3, ' ')
                if (currentSelection == 4)
                    ++currentSelection
            }
            8, 9, 10, 11
            -> {
                textArray.add(3, ' ')
                textArray.add(8, ' ')
                if (currentSelection == 9)
                    ++currentSelection
            }
        }
        return textArray.joinToString("")
    }

    private fun getAuthNumberTimerEvent(textView: TextView): Timer {
        this.authTime = com.skott.config.authTime
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

    private fun setTakeAuthNumberEvent(button: Button, timerTextView: TextView,phoneNumberEditText: EditText) {
        button.setOnClickListener {
            this.authTimer.cancel()
            this.authTimer = getAuthNumberTimerEvent(timerTextView)
            phoneNumberEditText.isEnabled=false
        }

    }

    private fun setAcceptAndStartIntentEvent(
        button: Button,
        editText: EditText,
        textView: TextView
    ) {

        button.setOnClickListener {

            if (certificationNumber.equals(editText.text.toString())) {
                val jwt = ApplicationClass.sSharedPreferences.getString(context.getString(R.string.jwt_key),"")
                if(jwt.equals(""))
                {

                    val intent = Intent(this,CreateProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                    signInService.tryGetJwt(SigninRequest(phoneNumber.replace(" ", "")))
            }
            else {
                textView.text = context.getString(R.string.sign_in_incorrect_auth_number)
                textView.setTextColor(context.getColor(R.color.sign_in_incorrect))
            }
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

    private fun showNotBackToast() {
        showCustomToast(context.getString(R.string.sign_up_not_back))
    }

    override fun onBackPressed() {
        showNotBackToast()
    }

    override fun jwtListener(signinResponse: SigninResponse) {
        editor.putString(context.getString(R.string.jwt_key), signinResponse.jwt)
        editor.apply()
        val nextActivity = MainActivity::class.java
        val intent = Intent(this,nextActivity)
        startActivity(intent)
        finish()
    }

    override fun jwtErrorListener(messae: String) {
        showCustomToast(messae)
    }
}