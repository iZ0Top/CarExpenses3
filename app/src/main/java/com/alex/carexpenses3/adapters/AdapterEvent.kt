package com.alex.carexpenses3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.databinding.ItemEventBinding
import com.alex.carexpenses3.model.Event

class AdapterEvent: RecyclerView.Adapter<AdapterEvent.MyHolder>() {

    var listEvent = emptyList<Event>()

    override fun getItemCount(): Int {
        return listEvent.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }



    override fun onBindViewHolder(holder: MyHolder, position: Int) {

    }

    class MyHolder(private val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root){

    }
}