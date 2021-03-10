package com.ac.taipeizooguide.ui.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ac.taipeizooguide.databinding.FragmentDistricDetailBinding
import com.ac.taipeizooguide.setActionBar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created on 2021/3/8.
 */
class DistrictDetailFragment : Fragment() {

    private var _binding: FragmentDistricDetailBinding? = null
    private val binding get() = _binding!!
    private val districtViewModel: DistrictViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDistricDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}