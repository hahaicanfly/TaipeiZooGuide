package com.ac.taipeizooguide.ui.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ac.taipeizooguide.databinding.FragmentDistricDetailBinding
import com.ac.taipeizooguide.loadUrl
import com.ac.taipeizooguide.model.District
import com.ac.taipeizooguide.setActionBar
import com.ac.taipeizooguide.setMemo
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created on 2021/3/8.
 */
class DistrictDetailFragment : Fragment() {

    private var _binding: FragmentDistricDetailBinding? = null
    private val binding get() = _binding!!
    private val districtViewModel: DistrictViewModel by viewModel()

    private lateinit var districtDetail: District

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar(true)
        districtDetail = arguments?.get("dist") as District
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDistricDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            with(districtDetail) {
                imgDistrictDetail.loadUrl(picUrl)
                tvDetailInfo.text = info
                tvDetailMemo.setMemo(memo.takeIf { it.isNotEmpty() })
                tvDetailCategory.text = category
            }

        }

    }
}