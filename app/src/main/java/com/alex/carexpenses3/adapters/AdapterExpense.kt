package com.alex.carexpenses3.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.databinding.ItemExpenseBinding
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.TAG

class AdapterExpense: RecyclerView.Adapter<AdapterExpense.ExpenseHolder>() {

    private var list = emptyList<Expense>()

    fun setList(list: List<Expense>){
        Log.d(TAG, "AdapterExpense.setList - list size=${list.size} ")
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseHolder(binding)
    }


    override fun onBindViewHolder(holder: ExpenseHolder, position: Int) {

        val expense = list[position]

        holder.description.text = expense.description
        holder.detailDescription.text = expense.detailDescription
        holder.partNumber.text = expense.partNum
        holder.quantity.text = expense.quantity.toString()
        holder.price.text = expense.price.toString()

    }

    class ExpenseHolder(binding: ItemExpenseBinding): RecyclerView.ViewHolder(binding.root){

        val description = binding.description
        val detailDescription = binding.detail
        val partNumber = binding.partNum
        val quantity = binding.quantity
        val price = binding.price
    }
}