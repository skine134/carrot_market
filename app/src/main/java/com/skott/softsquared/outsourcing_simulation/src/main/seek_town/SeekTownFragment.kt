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
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.MyTownSettingResponse
import com.skott.softsquared.outsourcing_simulation.src.main.my_town_setting.model.NearVillage
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RulerSeekBar
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.SeekMapView

class SeekTownFragment : BaseFragment<SeekTownFragmentBinding>(
    SeekTownFragmentBinding::bind,
    R.layout.seek_town_fragment
) {
    private var currentPercent = 0
    private lateinit var townResponse: MyTownSettingResponse
    private lateinit var currentScopeInfo: NearVillage
    private var arrayList = ArrayList<MyTownSettingResponse>()
    private var range=-1
    private var position = -1
    private lateinit var event : ()->Unit
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setSeekBarEvent(binding.myTownSetRangeProgressBar, binding.myTownVisualImageView)
        event  = {}
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
                Log.d("test1", value.toString())
                val scope = getScope(value)
                seekBar!!.progress = scope.seekVale()
                currentPercent = seekBar.progress
                if (!arrayList.isEmpty()) {
                    requireActivity().intent.putExtra(
                        requireContext().getString(R.string.seekmap_fragment_to_activity_intent_key),
                        scope.index()
                    )
                    currentScopeInfo = arrayList[position].nearVillages[scope.index()]
                    binding.seekTownNearByTownTextView.text =
                        requireContext().getString(R.string.seek_town_near_by_town)
                            .replace("town", townResponse.dong).replace(
                                "count",
                                currentScopeInfo.toString()
                            )
                    binding.seekTownNearByTownTextView.text =
                        requireContext().getString(R.string.seek_town_near_by_town)
                            .replace("town", townResponse.dong)
                            .replace("count", arrayList[position].nearVillages[scope.index()].dongs.size.toString())
                    range=scope.index()
                    event()
                } else {
                    showCustomToast("동네 인증을 해야 설정이 가능합니다.")
                }
            }

        })
    }
    fun getScope(value:Int): TOWN_SCOPE {

        var scope: TOWN_SCOPE = TOWN_SCOPE.CLOSEST
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
        return scope
    }
    fun changeSeekTownEvent(arrayList: ArrayList<MyTownSettingResponse>, position: Int) {
        this.arrayList = arrayList
        this.position = position
        binding.myTownSetRangeProgressBar.setProgress(arrayList[position].rangeLevel * 33)
        binding.seekTownNearByTownTextView.text =
            requireContext().getString(R.string.seek_town_near_by_town)
                .replace("town", arrayList[position].dong)
                .replace("count", arrayList[position].nearVillages[arrayList[position].rangeLevel].dongs.size.toString())
        this.townResponse = arrayList[position]
    }
    fun getCurrentTownRange(): Int {
        return range
    }
    fun seekMapUdpateEvent(event:()->Unit)
    {
        this.event = event
    }
}