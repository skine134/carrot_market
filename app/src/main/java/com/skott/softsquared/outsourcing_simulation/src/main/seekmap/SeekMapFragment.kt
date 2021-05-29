package com.skott.softsquared.outsourcing_simulation.src.main.seekmap

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SeekMapFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.config.TOWN_SCOPE
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
                var scope :TOWN_SCOPE = TOWN_SCOPE.CLOSEST
                Log.d("test1",value.toString())
                when(value)
                {
                    in 0..16->{scope=TOWN_SCOPE.CLOSEST}
                    in 16..49->{scope=TOWN_SCOPE.CLOSE}
                    in 50..83->{scope=TOWN_SCOPE.FAR}
                    in 84..100->{scope=TOWN_SCOPE.FARTHEST}
                }
                seekBar!!.progress=scope.seekVale()
                currentPercent = seekBar.progress
                requireActivity().intent.putExtra(requireContext().getString(R.string.seekmap_fragment_to_activity_intent_key),scope.index())
            }

        })
    }
}