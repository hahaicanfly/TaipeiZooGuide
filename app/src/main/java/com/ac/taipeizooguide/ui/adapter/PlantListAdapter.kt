package com.ac.taipeizooguide.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ac.taipeizooguide.databinding.ItemPlantBinding
import com.ac.taipeizooguide.loadUrl
import com.ac.taipeizooguide.model.Plant

/**
 * Created on 2021/3/13.
 */
class PlantListAdapter(private val plantList: MutableList<Plant>) :
    RecyclerView.Adapter<PlantListAdapter.PlantViewHolder>() {

    fun setData(list: List<Plant>) {
        this.plantList.apply {
            clear()
            addAll(list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ItemPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        plantList[position].let { holder.bind(it) }
        with(holder.itemView) {
            tag = plantList[position]
        }
    }

    override fun getItemCount(): Int = plantList.size

    inner class PlantViewHolder(private val binding: ItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: Plant) {
            binding.apply {
                imgPlant.loadUrl(plant.pic01Url)
                tvPlantName.text = plant.nameCh
                tvAlsoKnow.text = plant.alsoKnow
            }
        }
    }
}
