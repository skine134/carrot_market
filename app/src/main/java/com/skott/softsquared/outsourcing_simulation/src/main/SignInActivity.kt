package com.skott.softsquared.outsourcing_simulation.src.main

import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.skott.config.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.databinding.SignUpLayoutBinding

class SignInActivity : BaseActivity<SignUpLayoutBinding>(SignUpLayoutBinding::inflate) {
    private lateinit var preText: String
    private var changeFlag = false
    private var currentSelection = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setInputEvent(binding.signInCellphoneEditText)
    }

    private fun setInputEvent(inputField: EditText) {
        inputField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                currentSelection = inputField.selectionStart
                preText = s.toString()
//                Log.d("currentText", preText)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (changeFlag) {
                    changeFlag = false
                    return
                }
                val tmp = s.toString()
                val textArray = tmp.replace(" ", "").toMutableList()
                //전화번호 최대길이 이상 못받게, 숫자만 받게
                if(textArray.count()>=11)
                    return
                when (textArray.count()) {
                    4, 5, 6, 7
                    -> {
                        textArray.add(3, ' ')
                    }
                    8, 9, 10, 11
                    -> {
                        textArray.add(3, ' ')
                        textArray.add(8, ' ')
                    }
                }
                changeFlag = true
                inputField.setText(
                    textArray.joinToString("")
                )
                Log.d("currentCursorLoc", "$currentSelection ")
                inputField.setSelection(inputField.text.length)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val focusView = getCurrentFocus()
        return super.dispatchTouchEvent(ev)
    }
}