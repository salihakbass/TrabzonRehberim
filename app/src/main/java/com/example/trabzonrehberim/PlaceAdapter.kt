package com.example.trabzonrehberim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabzonrehberim.databinding.PlaceCardViewBinding

class PlaceAdapter(private val places: List<Place>) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {
    class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvPlace)
        val image: ImageView = itemView.findViewById(R.id.imgPlace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_card_view, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.name.text = place.name
        // Google Places photo API ile resim yükleme
        val photoUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=200&photoreference=${place.photos?.get(0)?.photo_reference}&key=AIzaSyAL5krlD2zjNRHF3rm1f9ZG9af-xUd_c0o"
        Glide.with(holder.itemView.context).load(photoUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return places.size
    }
}
