package com.kumparan.assesment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kumparan.assesment.data.model.Comment
import com.kumparan.assesment.databinding.ItemCommentBinding

class CommentPostAdapter(private val listComment: List<Comment>) :
    RecyclerView.Adapter<CommentPostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listComment[position])
    }

    override fun getItemCount(): Int = listComment.size

    class ViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Comment) {
            binding.commentAuthorName.text = data.name
            binding.commentBody.text = data.body
        }
    }
}