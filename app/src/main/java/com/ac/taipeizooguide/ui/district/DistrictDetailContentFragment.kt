package com.ac.taipeizooguide.ui.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ac.taipeizooguide.databinding.FragmentDistrictDetailContentBinding

/**
 * Created on 2021/3/15.
 */
class DistrictDetailContentFragment : Fragment() {
    private var _binding: FragmentDistrictDetailContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDistrictDetailContentBinding.inflate(inflater, container, false)
        return binding.root
    }


}