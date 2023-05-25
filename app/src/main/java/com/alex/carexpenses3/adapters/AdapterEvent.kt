package com.alex.carexpenses3.adapters

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.R
import com.alex.carexpenses3.databinding.ItemEventBinding
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.APP_ACTIVITY
import com.alex.carexpenses3.utils.REPOSITORY
import com.alex.carexpenses3.utils.TAG
import com.alex.carexpenses3.utils.showToast

class AdapterEvent: RecyclerView.Adapter<AdapterEvent.MyHolder>(), View.OnLongClickListener {


    var listEvents = emptyList<Event>()
    var listExpenses = emptyList<Expense>()

    class MyHolder(val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = listEvents.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        Log.d(TAG, "AdapterEvent.onCreateViewHolder")
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.setOnLongClickListener(this)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Log.d(TAG, "AdapterEvent.onBindViewHolder")
        val event = listEvents[position]
        val childAdapter = AdapterEventChild()

        with(holder.binding){
            holder.itemView.tag = event
            itemEventDate.text = event.date.substring(0,10)
            itemEventOdometer.text = APP_ACTIVITY.getString(R.string.text_km_template, event.odometer)
            itemEventSum.text = APP_ACTIVITY.getString(R.string.text_currency_template, event.sum)
            itemRecyclerView.adapter = childAdapter
        }

        childAdapter.setData(listExpenses)
    }

    override fun onLongClick(v: View): Boolean {
        Log.d(TAG, "AdapterEvent.onLongClick")
        showMenu(v)
        return false
    }

    fun setList(listEvents: List<Event>, listExpense: List<Expense>){
        Log.d(TAG, "AdapterEvent.setList, listEvents size = ${listEvents.size}")
        this.listEvents = listEvents
        this.listExpenses = listExpense
        notifyDataSetChanged()
    }

    private fun showMenu(view: View){
        Log.d(TAG, "AdapterEvent.showMenu")
        val popupUpMenu = PopupMenu(view.context, view)
        popupUpMenu.menu.add(0, ID_MENU_EDIT, Menu.NONE, "Edit group")
        popupUpMenu.menu.add(0, ID_MENU_DELETE, Menu.NONE, "Delete group")
        val currentEvent = view.tag as Event
        popupUpMenu.setOnMenuItemClickListener {
            when(it.itemId){
                ID_MENU_EDIT -> {
                    showToast("MENU EDIT")
                }
                ID_MENU_DELETE -> {
                    showToast("MENU DELETE")
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupUpMenu.gravity = Gravity.CENTER_HORIZONTAL
        popupUpMenu.show()
    }


    companion object {
        private const val ID_MENU_EDIT = 0
        private const val ID_MENU_DELETE = 1
    }
}