package com.example.trabzonrehberim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabzonrehberim.databinding.PlaceCardViewBinding

class PlaceAdapter(private val list: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: PlaceCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Place) {
            with(binding) {
                imgPlace.setImageResource(item.image)
                tvPlace.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PlaceCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}