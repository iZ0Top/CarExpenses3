package com.alex.carexpenses3.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.R
import com.alex.carexpenses3.adapters.AdapterEvent
import com.alex.carexpenses3.databinding.FragmentListBinding
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.APP_ACTIVITY
import com.alex.carexpenses3.utils.ODOMETER
import com.alex.carexpenses3.utils.TAG

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    lateinit var mListViewModel: ListViewModel
    private lateinit var mEventsObserver: Observer<List<Event>>
    private lateinit var mExpensesObserver: Observer<List<Expense>>
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AdapterEvent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mListViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)

        val toolbar = APP_ACTIVITY.supportActionBar
        if (toolbar != null) {
            toolbar.title = "sdfsd"
        }

        mRecyclerView = binding.listRecyclerView
        mAdapter = AdapterEvent()
        mRecyclerView.adapter = mAdapter

        mEventsObserver = Observer {
            mAdapter.setList(it)
            for (x in  it){
                if (x.odometer > ODOMETER) ODOMETER = x.odometer
            }
        }
        mExpensesObserver = Observer {
            for (x in it) {
                Log.d(TAG, x.toString() + "\n")
            }
        }

        mListViewModel.listEvents.observe(viewLifecycleOwner, mEventsObserver)
        mListViewModel.listExpenses.observe(viewLifecycleOwner, mExpensesObserver)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_add -> {
                APP_ACTIVITY.navController.navigate(R.id.action_navigation_list_to_addFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mRecyclerView.adapter = null
        mListViewModel.listEvents.removeObserver(mEventsObserver)
        _binding = null
    }
}