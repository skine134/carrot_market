package com.skott.softsquared.outsourcing_simulation.src.main.seek_town

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.SeekTownFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.config.TOWN_SCOPE
import com.skott.softsquared.outsourcing_simulation.src.main.seek_town.model.NearVillage
import com.skott.softsquared.outsourcing_simulation.src.main.seek_town.model.TownResponse
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RulerSeekBar
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.SeekMapView

class SeekTownFragment : BaseFragment<SeekTownFragmentBinding>(
    SeekTownFragmentBinding::bind,
    R.layout.seek_town_fragment
), SeekTownView {
    private var currentPercent = 0
    private lateinit var townResponse: TownResponse
    private lateinit var currentScopeInfo: NearVillage
    private lateinit var seekTownService: SeekTownService
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setSeekBarEvent(binding.myTownSetRangeProgressBar, binding.myTownVisualImageView)
        seekTownService=SeekTownService(this)
        seekTownService.tryGetTowns()
        return binding.root
    }

    //TODO 동네 인증이 안되면 못 움직이게 막아야함.
    private fun setSeekBarEvent(seekBar: RulerSeekBar, seekMapView: SeekMapView) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentPercent = progress
                seekMapView.setMapFilter(currentPercent)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val value = currentPercent
                var scope: TOWN_SCOPE = TOWN_SCOPE.CLOSEST
                Log.d("test1", value.toString())
                when (value) {
                    in 0..16 -> {
                        scope = TOWN_SCOPE.CLOSEST
                    }
                    in 16..49 -> {
                        scope = TOWN_SCOPE.CLOSE
                    }
                    in 50..83 -> {
                        scope = TOWN_SCOPE.FAR
                    }
                    in 84..100 -> {
                        scope = TOWN_SCOPE.FARTHEST
                    }
                }
                seekBar!!.progress = scope.seekVale()
                currentPercent = seekBar.progress
                if(::townResponse.isInitialized)
                {
                    requireActivity().intent.putExtra(
                        requireContext().getString(R.string.seekmap_fragment_to_activity_intent_key),
                        scope.index()
                    )
                    currentScopeInfo = townResponse.nearVillages[scope.index()]
                    binding.seekTownNearByTownTextView.text =
                        binding.seekTownNearByTownTextView.text.toString()
                            .replace("town", townResponse.dong).replace(
                                "count",
                                currentScopeInfo.toString()
                            )
                }
                else{
                    showCustomToast("동네 인증을 해야 설정이 가능합니다.")
                }
            }

        })
    }

    override fun onGetTownsSuccess(townResponse: TownResponse) {
        this.townResponse = townResponse
    }

    override fun onGetTownsFailure(message: String) {
        Log.e("api error", message)
    }
}