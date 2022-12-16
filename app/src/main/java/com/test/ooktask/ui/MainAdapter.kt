package com.test.ooktask.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.ooktask.databinding.LoImageBinding
import com.test.ooktask.model.PostCardModel

class MainAdapter(var items: ArrayList<PostCardModel>, var context: Context) :RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: LoImageBinding) : RecyclerView.ViewHolder(binding.root) {
        var image = binding.ivPhoto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            LoImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val image = items[position]
        holder.image

        Glide.with(context)
            .load(image)
            .into(holder.image)
    }

    override fun getItemCount(): Int = items.size
}