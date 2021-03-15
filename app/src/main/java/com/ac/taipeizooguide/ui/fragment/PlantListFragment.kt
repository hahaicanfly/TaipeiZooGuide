package com.ac.taipeizooguide.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac.taipeizooguide.R
import com.ac.taipeizooguide.addOnItemClickListener
import com.ac.taipeizooguide.base.BaseFragment
import com.ac.taipeizooguide.databinding.FragmentPlantListBinding
import com.ac.taipeizooguide.model.District
import com.ac.taipeizooguide.model.Plant
import com.ac.taipeizooguide.model.PlantResult
import com.ac.taipeizooguide.network.Response
import com.ac.taipeizooguide.network.State
import com.ac.taipeizooguide.ui.OnItemClickListener
import com.ac.taipeizooguide.ui.adapter.PlantListAdapter

/**
 * Created on 2021/3/15.
 */
class PlantListFragment : BaseFragment() {

    companion object {
        private const val ARG_PLANT_LIST = "plant_list"

        fun getInstance(district: District): PlantListFragment {
            return PlantListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PLANT_LIST, district)
                }
            }
        }
    }

    private var _binding: FragmentPlantListBinding? = null
    private val binding get() = _binding!!

    private lateinit var plantListAdapter: PlantListAdapter
    private lateinit var plantList: List<Plant>

    private val districtDetail: District by lazy {
        arguments?.get(ARG_PLANT_LIST) as District
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlantListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI() {
        plantListAdapter = PlantListAdapter(arrayListOf())

        binding.apply {
            rvPlantList.apply {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                rvPlantList.adapter = plantListAdapter

                //to plant detail page
                addOnItemClickListener(object : OnItemClickListener {
                    override fun onItemClicked(position: Int, view: View) {
                        val plant = plantList[position]
                        val bundle = bundleOf("plant" to plant)
                        findNavController().navigate(
                            R.id.action_navi_distric_detail_to_navi_plant_detail,
                            bundle
                        )
                    }
                })
            }
        }
    }

    override fun setupObserver() {

        zooViewModel.apply {
            location = districtDetail.districtName
            plantResult.observe(viewLifecycleOwner, Observer<Response<PlantResult>> {
                when (it.state) {
                    State.SUCCESS -> {
                        dismissLoading()
                        it.data?.let { plantList -> updatePlantList(plantList) }
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
    }

    private fun dismissLoading() {
        binding.pbLoadingPlants.visibility = View.GONE
    }

    private fun showLoading() {
        binding.pbLoadingPlants.visibility = View.VISIBLE
    }

    private fun updatePlantList(plantResult: PlantResult) {
        //distinct response data by same name
        plantList = plantResult.plantList.distinctBy { it.nameCh }

        //view state
        binding.rvPlantList.visibility = if (plantList.isEmpty()) View.GONE else View.VISIBLE
        binding.tvNoPlant.visibility = if (plantList.isEmpty()) View.VISIBLE else View.GONE

        plantListAdapter.apply {
            setData(plantList)
            notifyDataSetChanged()
        }
    }
}