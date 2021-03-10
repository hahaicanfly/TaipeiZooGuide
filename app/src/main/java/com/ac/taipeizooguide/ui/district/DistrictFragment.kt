package com.ac.taipeizooguide.ui.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac.taipeizooguide.databinding.FragmentDistricBinding
import com.ac.taipeizooguide.model.DistrictResult
import com.ac.taipeizooguide.network.Response
import com.ac.taipeizooguide.network.State
import com.ac.taipeizooguide.ui.adapter.DistrictListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created on 2021/3/8.
 */
class DistrictFragment : Fragment() {

    private var _binding: FragmentDistricBinding? = null
    private val binding get() = _binding!!
    private val districtViewModel: DistrictViewModel by viewModel()

    private val observer = Observer<Response<DistrictResult>> {
        when (it.state) {
            State.SUCCESS -> {
                updateDistrictList(it.data!!)
            }
            State.ERROR -> {
            }
            State.LOADING -> {
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDistricBinding.inflate(inflater, container, false)
        districtViewModel.districtList.observe(viewLifecycleOwner, observer)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDistrictList.layoutManager = LinearLayoutManager(context)
    }

    private fun updateDistrictList(districtResult: DistrictResult) {
        binding.rvDistrictList.apply {
            adapter = DistrictListAdapter(districtResult.districtList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}