package com.kumparan.assesment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kumparan.assesment.data.model.Album
import com.kumparan.assesment.data.model.Photo
import com.kumparan.assesment.databinding.ItemAlbumBinding

class AlbumAdapter(
    private val album: List<HashMap<Album, List<Photo>>>,
    private val onCLick: (photo: Photo) -> Unit
) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(album[position], onCLick)
    }

    override fun getItemCount(): Int = album.size

    class ViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: HashMap<Album, List<Photo>>, onCLick: (photo: Photo) -> Unit) {
            binding.albumName.text = data.keys.elementAt(0).title
            val photoAdapter = PhotoAdapter(data.getValue(data.keys.elementAt(0)), onCLick)

            binding.rvPhotos.apply {
                visibility = View.VISIBLE
                layoutManager = GridLayoutManager(itemView.context, 5)
                adapter = photoAdapter
            }
        }
    }
}