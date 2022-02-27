package com.kumparan.assesment.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.kumparan.assesment.data.model.Photo
import com.kumparan.assesment.databinding.ItemPhotoBinding

class PhotoAdapter(private val photo: List<Photo>, private val onCLick:(photo:Photo)->Unit) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photo[position],onCLick)
    }

    override fun getItemCount(): Int = photo.size

    class ViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Photo, onCLick:(photo:Photo)->Unit) {

            val image = GlideUrl(
                data.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "5")
                    .build()
            )
            val options = RequestOptions().override(75,75)

            Glide.with(itemView.context)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(options)
                .centerCrop()
                .skipMemoryCache(true)
                .into(binding.itemPhoto)

            binding.itemPhoto.setOnClickListener {
                onCLick(data)
            }
        }
    }
}