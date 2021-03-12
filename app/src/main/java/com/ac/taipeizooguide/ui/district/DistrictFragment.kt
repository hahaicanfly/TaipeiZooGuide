package com.ac.taipeizooguide.ui.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac.taipeizooguide.R
import com.ac.taipeizooguide.addOnItemClickListener
import com.ac.taipeizooguide.databinding.FragmentDistricBinding
import com.ac.taipeizooguide.model.DistrictResult
import com.ac.taipeizooguide.network.Response
import com.ac.taipeizooguide.network.State
import com.ac.taipeizooguide.ui.OnItemClickListener
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
                dismissLoading()
                updateDistrictList(it.data!!)
            }
            State.ERROR -> {
                dismissLoading()
            }
            State.LOADING -> {
                showLoading()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDistricBinding.inflate(inflater, container, false)
        districtViewModel.districtList.observe(viewLifecycleOwner, observer)
        return binding.root
    }


    private fun updateDistrictList(districtResult: DistrictResult) {
        binding.rvDistrictList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DistrictListAdapter(districtResult.districtList)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            //navigate to detail page
            addOnItemClickListener(object : OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {
                    val bundle = bundleOf("dist" to districtResult.districtList[position])
                    findNavController().navigate(R.id.navi_distric_detail, bundle)
                }
            })
        }
    }

    private fun showLoading() {
        binding.pbLoadingMain.visibility = View.VISIBLE
    }

    private fun dismissLoading() {
        binding.pbLoadingMain.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}