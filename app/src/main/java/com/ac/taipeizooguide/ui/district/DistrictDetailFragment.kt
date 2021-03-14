package com.ac.taipeizooguide.ui.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac.taipeizooguide.databinding.FragmentDistricDetailBinding
import com.ac.taipeizooguide.loadUrl
import com.ac.taipeizooguide.model.District
import com.ac.taipeizooguide.model.PlantResult
import com.ac.taipeizooguide.network.Response
import com.ac.taipeizooguide.network.State
import com.ac.taipeizooguide.setActionBar
import com.ac.taipeizooguide.setMemo
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
        setupUI()
        setupObserver()
        return binding.root
    }

    private fun setupUI() {
        plantListAdapter = PlantListAdapter(arrayListOf())

        binding.apply {
            rvPlantList.apply {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                rvPlantList.adapter = plantListAdapter
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
        zooViewModel.getPlantList(location = districtDetail.districtName)
            .observe(viewLifecycleOwner, Observer<Response<PlantResult>> {
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

    private fun dismissLoading() {
        binding.apply {
            pbLoadingPlants.visibility = View.GONE
            rvPlantList.visibility = View.VISIBLE
        }
    }

    private fun showLoading() {
        binding.apply {
            rvPlantList.visibility = View.GONE
            pbLoadingPlants.visibility = View.VISIBLE
        }
    }

    private fun updatePlantList(plantResult: PlantResult) {
        plantListAdapter.apply {
            setData(plantResult.plantList)
            notifyDataSetChanged()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}