package com.skott.softsquared.outsourcing_simulation.src.main.seek_map

import android.os.Bundle
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
        setSeekBarEvent(binding.myTownSetRangeProgressBar,binding.myTownVisualImageView)
        return super.onCreateView(inflater, container, savedInstanceState)
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
                val value= currentPercent/33
                //TODO 동작을 확실하게 모르므로 보류.
                seekBar!!.progress = value*33
            }

        })
    }
}