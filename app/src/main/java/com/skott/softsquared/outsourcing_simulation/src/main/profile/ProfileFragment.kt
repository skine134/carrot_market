package com.skott.softsquared.outsourcing_simulation.src.main.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProfileFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.main.create_profile.CreateProfileActivity
import com.skott.softsquared.outsourcing_simulation.src.util.custom_views.ProfileImageView

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(ProfileFragmentBinding::bind, R.layout.profile_fragment),
    CreateProfileActivity.ProfileDataInterface {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setUpdateImageButton(binding.profileImageView.getCameraButton())
        return binding.root
    }

    private fun setUpdateImageButton(button: ImageButton)
    {
        button.setOnClickListener{
//            Toast.makeText(context,"test",Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*")
            (context as Activity).startActivityForResult(intent,0)
        }
    }
    override fun profileImageViewListener(): ProfileImageView {
        return binding.profileImageView
    }
    override fun profileNicknameListener():EditText
    {
        return binding.profileNicknameEditText
    }
}