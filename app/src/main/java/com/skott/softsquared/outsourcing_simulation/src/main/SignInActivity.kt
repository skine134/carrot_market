package com.skott.softsquared.outsourcing_simulation.src.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.skott.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.skott.config.ApplicationClass.Companion.sSharedPreferences
import com.skott.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SignInLayoutBinding
import java.util.*
import kotlin.concurrent.fixedRateTimer

class SignInActivity : BaseActivity<SignInLayoutBinding>(SignInLayoutBinding::inflate) {
    private var preText = ""
    private var currentSelection = 0
    private lateinit var context: Context
    private lateinit var authTimer:Timer
    private var authTime = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        val phoneNumber =
            intent.getStringExtra(context.getString(R.string.sign_up_to_sign_in_intent_key))
                .toString()
        setPhoneNumber(binding.signInCellphoneEditText, phoneNumber)
        setBackButtonEvent(binding.backButton)
        setInputEvent(binding.signInCellphoneEditText,binding.signInTakeAuthNumberButton)
        authTimer = getAuthNumberTimerEvent(binding.signInAuthTimer)
        setTakeAuthNumberEvent(binding.signInTakeAuthNumberButton,binding.signInAuthTimer)
        setAcceptAndStartIntentEvent(binding.signInAcceptAndStartButton)
    }
    private fun setPhoneNumber(editText: EditText, phoneNumber: String) {
        editText.setText(phoneNumber)
    }
    private fun setBackButtonEvent(button: ImageButton) {
        button.setOnClickListener {
            showNotBackToast()
        }
    }
    private fun setInputEvent(inputField: EditText,button: Button) {
        inputField.setOnKeyListener(object: View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                val editText = v as EditText
                val text = editText.text.toString()
                val isMaxLength = text.replace(" ","").length>=11
                button.isEnabled = isMaxLength
                if(keyCode== KeyEvent.KEYCODE_BACK)
                    showNotBackToast()
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
                preText = text
                return false
            }
        })
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
    private fun getAuthNumberTimerEvent(textView: TextView): Timer
    {
        this.authTime = com.skott.config.authTime
        return fixedRateTimer(period=1000){
            --authTime

            val remainTime =  getRemainTime(authTime)
            runOnUiThread {

                if(authTime<=0)
                {
                    textView.text=""
                    return@runOnUiThread
                }
                textView.text = remainTime
            }

        }
    }
    private fun setTakeAuthNumberEvent(button: Button,textView: TextView)
    {
        button.setOnClickListener{
            //TODO: 서버로 인증번호 요청 및 전화번호 전송.
            this.authTimer.cancel()
            this.authTimer=getAuthNumberTimerEvent(textView)
        }

    }
    private fun setAcceptAndStartIntentEvent(button:Button)
    {
        button.setOnClickListener{
//            val nextActivity = if(sSharedPreferences.getString(X_ACCESS_TOKEN,"").equals("")) CreateProfileActivity::class.java else MainActivity::class.java
//            Log.d("nextActivity",if(sSharedPreferences.getString(X_ACCESS_TOKEN,"").equals("")) "mainActivity" else "createProfileActivity")
            val nextActivity = MainActivity::class.java
            val intent = Intent(this,nextActivity)
            startActivity(intent)
            finish()
        }
    }
    private fun getRemainTime(remainSeconds:Int) : String
    {
        return "${String.format("%02d",remainSeconds/60)}:${String.format("%02d",remainSeconds%60)}"
    }

    private fun showNotBackToast() {
        showCustomToast(context.getString(R.string.sign_up_not_back))
    }

    override fun onBackPressed() {
        showNotBackToast()
    }
}