package com.alex.carexpenses3.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.R
import com.alex.carexpenses3.databinding.ItemEventBinding
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.APP_ACTIVITY
import com.alex.carexpenses3.utils.TAG

class AdapterEvent: RecyclerView.Adapter<AdapterEvent.MyHolder>() {

    var listEvents = emptyList<Event>()
    var listExpenses = emptyList<Expense>()

    fun setList(listEvents: List<Event>){
        Log.d(TAG, "AdapterEvent.setList, listEvents size = ${listEvents.size}")
        this.listEvents = listEvents
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
        Log.d(TAG, "AdapterEvent.onBindViewHolder")
        val event = listEvents[position]
        val mAdapter = AdapterEventChild()

        holder.date.text = event.date.substring(0,10)
        holder.odometer.text = APP_ACTIVITY.getString(R.string.text_km_template, event.odometer)
        holder.sum.text = APP_ACTIVITY.getString(R.string.text_currency_template, event.sum)
        holder.recyclerView.adapter = mAdapter

        val newExpensesList = mutableListOf<Expense>()
        for (x in listExpenses){
            if (x.parent_id == event.id) newExpensesList.add(x)
        }
        mAdapter.setData(newExpensesList)
    }

    class MyHolder(private val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root){
        var date = binding.itemEventDate
        var odometer = binding.itemEventOdometer
        var sum = binding.itemEventSum
        var recyclerView = binding.itemRecyclerView
    }
}