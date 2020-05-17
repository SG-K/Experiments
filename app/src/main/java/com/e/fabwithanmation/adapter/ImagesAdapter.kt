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
        private val image_view by lazy { itemView.findViewById<ImageView>(R.id.im_image) }

//        init {
//            itemView.setOnClickListener {
//                onClick(getItem(adapterPosition))
//            }
//        }

        fun bind(imageUri: ImageUris) {
            Glide.with(context)
                .asBitmap()
                .skipMemoryCache(true)
                .load(imageUri.imageuri)
                .into(image_view)
        }
    }

    class ImageUriDiffUtil : DiffUtil.ItemCallback<ImageUris>() {
        override fun areItemsTheSame(oldItem: ImageUris, newItem: ImageUris): Boolean {
            return oldItem.imageuri == newItem.imageuri
        }

        override fun areContentsTheSame(oldItem: ImageUris, newItem: ImageUris): Boolean {
            return oldItem == newItem
        }
    }
}