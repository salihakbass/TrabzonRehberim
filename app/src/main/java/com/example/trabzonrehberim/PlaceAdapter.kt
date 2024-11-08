package com.example.trabzonrehberim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.trabzonrehberim.databinding.PlaceCardViewBinding

class PlaceAdapter(
    private val places: List<Place>
) :
    RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {
    class PlaceViewHolder(val binding: PlaceCardViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding =
            PlaceCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        with(holder.binding) {
            tvPlace.text = place.name
            Glide.with(imgPlace.context)
                .load(place.image)
                .override(400, 400)
                .into(imgPlace)

            cardView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(place)
                Navigation.findNavController(it).navigate(action)
            }
        }


    }

    override fun getItemCount(): Int = places.size
}

