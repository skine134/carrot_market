package com.skott.softsquared.outsourcing_simulation.src.main.fragments

import android.content.Intent
import android.widget.EditText
import android.widget.ImageView
import com.skott.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProfileFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.CreateProfileActivity

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(ProfileFragmentBinding::bind, R.layout.profile_fragment),
    CreateProfileActivity.ProfileDataInterface {
    override fun profileImageListener(): ImageView {
        return binding.profileImageView.getImageView()
    }

    override fun profileEditTextListener(): EditText {
        return binding.profileNicknameEditText
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.profileImageView.imageSelectListener(data!!.data.toString())
        super.onActivityResult(requestCode, resultCode, data)
    }
}