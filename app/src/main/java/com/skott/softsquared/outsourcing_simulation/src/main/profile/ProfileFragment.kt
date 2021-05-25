package com.skott.softsquared.outsourcing_simulation.src.main.profile

import android.widget.EditText
import android.widget.ImageView
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProfileFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.CreateProfileActivity
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.ProfileImageView

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(ProfileFragmentBinding::bind, R.layout.profile_fragment),
    CreateProfileActivity.ProfileDataInterface {
    override fun profileImageViewListener(): ProfileImageView {
        return binding.profileImageView
    }
    override fun profileNicknameListener():EditText
    {
        return binding.profileNicknameEditText
    }
}