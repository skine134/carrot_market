package com.skott.softsquared.outsourcing_simulation.src.main.seekmap

import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SeekMapLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity

class SeekMapActivity:BaseActivity<SeekMapLayoutBinding>(SeekMapLayoutBinding::inflate)
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackButtonEvent(binding.seekMapAppBar.getBackButton())
    }
    private lateinit var context: Context
    private fun setBackButtonEvent(button:ImageButton){
        button.setOnClickListener{
            savePostSeekCount()
            finish()
        }
    }
    override fun onBackPressed() {
        savePostSeekCount()
        super.onBackPressed()
    }
    private fun savePostSeekCount()
    {
        val scope = intent.getIntExtra(context.getString(R.string.seekmap_fragment_to_activity_intent_key),-1)
        if(scope==-1) {
            showCustomToast("Seek Map error")
            return
        }
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putString(context.getString(R.string.post_scope_key),scope.toString())
        editor.apply()
        //TODO api 적용 후 적용
        //TODO 가져온 주소 갯수만큼
//        intent.putExtra(context.getString(R.string.seekmap_activity_to_post_activity_intent_key),)
    }
}