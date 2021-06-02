package com.skott.softsquared.outsourcing_simulation.src.util.custom_views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.MyTownButtonViewBinding
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.FindTownActivity
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.MyTownSettingActivity

private const val RESULT_CODE = 200
class MyTownButtonView(context:Context,attrs:AttributeSet):ConstraintLayout(context,attrs) {
    private lateinit var binding: MyTownButtonViewBinding
    private var text=""
    private lateinit var myTownSetting: MyTownSettingActivity
    private var activeCount=0
    init{
        init()
        attrs.run {
            context.obtainStyledAttributes(this, R.styleable.MyTownButtonView)
        }.run {
            val isActive = getBoolean(R.styleable.MyTownButtonView_is_active,false)
            setActive(isActive)
        }
    }
    fun getText(): String {
        return text
    }
    fun init()
    {
        val inflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = MyTownButtonViewBinding.inflate(inflater,this,false)
        setDeleteEvent(binding.myTownActiveView,binding.myTownInactiveView)
        setChangeEvent(binding.myTownActiveView,binding.myTownInactiveView)
        setIntentFindTownEvent(binding.myTownEmptyView)
        addView(binding.root)
        myTownSetting = context as MyTownSettingActivity
    }
    fun setActive(isActive:Boolean)
    {
        binding.myTownInactiveView.isGone=isActive
        binding.myTownActiveView.isGone=!isActive
    }
    fun setDelete(isDelete:Boolean)
    {
        binding.myTownEmptyView.isGone=!isDelete
        binding.myTownInactiveView.isGone=isDelete
        binding.myTownActiveView.isGone=isDelete
    }
    fun setText(text:String)
    {
        this.text=text
        binding.myTownActiveView.setTown(this.text)
        binding.myTownInactiveView.setTown(this.text)
    }
    private fun setDeleteEvent(myTownButtonActiveView: MyTownButtonActiveView,myTownButtonInActiveView: MyTownButtonInActiveView)
    {
        myTownButtonActiveView.getBinding().myTownDeleteButton.setOnClickListener{
            myTownSetting.townDeleteEvent(this)
        }
        myTownButtonInActiveView.getBinding().myTownDeleteButton.setOnClickListener{
            myTownSetting.townDeleteEvent(this)
        }
    }
    private fun setIntentFindTownEvent(inActiveButton:ImageButton)
    {
        inActiveButton.setOnClickListener{
            val intent = Intent(context,FindTownActivity::class.java)
            (context as Activity).startActivityForResult(intent,RESULT_CODE)
        }
    }
    private fun setChangeEvent(myTownButtonActiveView: MyTownButtonActiveView,myTownButtonInActiveView: MyTownButtonInActiveView)
    {
        myTownButtonActiveView.setOnClickListener{
            myTownSetting.changeTownEvent(this)
        }
        myTownButtonInActiveView.setOnClickListener{
            myTownSetting.changeTownEvent(this)
        }
    }
}