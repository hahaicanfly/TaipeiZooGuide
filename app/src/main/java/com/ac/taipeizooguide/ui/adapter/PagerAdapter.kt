package com.ac.taipeizooguide.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ac.taipeizooguide.model.District
import com.ac.taipeizooguide.ui.fragment.DistrictDetailContentFragment
import com.ac.taipeizooguide.ui.fragment.PlantListFragment

/**
 * Created on 2021/3/15.
 */
class PagerAdapter(fragment: Fragment, district: District) : FragmentStateAdapter(fragment) {

    private val fragmentList = arrayListOf(
        DistrictDetailContentFragment.getInstance(district),
        PlantListFragment.getInstance(district)
    )

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}