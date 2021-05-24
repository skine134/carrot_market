package com.skott.softsquared.outsourcing_simulation.src.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.skott.androidUtil.CustomViews.ProfileImageView
import com.skott.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProfileFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.main.createprofile.CreateProfileActivity

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(ProfileFragmentBinding::bind, R.layout.profile_fragment),
    CreateProfileActivity.ProfileDataInterface {
    override fun profileImageListener(): ImageView {
        return binding.profileImageView.getImageView()
    }

    override fun profileEditTextListener(): EditText {
        return binding.profileNicknameEditText
    }
}