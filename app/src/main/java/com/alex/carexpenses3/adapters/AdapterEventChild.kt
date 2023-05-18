package com.alex.carexpenses3.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.R
import com.alex.carexpenses3.databinding.ItemEventChildBinding
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.APP_ACTIVITY
import com.alex.carexpenses3.utils.TAG

class AdapterEventChild : RecyclerView.Adapter<AdapterEventChild.ChildEventHolder>() {

    private var listExpense = emptyList<Expense>()

    fun setData(list: List<Expense>) {
        Log.d(TAG, "AdapterEventChild.setData, list size = ${list.size}")
        listExpense = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildEventHolder {
        val binding = ItemEventChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildEventHolder(binding)
    }

    override fun getItemCount() = listExpense.size

    override fun onBindViewHolder(holder: ChildEventHolder, position: Int) {
        Log.d(TAG, "AdapterEventChild.onBindViewHolder")
        val expense = listExpense[position]

        holder.description.text = expense.description
        holder.descriptionDetail.text = expense.detailDescription
        holder.partNum.text = expense.partNum
        holder.quantity.text = APP_ACTIVITY.getString(R.string.text_quantity_template, expense.quantity)
        holder.price.text = APP_ACTIVITY.getString(R.string.text_currency_template, expense.price)
    }

    class ChildEventHolder(binding: ItemEventChildBinding) : RecyclerView.ViewHolder(binding.root) {
        val description = binding.childDescription
        val descriptionDetail = binding.childDescriptionDetail
        val partNum = binding.childPartNum
        val quantity = binding.childQuantity
        val price = binding.childPrice
    }
}