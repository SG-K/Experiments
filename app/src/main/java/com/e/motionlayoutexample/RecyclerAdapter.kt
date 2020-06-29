package com.e.motionlayoutexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false) as ConstraintLayout)
    }

    override fun getItemCount(): Int {
        return 50
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) = Unit

    class ViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout)

}