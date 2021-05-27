package com.skott.softsquared.outsourcing_simulation.src.main.find_town

import android.content.Intent
import com.skott.softsquared.outsourcing_simulation.databinding.FindTownByCurrentLocationBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity

class FindTownActivity :BaseActivity<FindTownByCurrentLocationBinding>(FindTownByCurrentLocationBinding::inflate){

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //TODO: 사용자가 선택한 주소 정보를 받아옴.
    }
}