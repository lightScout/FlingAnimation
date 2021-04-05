package com.lightscout.flinganimation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lightscout.flinganimation.databinding.PhotoItemLayoutBinding

class PhotoAdapter(var photoList: List<PhotoItem>): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    inner class PhotoViewHolder(val binding: PhotoItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder = PhotoViewHolder(PhotoItemLayoutBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {


        holder.binding.apply {
            Glide.with(this.photoImageView)
                .load(photoList[position].photo)
                .into(this.photoImageView)

        }



    }

    override fun getItemCount(): Int = photoList.size
}