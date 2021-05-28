package com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SeekMapFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RulerSeekBar
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.SeekMapView

class SeekMapFragment : BaseFragment<SeekMapFragmentBinding>(SeekMapFragmentBinding::bind, R.layout.seek_map_fragment) {
    private var currentPercent =0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setSeekBarEvent(binding.myTownSetRangeProgressBar,binding.myTownVisualImageView)
        return binding.root
    }
    private fun setSeekBarEvent(seekBar: RulerSeekBar,seekMapView: SeekMapView)
    {
        seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentPercent=progress
                seekMapView.setMapFilter(currentPercent)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val value= currentPercent
                Log.d("test1",value.toString())
                when(value)
                {
                    in 0..16->{seekBar!!.progress=0}
                    in 16..49->{seekBar!!.progress=33}
                    in 50..83->{seekBar!!.progress=66}
                    in 84..100->{seekBar!!.progress=100}
                }
                currentPercent = seekBar!!.progress
            }

        })
    }
}