package com.skott.softsquared.outsourcing_simulation.src.main.fragments

import android.widget.EditText
import android.widget.ImageView
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
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
}