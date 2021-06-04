package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.FindTownNearByTownAdapterBinding
import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.main.certification.CertificationActivity
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.CreateProfileActivity
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.FindMyTownResponse
import com.skott.softsquared.outsourcing_simulation.src.main.find_town.model.RegisterAddressRequest
import com.skott.softsquared.outsourcing_simulation.src.util.adapters.BaseRecyclerMessageViewAdapter
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.RecyclerMessageView

class FindMyTownAdapter(context: Context,arrayList: ArrayList<FindMyTownResponse>,val service: FindMyTownService,recyclerMessageView: RecyclerMessageView):BaseRecyclerMessageViewAdapter<FindMyTownResponse,FindMyTownViewHolder>(context,arrayList,recyclerMessageView) {
    private val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding: FindTownNearByTownAdapterBinding
    private var clickPosition=-1
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FindMyTownViewHolder {
        binding = FindTownNearByTownAdapterBinding.inflate(inflater,parent,false)
        binding.root.setOnClickListener {
            clickPosition = arrayList[position].idx
            if((context as FindTownActivity).getIntentVale()==-200)
            {
                    ApplicationClass.sSharedPreferences.edit().putString(context.getString(R.string.location_key),arrayList[position].toString()).apply()
                    //초기 동네 등록, 인텐트 값은 동네 번호
                    val intent= Intent(context, CertificationActivity::class.java)
                    intent.putExtra(context.getString(R.string.create_profile_intent_key),clickPosition)
                    context.startActivity(intent)
                    context.finish()
            }
            service.tryPostRegisterAddress(RegisterAddressRequest(clickPosition))
        }
        return FindMyTownViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FindMyTownViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    fun getClickPosition(): Int {
        return clickPosition
    }
}