package com.ac.taipeizooguide.ui.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac.taipeizooguide.*
import com.ac.taipeizooguide.databinding.FragmentDistricDetailBinding
import com.ac.taipeizooguide.model.District
import com.ac.taipeizooguide.model.Plant
import com.ac.taipeizooguide.model.PlantResult
import com.ac.taipeizooguide.network.Response
import com.ac.taipeizooguide.network.State
import com.ac.taipeizooguide.ui.OnItemClickListener
import com.ac.taipeizooguide.ui.adapter.PlantListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created on 2021/3/8.
 */
class DistrictDetailFragment : Fragment() {

    private var _binding: FragmentDistricDetailBinding? = null
    private val binding get() = _binding!!
    private val zooViewModel: ZooViewModel by viewModel()

    private lateinit var plantListAdapter: PlantListAdapter
    private val districtDetail: District by lazy {
        arguments?.get("dist") as District
    }
    private lateinit var plantList: List<Plant>

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
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        setActionBar(true, districtDetail.districtName)
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
                        findNavController().navigate(R.id.navi_plant_detail, bundle)
                    }
                })
            }

            with(districtDetail) {
                imgDistrictDetail.loadUrl(picUrl)
                tvDetailInfo.text = info
                tvDetailMemo.setMemo(memo.takeIf { it.isNotEmpty() })
                tvDetailCategory.text = category
            }
        }
    }

    private fun setupObserver() {

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
        plantList = plantResult.plantList

        //view state
        binding.rvPlantList.visibility = if (plantList.isEmpty()) View.GONE else View.VISIBLE
        binding.tvNoPlant.visibility = if (plantList.isEmpty()) View.VISIBLE else View.GONE

        plantListAdapter.apply {
            setData(plantList)
            notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}