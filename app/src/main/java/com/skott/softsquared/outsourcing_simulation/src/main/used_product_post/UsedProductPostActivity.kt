package com.skott.softsquared.outsourcing_simulation.src.main.used_product_post

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.view.isGone
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.UsedProductPostingLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.model.UsedProductPostRequest
import com.skott.softsquared.outsourcing_simulation.src.main.seekmap.SeekMapActivity
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getListDialog
import com.skott.softsquared.outsourcing_simulation.src.main.gallery_picker.model.Picture
import com.skott.softsquared.outsourcing_simulation.src.util.lib.dispatchKeyboardEvent
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import gun0912.tedkeyboardobserver.TedRxKeyboardObserver

enum class UsedProductCategory(val value: String) {
    DIGITAL("디지털/가전"),
    INTERIOR("가구/인테리어"),
    CHILD("유아동/유아도서"),
    LIVING("생활/가공식품"),
    SPORTS("스포츠/레저"),
    GIRLS_ACCESORY("여성잡화"),
    GIRLS_CLOTH("여성의류"),
    MANS("남성패션/잡화"),
    HOBBY("게임/취미"),
    BEAUTY("뷰티/미용"),
    PETS("반려동물용품"),
    BOOKS("도서/티켓/음반"),
    PLANTS("식물"),
    OTHERS("기타 중고물품"),
    WANTED("삽니다"),
}

class UsedProductPostActivity :
    BaseActivity<UsedProductPostingLayoutBinding>(UsedProductPostingLayoutBinding::inflate),UsedProductPostView {
    private lateinit var context: Context
    private var category: String = ""
    private lateinit var categoryDialog: AlertDialog
    private lateinit var defineDialog: AlertDialog
    private var scope:Int = 1
    private val pictures = ArrayList<Picture>()
    private var uriList = ArrayList<String>()
    private lateinit var usedProductPostService:UsedProductPostService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        usedProductPostService = UsedProductPostService(this)
        scope = ApplicationClass.sSharedPreferences.getInt(context.getString(R.string.post_scope_key),1)
        setTitleEvent(binding.usedProductPostingTitleEditText)
        showCategoryDialogEvent(binding.usedProductPostingCategoryLayout)
        showSeekMapEvent(binding.usedProductPostingSelectSeekMapLayout)
        inputPriceEvent(binding.usedProductPostingPriceEditText)
        TedKeyboardObserver(this)
            .listen {
                binding.usedProductImageSelectorView.isGone=it}
        submitPostEvent(binding.usedProductPostingFinishButton)
        setBackButtonEvent(binding.usedProductPostingAppBar.getBackButton())
    }

    private fun setTitleEvent(editText: EditText) {
        editText.setOnKeyListener { view: View, keyCode: Int, keyEvent: KeyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                val keyBoard =
                    context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                editText.clearFocus() //에딧 텍스트 포커스를 제거
                keyBoard.hideSoftInputFromWindow(editText.windowToken, 0); //키보드 내려줌.

            }
            if (editText.text.toString().length < 3)
                //TODO 관련 카테고리 조회 기능
                return@setOnKeyListener false

//            showRelateCategories(editText)
            return@setOnKeyListener false
        }

    }

    private fun showCategoryDialogEvent(layout: ViewGroup) {
        layout.setOnClickListener {
            val array = arrayListOf<String>()
            for (item in UsedProductCategory.values()) array.add(item.value)
            categoryDialog =
                getListDialog(context, array) { dialogInterface: DialogInterface, position: Int ->
                    category = array[position]
                    binding.usedProductPostingCategoryTextView.text=category
                }
            categoryDialog.listView.defaultFocusHighlightEnabled=false
            categoryDialog.show()
        }
    }

    private fun inputPriceEvent(editText: EditText) {
        editText.setOnKeyListener { v, keyCode, event ->
            val hideColor = context.getColor(R.color.hint_color)
            val showColor = context.getColor(R.color.black)
            val isEmpty = editText.text.toString().isEmpty()
            binding.usedProductPostingWonSignTextView.setTextColor(if (!isEmpty) showColor else hideColor)
            binding.isDealTextView.setTextColor(if (!isEmpty) showColor else hideColor)
            binding.isDealCheckbox.isEnabled = !isEmpty

            return@setOnKeyListener false
        }

    }

    private fun showSeekMapEvent(layout: ViewGroup) {
        layout.setOnClickListener {
            val intent=Intent(this,SeekMapActivity::class.java)
            startActivityForResult(intent, RESULT_OK)
        }
    }

    private fun showDefineStringDialogEvent() {

    }

    private fun getGalleryImageEvent() {

    }

    private fun submitPostEvent(button: Button) {
        button.setOnClickListener {
            if (binding.usedProductPostingTitleEditText.text.toString()
                    .equals("") || category.equals("") || binding.usedProductPostingContentEditText.text.toString()
                    .equals("")
            ) {
                showCustomToast(context.getString(R.string.used_product_posting_please_input_primary))
                return@setOnClickListener
            }
            val price = if(binding.usedProductPostingPriceEditText.text.toString().equals("")) 0 else binding.usedProductPostingPriceEditText.text.toString().toInt()
            val village = if(ApplicationClass.userTownInfoArray.size<=0) 1 else ApplicationClass.userTownInfoArray[0].villageIdx
            val usedProductPost = UsedProductPostRequest(
                title = binding.usedProductPostingTitleEditText.text.toString(),
                category = category,
                price = price,
                isNegotiable = if (binding.isDealCheckbox.isChecked) "YES" else "NO",
                content = binding.usedProductPostingContentEditText.text.toString(),
                //TODO:0이 아닌 현재 동네 값으로
                villageIdx = village,
                rangeLevel = scope,
                //TODO: gallery에서 가져온 값으로
                pictures = pictures,
            )
            usedProductPostService.tryPostItemUpload(usedProductPost)
        }
    }

    private fun showRelateCategories() {
    }

    private fun setBackButtonEvent(button: ImageButton) {
        button.setOnClickListener {
            showCustomToast(context.getString(R.string.used_product_posting_temp_save))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode!= RESULT_OK) {
            Log.d("seek_result_fail","정상적으로 완료하지 못했습니다.")
            return
        }
        //TODO api 적용 후 적용
//        scope = ApplicationClass.sSharedPreferences.getString(context.getString(R.string.post_scope_key),"")!!.toInt()
//        //TODO 0이 아닌 현재 선태된 동네로 선택
//        if(ApplicationClass.userTownInfoArray.size>0&&ApplicationClass.userTownInfoArray[0].dong.isEmpty())
//        binding.usedProductPostingSelectScopeTextView.text = context.getString(R.string.used_product_posting_selected_range).replace(
//            "town",ApplicationClass.userTownInfoArray[0].dong).replace("count",scope.toString())
    }

    override fun onPostItemUploadSuccess() {
        finish()
    }

    override fun onPostItemUploadFailure(message:String) {
        Log.e("api error",message)
        finish()
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        dispatchKeyboardEvent(context,ev!!)
        return super.dispatchTouchEvent(ev)
    }
}