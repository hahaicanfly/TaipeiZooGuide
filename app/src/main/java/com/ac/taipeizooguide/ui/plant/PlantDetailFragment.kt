package com.ac.taipeizooguide.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ac.taipeizooguide.R
import com.ac.taipeizooguide.databinding.FragmentPlantDetailBinding
import com.ac.taipeizooguide.loadUrl
import com.ac.taipeizooguide.model.Plant
import com.ac.taipeizooguide.setActionBar

/**
 * Created on 2021/3/8.
 */
class PlantDetailFragment : Fragment() {

    private var _binding: FragmentPlantDetailBinding? = null
    private val binding get() = _binding!!

    private val plantDetail: Plant by lazy {
        arguments?.get("plant") as Plant
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar(true, plantDetail.nameCh)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        binding.apply {
            imgPlantDetail.loadUrl(plantDetail.pic01Url)

            plantDetailContent.apply {
                tvPlantDetailNameCh.text = plantDetail.nameCh
                tvPlantDetailNameEn.text = plantDetail.nameEn
                tvPlantDetailAlsoKnow.text =plantDetail.alsoKnow
                tvPlantDetailBriefContent.text = plantDetail.brief
                tvPlantDetailFeatureContent.text = plantDetail.feature
                tvPlantDetailApplicationContent.text = plantDetail.application
                tvPlantDetailLastUpdate.text = String.format(getString(R.string.text_last_update),plantDetail.update)
            }
        }
    }
}