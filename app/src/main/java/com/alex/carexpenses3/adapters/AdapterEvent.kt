package com.alex.carexpenses3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.R
import com.alex.carexpenses3.databinding.ItemEventBinding
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.APP_ACTIVITY

class AdapterEvent: RecyclerView.Adapter<AdapterEvent.MyHolder>() {

    var listEvents = emptyList<Event>()
    var listExpenses = emptyList<Expense>()

    fun setList(list: List<Event>){
        listEvents = list
        listExpenses =
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listEvents.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val event = listEvents[position]
        var mAdapter = AdapterEventChild()

        holder.date.text = event.date.substring(0,10)
        holder.odometer.text = APP_ACTIVITY.getString(R.string.text_km_template, event.odometer)
        holder.sum.text = APP_ACTIVITY.getString(R.string.text_currency_template, event.sum)
        holder.recyclerView.adapter = mAdapter
        mAdapter.setData(event.id, listExpenses)

    }

    class MyHolder(private val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root){
        var date = binding.itemEventDate
        var odometer = binding.itemEventOdometer
        var sum = binding.itemEventSum
        var recyclerView = binding.itemRecyclerView
    }
}