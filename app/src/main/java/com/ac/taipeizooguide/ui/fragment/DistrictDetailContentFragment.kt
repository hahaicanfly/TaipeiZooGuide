package com.ac.taipeizooguide.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ac.taipeizooguide.base.BaseFragment
import com.ac.taipeizooguide.databinding.FragmentDistrictDetailContentBinding
import com.ac.taipeizooguide.model.District
import com.ac.taipeizooguide.setMemo

/**
 * Created on 2021/3/15.
 */
class DistrictDetailContentFragment : BaseFragment() {

    companion object {
        private const val ARG_DISTRICT_DETAIL_CONTENT = "district_content"

        fun getInstance(district: District): DistrictDetailContentFragment {
            return DistrictDetailContentFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DISTRICT_DETAIL_CONTENT, district)
                }
            }
        }
    }

    private var _binding: FragmentDistrictDetailContentBinding? = null
    private val binding get() = _binding!!

    private val districtDetail: District by lazy {
        arguments?.get(ARG_DISTRICT_DETAIL_CONTENT) as District
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDistrictDetailContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI() {
        binding.apply {
            with(districtDetail) {
                tvDetailInfo.text = info
                tvDetailMemo.setMemo(memo.takeIf { it.isNotEmpty() })
                tvDetailCategory.text = category

                tvOpenWeb.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                }
            }
        }
    }

    override fun setupObserver() = Unit
}