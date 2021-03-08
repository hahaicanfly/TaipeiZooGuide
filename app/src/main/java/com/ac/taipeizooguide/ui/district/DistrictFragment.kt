package com.ac.taipeizooguide.ui.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ac.taipeizooguide.databinding.FragmentDistricBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created on 2021/3/8.
 */
class DistrictFragment : Fragment() {

    private var _binding: FragmentDistricBinding? = null
    private val binding get() = _binding!!

    private val districViewModel: DistricViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDistricBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        districViewModel.districName.observe(viewLifecycleOwner, {
            binding.tvDistric.text = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}