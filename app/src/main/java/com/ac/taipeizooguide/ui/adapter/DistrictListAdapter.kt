package com.ac.taipeizooguide.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ac.taipeizooguide.databinding.ItemDistrictBinding
import com.ac.taipeizooguide.model.District

/**
 * Created on 2021/3/10.
 */
class DistrictListAdapter(private val districtList: List<District>) :
    RecyclerView.Adapter<DistrictListAdapter.DistrictViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val binding =
            ItemDistrictBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DistrictViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        districtList[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = districtList.size

    inner class DistrictViewHolder(private val binding: ItemDistrictBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(district: District) {
            binding.tvDistrictName.text = district.districtName
        }
    }
}