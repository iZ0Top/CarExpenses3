package com.alex.carexpenses3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.R
import com.alex.carexpenses3.databinding.ItemEventChildBinding
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.APP_ACTIVITY

class AdapterEventChild: RecyclerView.Adapter<AdapterEventChild.ChildEventHolder>() {

    private var listExpense = emptyList<Expense>()
    private var parentId = 0

    fun setData(id: Int, list: List<Expense>){
        listExpense = list
        parentId = id
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildEventHolder {
        val binding = ItemEventChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildEventHolder(binding)
    }

    override fun getItemCount() = listExpense.size


    override fun onBindViewHolder(holder: ChildEventHolder, position: Int) {
        val expense = listExpense[position]
        if (expense.parent_id == parentId ){
            holder.description.text = expense.description
            holder.descriptionDetail.text = expense.detailDescription
            holder.partNum.text = expense.partNum
            holder.quantity.text = APP_ACTIVITY.getString(R.string.text_quantity_template, expense.quantity)
            holder.price.text = APP_ACTIVITY.getString(R.string.text_currency_template, expense.price)
        }
    }


    class ChildEventHolder(binding: ItemEventChildBinding ): RecyclerView.ViewHolder(binding.root){
        val description = binding.childDescription
        val descriptionDetail = binding.childDescriptionDetail
        val partNum = binding.childPartNum
        val quantity = binding.childQuantity
        val price = binding.childPrice
    }
}