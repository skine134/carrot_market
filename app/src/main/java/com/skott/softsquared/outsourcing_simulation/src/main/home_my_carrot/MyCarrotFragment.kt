package com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.skott.softsquared.outsourcing_simulation.src.config.BaseFragment
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.MyCarrotFragmentBinding
import com.skott.softsquared.outsourcing_simulation.src.main.home_my_carrot.model.MyCarrotListItem
import com.skott.softsquared.outsourcing_simulation.src.util.lib.getLinearLayoutManager

class MyCarrotFragment : BaseFragment<MyCarrotFragmentBinding>(
    MyCarrotFragmentBinding::bind,
    R.layout.my_carrot_fragment
) {
    private lateinit var mySettingAdapter: MyCarrotRecyclerAdapter
    private lateinit var townsPostInfoAdapter: MyCarrotRecyclerAdapter
    private lateinit var businessAdapter: MyCarrotRecyclerAdapter
    private lateinit var otherAdapter: MyCarrotRecyclerAdapter
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
        setListItem(mySettingList,townPostInfoList,businessList,otherList)
        mySettingAdapter = MyCarrotRecyclerAdapter(context!!, mySettingList)
        townsPostInfoAdapter = MyCarrotRecyclerAdapter(context!!, townPostInfoList)
        businessAdapter = MyCarrotRecyclerAdapter(context!!, businessList)
        otherAdapter = MyCarrotRecyclerAdapter(context!!, otherList)
        binding.mySettingList.layoutManager = LinearLayoutManager(context!!)
        binding.townPostInfoList.layoutManager = LinearLayoutManager(context!!)
        binding.businessList.layoutManager = LinearLayoutManager(context!!)
        binding.otherList.layoutManager = LinearLayoutManager(context!!)
        binding.mySettingList.adapter = mySettingAdapter
        binding.townPostInfoList.adapter = townsPostInfoAdapter
        binding.businessList.adapter = businessAdapter
        binding.otherList.adapter = otherAdapter
        return binding.root
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
                context!!.getString(R.string.my_carrot_my_town_setting)
            )
        )
        mySettingList.add(
            MyCarrotListItem(
                R.drawable.profile_auth_town,
                context!!.getString(R.string.my_carrot_my_town_auth)
            )
        )
        mySettingList.add(
            MyCarrotListItem(
                R.drawable.proflie_alert_keyword,
                context!!.getString(R.string.my_carrot_my_keyward_alert)
            )
        )
        mySettingList.add(
            MyCarrotListItem(
                R.drawable.profile_set_see,
                context!!.getString(R.string.my_carrot_see_profile)
            )
        )

        // townPostInfoList

        townPostInfoList.add(
            MyCarrotListItem(
                R.drawable.town_posts,
                context!!.getString(R.string.my_carrot_my_town_posts)
            )
        )
        townPostInfoList.add(
            MyCarrotListItem(
                R.drawable.town_post_coments,
                context!!.getString(R.string.my_carrot_my_town_post_comments)
            )
        )
        townPostInfoList.add(
            MyCarrotListItem(
                R.drawable.town_post_titles,
                context!!.getString(R.string.my_carrot_my_town_post_title_list)
            )
        )

        // businessList

        businessList.add(
            MyCarrotListItem(
                R.drawable.make_bis_profile,
                context!!.getString(R.string.my_carrot_make_bis_profile)
            )
        )
        businessList.add(
            MyCarrotListItem(
                R.drawable.region_advertise,
                context!!.getString(R.string.my_carrot_region_advertisement)
            )
        )

        // otherList

        otherList.add(
            MyCarrotListItem(
                R.drawable.invite_freind,
                context!!.getString(R.string.my_carrot_invite_friend)
            )
        )
        otherList.add(
            MyCarrotListItem(
                R.drawable.share_carrot_market,
                context!!.getString(R.string.my_carrot_share_carrot_market)
            )
        )
        otherList.add(
            MyCarrotListItem(
                R.drawable.carrot_market_notice,
                context!!.getString(R.string.my_carrot_carrot_market_notice)
            )
        )
        otherList.add(
            MyCarrotListItem(
                R.drawable.help_center,
                context!!.getString(R.string.my_carrot_help_center)
            )
        )
        otherList.add(
            MyCarrotListItem(
                R.drawable.app_setting,
                context!!.getString(R.string.my_carrot_app_setting)
            )
        )

    }
}