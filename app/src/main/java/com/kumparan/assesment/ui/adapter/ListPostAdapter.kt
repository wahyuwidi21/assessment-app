package com.kumparan.assesment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kumparan.assesment.data.model.PostFinal
import com.kumparan.assesment.databinding.ItemPostBinding

class ListPostAdapter(private val listPost: List<PostFinal>, private val onClick:(postFinal:PostFinal)->Unit) :
    RecyclerView.Adapter<ListPostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listPost[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onClick(data)
        }

    }

    override fun getItemCount(): Int = listPost.size

    class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(data: PostFinal) {
            binding.postUserName.text = data.userName
            binding.postCompanyName.text = data.companyName
            binding.postTitle.text = data.title
           binding.postBody.text = data.body
        }
    }
}