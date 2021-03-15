package com.ac.taipeizooguide.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ac.taipeizooguide.R
import com.ac.taipeizooguide.base.BaseFragment
import com.ac.taipeizooguide.databinding.FragmentDistricDetailBinding
import com.ac.taipeizooguide.loadUrl
import com.ac.taipeizooguide.model.District
import com.ac.taipeizooguide.setActionBar
import com.ac.taipeizooguide.ui.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Created on 2021/3/8.
 */
class DistrictDetailFragment : BaseFragment() {

    private var _binding: FragmentDistricDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var pagerAdapter: PagerAdapter

    private val districtDetail: District by lazy {
        arguments?.get("dist") as District
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDistricDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI() {
        setActionBar(true, districtDetail.districtName)
        pagerAdapter = PagerAdapter(this, districtDetail)
        val tabTitle = arrayListOf(
            getString(R.string.tab_title_district),
            getString(R.string.tab_title_plants)
        )

        binding.apply {

            with(districtDetail) {
                imgDistrictDetail.loadUrl(picUrl)
            }

            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = tabTitle[position]
            }.attach()
        }
    }

    override fun setupObserver() = Unit

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}