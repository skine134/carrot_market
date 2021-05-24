package com.skott.softsquared.outsourcing_simulation.src.main.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.skott.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SignUpLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.main.FindIdForEmailActivity
import com.skott.softsquared.outsourcing_simulation.src.main.signin.SignInActivity
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.CertificationsRequest
import com.skott.softsquared.outsourcing_simulation.src.main.signup.models.CertificationsResponse


class SignupActivity : BaseActivity<SignUpLayoutBinding>(SignUpLayoutBinding::inflate),SignupActivityView {
    private var preText = ""
    private var currentSelection = 0
    private lateinit var context : Context
    private lateinit var numberRegex: Regex
    private lateinit var nextIntent:Intent
    private lateinit var signupService: SignupService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        numberRegex = this.getString(R.string.number_regex).toRegex()
        context = this
        signupService = SignupService(this)
        setBackButtonEvent(binding.backButton)
        setInputEvent(binding.signUpCellphoneEditText,binding.signUpTakeAuthNumberButton)
        setFindIdForEmailIntentEvent(binding.signUpFindIdForEmailTextView)
        setSignInIntentEvent(binding.signUpTakeAuthNumberButton,binding.signUpCellphoneEditText)
    }

    private fun setBackButtonEvent(button: ImageButton)
    {
        button.setOnClickListener{
            showNotBackToast()
        }
    }
    private fun setInputEvent(inputField: EditText, button: Button) {
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
    private fun setSignInIntentEvent(button: Button, textView: TextView)
    {
        button.setOnClickListener{
            nextIntent = Intent(this, SignInActivity::class.java)
            nextIntent.putExtra(context.getString(R.string.sign_up_to_sign_in_phone_number_intent_key),textView.text.toString())
            signupService.tryGetCertifications(CertificationsRequest(textView.text.toString().replace(" ","")))
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
        showCustomToast(context.getString(R.string.sign_up_not_back))
    }

    override fun onBackPressed() {
        showNotBackToast()
    }

    override fun certificationsResponseListener(response: CertificationsResponse) {
        nextIntent.putExtra(context.getString(R.string.sign_up_to_sign_in_certification_intent_key),response.authNumber)
        startActivity(nextIntent)
        finish()
    }

    override fun certificationsResponseErrorListener(message: String) {
        showCustomToast(message)
    }
}