package com.example.proyecto_final_nicolas.ui.destinations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final_nicolas.data.model.Destination
import com.example.proyecto_final_nicolas.databinding.ItemDestinationBinding

class DestinationsAdapter(
    private val destinations: List<Destination>,
    private val onItemClick: (Destination) -> Unit
) : RecyclerView.Adapter<DestinationsAdapter.DestinationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val binding = ItemDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DestinationViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        holder.bind(destinations[position])
    }

    override fun getItemCount(): Int = destinations.size

    class DestinationViewHolder(
        private val binding: ItemDestinationBinding,
        private val onItemClick: (Destination) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(destination: Destination) {
            binding.ivDestinationImage.setImageResource(destination.imageResId)
            binding.tvDestinationTitle.text = destination.title
            binding.tvDestinationDescription.text = destination.description
            itemView.setOnClickListener { onItemClick(destination) }
        }
    }
}