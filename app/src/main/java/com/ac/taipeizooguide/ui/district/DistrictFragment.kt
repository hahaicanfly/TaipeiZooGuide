package com.ac.taipeizooguide.ui.district

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ac.taipeizooguide.databinding.FragmentDistricBinding
import com.ac.taipeizooguide.model.DistrictResult
import com.ac.taipeizooguide.network.Response
import com.ac.taipeizooguide.network.State
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created on 2021/3/8.
 */
class DistrictFragment : Fragment() {

    private var _binding: FragmentDistricBinding? = null
    private val binding get() = _binding!!
    private val districViewModel: DistrictViewModel by viewModel()

    private val observer = Observer<Response<DistrictResult>> {
        when (it.state) {
            State.SUCCESS -> {
                updateDistrictList(it.data)
            }
            State.ERROR -> {
            }
            State.LOADING -> {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDistricBinding.inflate(inflater, container, false)
        districViewModel.districtList.observe(viewLifecycleOwner, observer)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateDistrictList(districtResult: DistrictResult?) {
        districtResult?.districtList?.forEach {
            Log.e("TAG", "districtName: ${it.districtName}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}