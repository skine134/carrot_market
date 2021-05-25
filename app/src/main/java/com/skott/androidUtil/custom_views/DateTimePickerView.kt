package com.skott.androidUtil.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.NumberPicker
import androidx.constraintlayout.widget.ConstraintLayout
import com.skott.softsquared.outsourcing_simulation.databinding.DateTimePickerLayoutBinding
import java.util.*

class DateTimePickerView(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    private lateinit var binding: DateTimePickerLayoutBinding
    val cal = Calendar.getInstance()
    private val year = cal.get(Calendar.YEAR)
    private val month = cal.get(Calendar.MONTH)+1
    private val date = cal.get(Calendar.DATE)
    private val hour = cal.get(Calendar.HOUR)
    private val minute = cal.get(Calendar.MINUTE)
    init {
        init()
    }

    fun init() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DateTimePickerLayoutBinding.inflate(inflater, this, false)

        setRanges()
        setValues(year,month,date,hour,minute)
    }

    fun setRange(picker: NumberPicker, maxTime: Int, minTime: Int = 0) {
        picker.minValue = minTime
        picker.maxValue = maxTime
    }

    fun setValue(picker: NumberPicker, value: Int) {
        picker.value = value
    }

    fun setRanges(
        maxYear: Int = 10,
        maxMonth: Int = 10,
        maxDate: Int = 10,
        maxHour: Int = 10,
        maxMinute: Int = 10,
        maxSecond: Int = 10,
        minYear: Int = 0,
        minMonth: Int = 0,
        minDate: Int = 0,
        minHour: Int = 0,
        minMinute: Int = 0,
        minSecond: Int = 0
    ) {
//        val cal = Calendar.getInstance()
//        val year = cal.get(Calendar.YEAR)
        setRange(binding.yearPicker, maxYear, minYear)
        setRange(binding.monthPicker, maxMonth, minMonth)
        setRange(binding.dayPicker, maxDate, minDate)
        setRange(binding.hourPicker, maxHour,minHour)
        setRange(binding.minutePicker, maxMinute,minMinute)
    }
    fun setValues(
        year:Int = 0,
        month: Int = 0,
        date: Int = 0,
        hour: Int = 0,
        minute: Int = 0,
        second: Int = 0)
    {
        setValue(binding.yearPicker, year)
        setValue(binding.monthPicker, month)
        setValue(binding.dayPicker, date)
        setValue(binding.hourPicker, hour)
        setValue(binding.minutePicker, minute)
    }
}