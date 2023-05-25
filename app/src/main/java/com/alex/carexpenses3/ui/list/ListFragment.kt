package com.alex.carexpenses3.ui.list

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.R
import com.alex.carexpenses3.adapters.AdapterEvent
import com.alex.carexpenses3.databinding.FragmentListBinding
import com.alex.carexpenses3.model.Car
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.APP_ACTIVITY
import com.alex.carexpenses3.utils.Decorator
import com.alex.carexpenses3.utils.ODOMETER
import com.alex.carexpenses3.utils.REPOSITORY
import com.alex.carexpenses3.utils.TAG

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    lateinit var mListViewModel: ListViewModel
    private lateinit var mCarObserver: Observer<Car>
    private lateinit var mEventsObserver: Observer<List<Event>>
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AdapterEvent
    private lateinit var mToolbar: ActionBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mListViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        Log.d(TAG, "ListFragment.onStart")
        super.onStart()

        initObserves()
        setHasOptionsMenu(true)

        mToolbar = APP_ACTIVITY.supportActionBar!!

        mRecyclerView = binding.listRecyclerView
        mAdapter = AdapterEvent()
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(Decorator(5))

        mListViewModel.car.observe(viewLifecycleOwner, mCarObserver)
        mListViewModel.listEvents.observe(viewLifecycleOwner, mEventsObserver)

    }

    private fun initObserves() {
        Log.d(TAG, "ListFragment.initObserves")

        mCarObserver = Observer {
            Log.d(TAG, it.toString())
            mToolbar.title = APP_ACTIVITY.getString(R.string.toolbar_model, it.brand, it.model)
        }

        mEventsObserver = Observer {
            //якщо змінився список Івентів, запитатии список Експенсів

            if (it.isNotEmpty()) {
                binding.listTvNoEvent.visibility = View.INVISIBLE

                val listExpenses = mutableListOf<Expense>()


                listExpenses.add(Expense(1, 0, 0, 123456, 1, 168.0, "01-01-2000", "Фільтр масляний", "Man 9999", "ABC181920"))

                mAdapter.setList(it, listExpenses)
            }
        }
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