package com.alex.carexpenses3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.databinding.ItemEventBinding
import com.alex.carexpenses3.model.Event

class AdapterEvent: RecyclerView.Adapter<AdapterEvent.myHolder>() {

    var listEvent = emptyList<Event>()

    override fun getItemCount(): Int {
        return listEvent.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {

        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return binding
    }



    override fun onBindViewHolder(holder: myHolder, position: Int) {

    }

    class myHolder(val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root){

    }
}