package com.skott.softsquared.outsourcing_simulation.src.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.media.Image
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.skott.config.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SignInLayoutBinding
import com.skott.softsquared.outsourcing_simulation.databinding.SignUpLayoutBinding


class SignupActivity : BaseActivity<SignUpLayoutBinding>(SignUpLayoutBinding::inflate) {
    private var preText = ""
    private var currentSelection = 0
    private lateinit var context : Context
    private lateinit var numberRegex: Regex
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        numberRegex = this.getString(R.string.number_regex).toRegex()
        context = this
        setBackButtonEvent(binding.backButton)
        setInputEvent(binding.signUpCellphoneEditText,binding.signUpTakeAuthNumberButton)
        setFindIdForEmailIntentEvent(binding.signUpFindIdForEmailTextView)
        setSignInIntentEvent(binding.signUpTakeAuthNumberButton)
    }
    private fun setBackButtonEvent(button:ImageButton)
    {
        button.setOnClickListener{
            showNotBackToast()
        }
    }
    private fun setInputEvent(inputField: EditText,button: Button) {
        inputField.setOnKeyListener(object:View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                val editText = v as EditText
                val text = editText.text.toString()
                val isMaxLength = text.replace(" ","").length>=11
                button.isEnabled = isMaxLength
                if(keyCode==KeyEvent.KEYCODE_BACK)
                    showNotBackToast()
                if(keyCode==KeyEvent.KEYCODE_ENTER)
                {
                    val keyBoard = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputField.clearFocus() //에딧 텍스트 포커스를 제거
                    keyBoard.hideSoftInputFromWindow(inputField.windowToken,0); //키보드 내려줌.
                }
                if(keyCode!=KeyEvent.KEYCODE_DEL){
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
                if(keyCode==KeyEvent.KEYCODE_DEL)
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
    private fun setFindIdForEmailIntentEvent(textView: TextView)
    {
        val content = SpannableString(textView.text.toString())
        content.setSpan(UnderlineSpan(), 0, textView.text.toString().length, 0)
        textView.text = content
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setOnClickListener{
            val intent = Intent(this,FindIdForEmailActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setSignInIntentEvent(button:Button)
    {
        button.setOnClickListener{
            val intent = Intent(this,SignInActivity::class.java)
            //TODO: 서버로 핸드폰 번호 전송 및 auth number 발급 요청.
            startActivity(intent)
            finish()
        }
    }
    private fun filterPhoneNumber(phoneNumber:String):String
    {

        val textArray = phoneNumber.replace(" ", "").toMutableList()
        //전화번호 최대길이 이상 못받게, 숫자만 받게
        if(textArray.count()>=11)
            return phoneNumber
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
        showCustomToast(context.getString(R.string.sign_up_not_back))
    }

    override fun onBackPressed() {
        showNotBackToast()
    }
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val focusView = getCurrentFocus()
        return super.dispatchTouchEvent(ev)
    }
}