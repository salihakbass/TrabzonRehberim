package com.example.trabzonrehberim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabzonrehberim.databinding.ItemPhotoBinding

class PhotoPagerAdapter(
    private val photoUrls: List<String>
) : RecyclerView.Adapter<PhotoPagerAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val imageUrl = photoUrls[position]
        Glide.with(holder.binding.imageView.context)
            .load(imageUrl)
            .into(holder.binding.imageView)
    }

    override fun getItemCount(): Int = photoUrls.size
}
