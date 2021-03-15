package com.ac.taipeizooguide.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac.taipeizooguide.R
import com.ac.taipeizooguide.addOnItemClickListener
import com.ac.taipeizooguide.base.BaseFragment
import com.ac.taipeizooguide.databinding.FragmentDistricBinding
import com.ac.taipeizooguide.model.DistrictResult
import com.ac.taipeizooguide.network.State
import com.ac.taipeizooguide.setActionBar
import com.ac.taipeizooguide.ui.OnItemClickListener
import com.ac.taipeizooguide.ui.adapter.DistrictListAdapter

/**
 * Created on 2021/3/8.
 */
class DistrictFragment : BaseFragment() {

    private var _binding: FragmentDistricBinding? = null
    private val binding get() = _binding!!

    private lateinit var districtListAdapter: DistrictListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDistricBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI() {
        setActionBar(false)
        districtListAdapter = DistrictListAdapter(arrayListOf())
        binding.rvDistrictList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = districtListAdapter
            //navigate to detail page
            addOnItemClickListener(object : OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {

                    val dist = zooViewModel.getDistrict(position)
                    val bundle = bundleOf("dist" to dist)
                    findNavController().navigate(R.id.navi_distric_detail, bundle)
                }
            })
        }
    }


    override fun setupObserver() {
        //get district data
        zooViewModel.districtResult.observe(viewLifecycleOwner, {
            when (it.state) {
                State.SUCCESS -> {
                    dismissLoading()
                    it.data?.let { districtResult ->
                        updateDistrictList(districtResult)
                    }
                }
                State.ERROR -> {
                    dismissLoading()
                }
                State.LOADING -> {
                    showLoading()
                }
            }
        })
    }


    private fun updateDistrictList(districtResult: DistrictResult) {
        districtListAdapter.apply {
            setData(districtResult.districtList)
            notifyDataSetChanged()
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