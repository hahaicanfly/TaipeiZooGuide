package com.ac.taipeizooguide.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ac.taipeizooguide.R
import com.ac.taipeizooguide.databinding.ItemDistrictBinding
import com.ac.taipeizooguide.loadUrl
import com.ac.taipeizooguide.model.District
import com.ac.taipeizooguide.ui.OnItemClickListener

/**
 * Created on 2021/3/10.
 */
class DistrictListAdapter(
    private val districtList: List<District>,
) :
    RecyclerView.Adapter<DistrictListAdapter.DistrictViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val binding =
            ItemDistrictBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DistrictViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        districtList[position].let { holder.bind(it) }
        with(holder.itemView) {
            tag = districtList[position]
        }
    }

    override fun getItemCount(): Int = districtList.size

    inner class DistrictViewHolder(private val binding: ItemDistrictBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(district: District) {

            binding.apply {
                tvDistrictName.text = district.districtName
                tvDistrictInfo.text = district.info
                imgDistrict.loadUrl(district.picUrl)
                tvMemo.text =
                    if (district.memo.isNotEmpty()) district.memo else itemView.context.resources.getString(
                        R.string.no_official_holiday_message
                    )
            }
        }
    }
}