package com.e.fabwithanmation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.fabwithanmation.R
import com.e.fabwithanmation.roomdb.ImageUris
import kotlinx.android.synthetic.main.list_item.view.*

class ImagesAdapter(val context : Context) :
    ListAdapter<ImageUris, ImagesAdapter.ImagesViewHolder>(
        ImageUriDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder =
        ImagesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        init {
//            itemView.setOnClickListener {
//                onClick(getItem(adapterPosition))
//            }
//        }

        fun bind(imageUri: ImageUris) {
            Glide.with(context)
                .load(imageUri.imageuri)
                .into(itemView.im_image)
        }
    }

    class ImageUriDiffUtil : DiffUtil.ItemCallback<ImageUris>() {
        override fun areItemsTheSame(oldItem: ImageUris, newItem: ImageUris): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageUris, newItem: ImageUris): Boolean {
            return oldItem == newItem
        }
    }
}