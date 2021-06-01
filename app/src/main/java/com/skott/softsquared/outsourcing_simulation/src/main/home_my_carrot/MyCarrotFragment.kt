package com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.BuyListItemAdapterBinding
import com.skott.softsquared.outsourcing_simulation.databinding.MyCarrotFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.src.main.app_setting.AppSettingActivity
import com.skott.softsquared.outsourcing_simulation.src.main.buy_list.BuyListActivity
import com.skott.softsquared.outsourcing_simulation.src.main.favorite_list.FavoriteListActivity
import com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot.model.MyCarrotListItem
import com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot.model.MyCarrotResponse
import com.skott.softsquared.outsourcing_simulation.src.main.sell_list.SellListActivity
import com.skott.softsquared.outsourcing_simulation.src.util.lib.convertDpToPixel
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getRoundedAllCornerBitmap


class MyCarrotFragment : BaseFragment<MyCarrotFragmentBinding>(
    MyCarrotFragmentBinding::bind,
    R.layout.my_carrot_fragment
), MyCarrotView {
    private lateinit var mySettingAdapter: MyCarrotRecyclerAdapter
    private lateinit var townsPostInfoAdapter: MyCarrotRecyclerAdapter
    private lateinit var businessAdapter: MyCarrotRecyclerAdapter
    private lateinit var otherAdapter: MyCarrotRecyclerAdapter
    private lateinit var myCarrotService:MyCarrotService
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val mySettingList = ArrayList<MyCarrotListItem>()
        val townPostInfoList = ArrayList<MyCarrotListItem>()
        val businessList = ArrayList<MyCarrotListItem>()
        val otherList = ArrayList<MyCarrotListItem>()

        myCarrotService = MyCarrotService(this)
        myCarrotService.tryGetMyCarrot()
        setListItem(mySettingList, townPostInfoList, businessList, otherList)
        mySettingAdapter = MyCarrotRecyclerAdapter(requireContext(), mySettingList)
        townsPostInfoAdapter = MyCarrotRecyclerAdapter(requireContext(), townPostInfoList)
        businessAdapter = MyCarrotRecyclerAdapter(requireContext(), businessList)
        otherAdapter = MyCarrotRecyclerAdapter(requireContext(), otherList)
        binding.mySettingList.layoutManager = LinearLayoutManager(requireContext())
        binding.townPostInfoList.layoutManager = LinearLayoutManager(requireContext())
        binding.businessList.layoutManager = LinearLayoutManager(requireContext())
        binding.otherList.layoutManager = LinearLayoutManager(requireContext())
        binding.mySettingList.adapter = mySettingAdapter
        binding.townPostInfoList.adapter = townsPostInfoAdapter
        binding.businessList.adapter = businessAdapter
        binding.otherList.adapter = otherAdapter
        setFavoriteIntentEvent(binding.myCarrotUserFavoriteListLayout)
        setSellIntentEvent(binding.myCarrotUserSellInfoLayout)
        setBuyIntentEvent(binding.myCarrotUserBuyInfoLayout)
        setSettingIntentEvent(binding.setting)
        return binding.root
    }
    private fun setFavoriteIntentEvent(viewGroup: ViewGroup)
    {
        viewGroup.setOnClickListener{
            val intent= Intent(requireContext(),FavoriteListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setBuyIntentEvent(viewGroup: ViewGroup)
    {
        viewGroup.setOnClickListener{
            val intent= Intent(requireContext(),BuyListActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setSellIntentEvent(viewGroup: ViewGroup)
    {
        viewGroup.setOnClickListener{
            val intent= Intent(requireContext(),SellListActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setSettingIntentEvent(imageButton: ImageButton)
    {
        imageButton.setOnClickListener{
            val intent =  Intent(requireContext(),AppSettingActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setListItem(
        mySettingList: ArrayList<MyCarrotListItem>,
        townPostInfoList: ArrayList<MyCarrotListItem>,
        businessList: ArrayList<MyCarrotListItem>,
        otherList: ArrayList<MyCarrotListItem>
    ) {
        // mySettingList
        mySettingList.add(
            MyCarrotListItem(
                R.drawable.profile_gps,
                requireContext().getString(R.string.my_carrot_my_town_setting)
            )
        )
        mySettingList.add(
            MyCarrotListItem(
                R.drawable.profile_auth_town,
                requireContext().getString(R.string.my_carrot_my_town_auth)
            )
        )
        mySettingList.add(
            MyCarrotListItem(
                R.drawable.proflie_alert_keyword,
                requireContext().getString(R.string.my_carrot_my_keyward_alert)
            )
        )
        mySettingList.add(
            MyCarrotListItem(
                R.drawable.profile_set_see,
                requireContext().getString(R.string.my_carrot_see_profile)
            )
        )

        // townPostInfoList

        townPostInfoList.add(
            MyCarrotListItem(
                R.drawable.town_posts,
                requireContext().getString(R.string.my_carrot_my_town_posts)
            )
        )
        townPostInfoList.add(
            MyCarrotListItem(
                R.drawable.town_post_coments,
                requireContext().getString(R.string.my_carrot_my_town_post_comments)
            )
        )
        townPostInfoList.add(
            MyCarrotListItem(
                R.drawable.town_post_titles,
                requireContext().getString(R.string.my_carrot_my_town_post_title_list)
            )
        )

        // businessList

        businessList.add(
            MyCarrotListItem(
                R.drawable.make_bis_profile,
                requireContext().getString(R.string.my_carrot_make_bis_profile)
            )
        )
        businessList.add(
            MyCarrotListItem(
                R.drawable.region_advertise,
                requireContext().getString(R.string.my_carrot_region_advertisement)
            )
        )

        // otherList

        otherList.add(
            MyCarrotListItem(
                R.drawable.invite_freind,
                requireContext().getString(R.string.my_carrot_invite_friend)
            )
        )
        otherList.add(
            MyCarrotListItem(
                R.drawable.share_carrot_market,
                requireContext().getString(R.string.my_carrot_share_carrot_market)
            )
        )
        otherList.add(
            MyCarrotListItem(
                R.drawable.carrot_market_notice,
                requireContext().getString(R.string.my_carrot_carrot_market_notice)
            )
        )
        otherList.add(
            MyCarrotListItem(
                R.drawable.help_center,
                requireContext().getString(R.string.my_carrot_help_center)
            )
        )
        otherList.add(
            MyCarrotListItem(
                R.drawable.app_setting,
                requireContext().getString(R.string.my_carrot_app_setting)
            )
        )

    }
    override fun onGetMyCarrotSuccess(response: MyCarrotResponse) {
        var dong = response.dong.toString()
        binding.myCarrotNickname.text = response.nickName
        if(dong.equals("null")) {
            showCustomToast("동네 없음")
            dong=""
        }
        binding.myCarrotUserInfo.text =
            requireContext().getString(R.string.my_carrot_user_info)
                .replace("town", dong)
                .replace("code", String.format("%07d", response.userIdx))
        val imageUrl = response.profilePictureUrl.toString()
        if(imageUrl.equals("null")) {
            showCustomToast("이미지 없음")
            return
        }
        getRoundedAllCornerBitmap(requireContext(),imageUrl,0,binding.profileImageView.getImageView())
    }

    override fun onGetMyCarrotFailure(message: String) {
        Log.e("api error",message)
    }
}